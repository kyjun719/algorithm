package acmicpc._15649

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

var used = booleanArrayOf()
val numList = mutableListOf<Int>()
fun main() {
    readWriteWithBuffer { br, bw ->
        val (n, m) = br.readLine().split(" ").map { it.toInt() }

        used = BooleanArray(n + 1)

        solve(n, m, bw)
    }
}

fun solve(n: Int, m: Int, bw: BufferedWriter) {
    if (m == 0) {
        bw.write(numList.joinToString(separator = " "))
        bw.newLine()
        return
    }

    for (i in 1..n) {
        if (!used[i]) {
            numList.add(i)
            used[i] = true
            solve(n, m-1, bw)
            numList.removeLast()
            used[i] = false
        }
    }
}
