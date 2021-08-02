package acmicpc._14501

var n = 0
lateinit var cache: IntArray
fun main() {
    n = readLine()!!.toInt()
    cache = IntArray(n+1){ -1 }
    val list = mutableListOf<Consulting>()
    for(i in 1..n) {
        val tmp = readLine()!!.split(" ")
        list.add(Consulting(tmp[0].toInt(), tmp[1].toInt()))
    }
    println(solve(0, list))
}

fun solve(idx: Int, list: List<Consulting>): Int {
    if(idx >= list.size) {
        return 0
    }

    if(cache[idx] != -1) {
        return cache[idx]
    }

    var ret = 0
    for(i in idx until list.size) {
        if(i+list[idx].d <= n) {
            ret = maxOf(ret, list[idx].value+solve(i+list[idx].d, list))
        }
    }
    ret = maxOf(ret, solve(idx+1, list))
    cache[idx] = ret
    return ret
}

data class Consulting(val d: Int, val value: Int)