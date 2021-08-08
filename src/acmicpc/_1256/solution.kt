package acmicpc._1256

val arr = Array(201) { LongArray(201) }.apply {
    this[1][0] = 1
    this[1][1] = 1
    val mv: Long = Long.MAX_VALUE/2
    for(i in 2 until this.size) {
        for(j in 0..i) {
            if(j == 0 || j == i) {
                this[i][j] = 1
            } else {
                this[i][j] = minOf(this[i-1][j]+this[i-1][j-1], Long.MAX_VALUE/2)
            }
        }
    }
}

fun main() {
    val tmp = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    if(tmp[2].toLong() > arr[tmp[0]+tmp[1]][tmp[0]]) {
        println(-1)
    } else {
        println(solve(tmp[0], tmp[1], tmp[2].toLong(), ""))
    }
}

fun solve(n: Int, m: Int, k: Long, ans: String): String {
//    println("$n $m $k >> $ans")
    if(n == 0) {
        return ans+"z".repeat(m)
    }
    if(m == 0) {
        return ans+"a".repeat(n)
    }

    return if(k <= arr[n+m-1][n-1]) {
        solve(n-1,m,k,ans+"a")
    } else {
        solve(n,m-1,k-arr[n+m-1][n-1],ans+"z")
    }
}