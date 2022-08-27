package acmicpc._10070

import kotlin.math.ceil
import kotlin.math.log2

lateinit var arr: Array<Node>
lateinit var ans: IntArray

data class Node(var low: Int, var high: Int) {
    fun clear() {
        low = 0
        high = Int.MAX_VALUE
    }

    fun add(op: Int, v: Int) {
        if(op == 1) {
            low = maxOf(low, v)
            high = maxOf(high, v)
        } else {
            low = minOf(low, v)
            high = minOf(high, v)
        }
    }
}

fun update(i: Int, s: Int, e: Int, l: Int, r: Int, op: Int, v: Int) {
    if(r < s || e < l) return
    if(l <= s && e <= r) {
        arr[i].add(op, v)
        ans[l] = arr[i].low
        return
    }

    arr[i*2].add(1, arr[i].low)
    arr[i*2].add(2, arr[i].high)
    arr[i*2+1].add(1, arr[i].low)
    arr[i*2+1].add(2, arr[i].high)
    arr[i].clear()

    val mid = (s+e)/2
    update(i*2, s, mid, l, r, op, v)
    update(i*2+1, mid+1, e, l, r, op, v)
}

fun main() {
    val (n,k) = readLine()!!
        .split(" ")
        .map{ it.toInt() }

    arr = Array( 1 shl (ceil(log2(n.toDouble())).toInt()+1) ) { Node(0,0) }
    ans = IntArray(n)

    for(i in 0 until k) {
        val tmp = readLine()!!
            .split(" ")
            .map{ it.toInt() }
        update(1,0, n-1, tmp[1], tmp[2], tmp[0], tmp[3])
    }

    for(i in 0 until n) {
        update(1, 0, n-1, i, i, 1, 0)
    }
    println(ans.joinToString("\n"))
}
