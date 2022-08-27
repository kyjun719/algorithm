package acmicpc._1520

var n = 0
var m = 0
var cache = Array<IntArray>(n) { IntArray(m) }
var arr = Array<IntArray>(n) { IntArray(m) }
val del = arrayOf(intArrayOf(-1,0), intArrayOf(1,0), intArrayOf(0,-1), intArrayOf(0,1))

fun main() {
    val (nn, mm) = readLine()!!.split(" ").map { it.toInt() }
    n = nn
    m = mm

    cache = Array<IntArray>(n) { IntArray(m) { -1 } }
    arr = Array<IntArray>(n) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }

    println(dfs(0,0))
}

fun dfs(ny: Int, nx: Int): Int {
    if(ny == n-1 && nx == m-1) {
        return 1
    }

    if(cache[ny][nx] != -1) {
        return cache[ny][nx]
    }

    var ret = 0
    del.forEach {
        val next = Pair(ny + it[0], nx + it[1])
        if(next.isInBound() && arr[ny][nx] > arr[next.first][next.second]) {
            ret += dfs(next.first, next.second)
        }
    }
    cache[ny][nx] = ret
    return ret
}

fun Pair<Int, Int>.isInBound(): Boolean {
    return first in 0 until n && second in 0 until m
}