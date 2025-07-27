package acmicpc._1697

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


fun main() {
    readWriteWithBuffer { br, bw ->
        val (n, k) = br.readLine().split(" ").map { it.toInt() }

        val q = ArrayDeque<Pair<Int, Int>>()
        val visited = BooleanArray(200_000)

        q.add(Pair(n, 0))
        visited[n] = true

        while (q.isNotEmpty()) {
            val (ptr, cnt) = q.removeFirst()

            if (ptr == k) {
                bw.write(cnt.toString())
                break
            }

            listOf(ptr + 1, ptr - 1, ptr * 2).forEach {
                if (it in 0..199_999 && !visited[it]) {
                    visited[it] = true
                    q.add(Pair(it, cnt + 1))
                }
            }
        }
    }
}