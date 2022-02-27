package acmicpc._4811

val cache = Array(31) { LongArray(31) { -1 } }

fun main() {
    while(true) {
        val n = readLine()!!.toInt()
        if(n == 0) {
            break
        }

        println(solve(n, n))
    }
}

fun solve(w: Int, h: Int): Long {
    if(w < 0 || h < 0) return 0
    if(w < h) return 0

    if(h == 0) return 1

    if(cache[w][h] != -1L) {
        return cache[w][h]
    }

    val ret = solve(w-1, h) + solve(w, h-1)
    cache[w][h] = ret
    return ret
}