package acmicpc._18868

const val SAME = 0
const val SMALL = 1
const val LARGE = 2
fun main() {
    val t = readLine()!!.split(" ")
    val m = t[0].toInt()
    val n = t[1].toInt()

    val arr = Array<Array<IntArray>>(m) {
        Array(n) { IntArray(n) { -1 } }
    }

    for(k in 0 until m) {
        val ret = readLine()!!.split(" ").map { it.toInt() }
        ret.forEachIndexed { i, num ->
            for(j in i+1 until ret.size) {
                arr[k][i][j] = when {
                    num == ret[j] -> SAME
                    num < ret[j] -> SMALL
                    else -> LARGE
                }
            }
        }
    }

    var cnt = 0
    for(i in 0 until m) {
        for(j in i+1 until m) {
            var isEqual = true
            for(y in 0 until n) {
                for(x in y+1 until n) {
                    if(arr[i][y][x] != arr[j][y][x]) {
                        isEqual = false
                        break
                    }
                }
            }
            if(isEqual) cnt++
        }
    }
    println(cnt)
}