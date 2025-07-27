package acmicpc._2178

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

private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(first + other.first, second + other.second)
}

private fun Pair<Int, Int>.isInBound(maxFirst: Int, maxSecond: Int): Boolean {
    return first in 0..< maxFirst &&
            second in 0 ..< maxSecond
}

fun main() {
    readWriteWithBuffer { br, bw ->
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val board = Array(n) { IntArray(m) { -1 } }
        val dist = Array(n) { IntArray(m) { -1 } }

        for (i in 0 until n) {
            board[i] = br.readLine().map { it.code - 48 }.toIntArray()
        }
        val del = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))

        val q = ArrayDeque<Pair<Int, Int>>()

        q.add(Pair(0, 0))
        dist[0][0] = 1

        while (q.isNotEmpty()) {
            val ptr = q.removeFirst()

            del.forEach {
                (ptr + it).run {
                    if (this.isInBound(n, m) && board[first][second] == 1) {
                        if (dist[first][second] == -1) {
                            dist[first][second] = dist[ptr.first][ptr.second] + 1
                            q.add(this)
                        }
                    }
                }
            }
        }

        bw.write(dist[n - 1][m - 1].toString())
        bw.newLine()
    }
}
