package acmicpc._1182

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private fun readWriteWithBuffer(func: (BufferedReader, BufferedWriter) -> Unit) {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    bufferedReader.use { br ->
        bufferedWriter.use { bw ->
            func(br, bw)
            bw.flush()
        }
    }
}

var intArr = intArrayOf()
fun main() {
    readWriteWithBuffer { br, bw ->
        val (n, s) = br.readLine().split(" ").map { it.toInt() }
        intArr = br.readLine().split(" ").map { it.toInt() }.toIntArray()

        var ret = solve(0, 0, n, s)
        if (s == 0) ret--
        bw.write(ret.toString())
    }
}

fun solve(idx: Int, sum: Int, n: Int, s: Int): Int {
    if (idx >= n) {
        return if (sum == s) 1 else 0
    }

    var ret = solve(idx + 1, sum, n, s)
    ret += solve(idx + 1, sum + intArr[idx], n, s)

    return ret
}