package acmicpc._1926

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

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

        for (i in 0 until n) {
            board[i] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
        }
        var cnt = 0
        var maxSize = 0
        val del = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))

        for (y in 0 until n) {
            for (x in 0 until m) {
                if (board[y][x] == 1) {
                    cnt++

                    val q = ArrayDeque<Pair<Int, Int>>()

                    q.add(Pair(y, x))
                    var size = 1
                    board[y][x] = 0

                    while (q.isNotEmpty()) {
                        val ptr = q.removeFirst()

                        del.forEach {
                            (ptr + it).run {
                                if (this.isInBound(n, m) && board[first][second] == 1) {
                                    size++
                                    q.add(this)
                                    board[first][second] = 0
                                }
                            }
                        }
                    }

                    maxSize = max(maxSize, size)
                }
            }
        }

        bw.write(cnt.toString())
        bw.newLine()
        bw.write(maxSize.toString())
    }
}
