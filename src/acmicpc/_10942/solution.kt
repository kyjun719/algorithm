package acmicpc._10942

fun main() {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    val d = Array<IntArray>(n) { IntArray(n) }

    d[0][0] = 1
    for(i in 1 until n) {
        d[i][i] = 1
        if(arr[i-1] == arr[i]) {
            d[i-1][i] = 1
        }
    }

    for(i in 0 until n) {
        d[i][i] = 1
        for(j in i-1 downTo 0) {
            if(arr[i] == arr[j]) {
                if(j+1 == i || d[j+1][i-1] == 1) {
                    d[j][i] = 1
                }
            }
        }
    }

    val sb = StringBuilder()
    val m = readLine()!!.toInt()
    for(i in 0 until m) {
        val (s, e) = readLine()!!.split(" ").map { it.toInt() }
        sb.append("${d[s-1][e-1]}\n")
    }
    print(sb.toString())
}
