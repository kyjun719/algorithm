package acmicpc._4179

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
    return this.first in 0..< maxFirst &&
            this.second in 0 ..< maxSecond
}


data class Point(
    val ptr: Pair<Int, Int>,
    val moved: Int
)

fun main() {
    readWriteWithBuffer { br, bw ->
        val (r, c) = br.readLine().split(" ").map { it.toInt() }

        val board = Array(r) { CharArray(c) }
        val visited = Array(r) { BooleanArray(c) }

        for (i in 0 until r) {
            board[i] = br.readLine().toCharArray()
        }

        val del = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))
        val movedQueue = ArrayDeque<Point>()
        val fireQueue = ArrayDeque<Pair<Int, Int>>()

        for (y in 0 until r) {
            for (x in 0 until c) {
                if (board[y][x] == 'J') {
                    movedQueue.add(Point(Pair(y, x), 0))
                    board[y][x] = '.'
                    visited[y][x] = true
                } else if (board[y][x] == 'F') {
                    fireQueue.add(Pair(y, x))
                }
            }
        }

        var cnt = -1

        while (movedQueue.isNotEmpty()) {
            val nextFireList = mutableListOf<Pair<Int, Int>>()

            while (fireQueue.isNotEmpty()) {
                val fire = fireQueue.removeFirst()

                del.forEach {
                    (fire + it).run {
                        if (isInBound(r, c) && board[first][second] == '.') {
                            board[first][second] = 'F'
                            nextFireList.add(this)
                        }
                    }
                }
            }
            fireQueue.addAll(nextFireList)

            val nextMovedList = mutableListOf<Point>()

            while (movedQueue.isNotEmpty()) {
                val moved = movedQueue.removeFirst()

                del.forEach {
                    (moved.ptr + it).run {
                        if (!isInBound(r, c)) {
                            cnt = moved.moved + 1
                            return@forEach
                        }

                        if (isInBound(r, c) && !visited[first][second] && board[first][second] == '.') {
                            nextMovedList.add(Point(this, moved.moved + 1))
                            visited[first][second] = true
                        }
                    }
                }

                if (cnt != -1) break
            }

            if (cnt != -1) break

            movedQueue.addAll(nextMovedList)
        }

        if (cnt == -1) {
            bw.write("IMPOSSIBLE")
        } else {
            bw.write(cnt.toString())
        }
    }
}