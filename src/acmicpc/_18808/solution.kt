package acmicpc._18808

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
//        solve1(br, bw)
        solve2(br, bw)
    }
}

// region solve1
private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(first + other.first, second + other.second)
}
private operator fun Pair<Int, Int>.minus(other: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(first - other.first, second - other.second)
}

private fun Pair<Int, Int>.isInBound(maxFirst: Int, maxSecond: Int): Boolean {
    return this.first in 0..< maxFirst &&
            this.second in 0 ..< maxSecond
}

data class Sticker(
    val r: Int,
    val c: Int,
    val block: Array<IntArray>
) {
    /**
     * 가장위의 맨 왼쪽점을 기준으로 상대적인 위치를 가진 점 리스트 반환
     */
    fun blockToPtrList(): List<Pair<Int, Int>> {
        val list = mutableListOf<Pair<Int, Int>>()
        val visited = Array(block.size) { BooleanArray(block[0].size) }

        var startPtr = Pair(-1, -1)

        run {
            for (y in 0 until block.size) {
                for (x in 0 until block[0].size) {
                    if (block[y][x] == 1) {
                        startPtr = Pair(y, x)
                        visited[y][x] = true
                        return@run
                    }
                }
            }
        }

        val q = ArrayDeque<Pair<Int, Int>>()
        q.add(startPtr)
        list.add(Pair(0, 0))
        while (q.isNotEmpty()) {
            val ptr = q.removeFirst()

            listOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0)).forEach {
                (ptr + it).run {
                    if (isInBound(block.size, block[0].size) && !visited[first][second] && block[first][second] == 1) {
                        list.add(this - startPtr)
                        visited[first][second] = true
                        q.add(this)
                    }
                }
            }
        }

        return list
    }
}
val stickerList = mutableListOf<Sticker>()
var board = arrayOf(intArrayOf())

fun solve1(br: BufferedReader, bw: BufferedWriter) {
    val (n, m, k) = br.readLine().split(" ").map { it.toInt() }

    for (i in 0 until k) {
        val (r, c) = br.readLine().split(" ").map { it.toInt() }
        val maxSize = maxOf(r, c)
        val block = Array(maxSize) { IntArray(maxSize) }

        for (y in 0 until r) {
            block[y] = br.readLine().split(" ").map { it.toInt() }.toIntArray().copyOf(maxSize)
        }

        stickerList.add(Sticker(r, c, block))
    }

    board = Array(n) { IntArray(m) }

    stickerList.forEach {
        val sticker = it

        run {
            for (i in 0 until 4) {
                for (y in 0 until n) {
                    for (x in 0 until m) {
                        if (canUsed(y, x, sticker)) {
                            setSticker(y, x, sticker)
                            return@run
                        }
                    }
                }

                rotateMatrix(sticker.block)
            }
        }
    }

    bw.write(board.sumOf { it.sum() }.toString())
}

fun printBoard(arr: Array<IntArray>) {
    println(arr.joinToString(separator = "\n") { it.joinToString(separator = " ") })
    println()
}

fun canUsed(y: Int, x: Int, sticker: Sticker): Boolean {
    sticker.blockToPtrList().forEach {
        val tmpPtr = Pair(y, x) + it

        if (!tmpPtr.isInBound(board.size, board[0].size)) return false

        if (board[tmpPtr.first][tmpPtr.second] != 0) return false
    }

    return true
}

fun setSticker(y: Int, x: Int, sticker: Sticker) {
    sticker.blockToPtrList().forEach {
        val tmpPtr = Pair(y, x) + it
        board[tmpPtr.first][tmpPtr.second] = 1
    }
}


fun rotateMatrix(matrix: Array<IntArray>) {
    val n = matrix.size

    // 행렬 전치(transpose)
    for (i in 0 until n) {
        for (j in i until n) {
            val temp = matrix[i][j]
            matrix[i][j] = matrix[j][i]
            matrix[j][i] = temp
        }
    }

    // 각 행 뒤집기
    for (i in 0 until n) {
        for (j in 0 until n / 2) {
            val temp = matrix[i][j]
            matrix[i][j] = matrix[i][n - j - 1]
            matrix[i][n - j - 1] = temp
        }
    }
}
// endregion

// region solve2
data class Sticker2(
    val r: Int,
    val c: Int,
    val paper: Array<IntArray>
) {
    override fun toString(): String {
        return "r=$r, c=$c, paper=\n${paper.joinToString(separator = "\n") { it.joinToString(separator = " ") }}"
    }
}

fun solve2(br: BufferedReader, bw: BufferedWriter) {
    val (n, m, k) = br.readLine().split(" ").map { it.toInt() }

    var note = Array(n) { IntArray(m) }

    for (i in 0 until k) {
        val (r, c) = br.readLine().split(" ").map { it.toInt() }

        val paper = Array(11) { IntArray(11) }

        for (y in 0 until r) {
            paper[y] = br.readLine().split(" ").map { it.toInt() }.toIntArray().copyOf(11)
        }

        var sticker = Sticker2(r,c,paper)

        for (z in 0 until 4) {
            var isPaste = false

            for (y in 0 .. n-sticker.r) {
                for (x in 0 .. m-sticker.c) {
                    val result = pastable(sticker, note, x, y)

                    if (result.first) {
                        isPaste = true
                        note = result.second
                        break
                    }
                }
                if (isPaste) break
            }
            if (isPaste) break
            sticker = rotate(sticker)
        }
    }

    var ret = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            ret += note[i][j]
        }
    }
    println(ret)
}

fun pastable(sticker: Sticker2, note: Array<IntArray>, x: Int, y: Int): Pair<Boolean, Array<IntArray>> {
    for (i in 0 until sticker.r) {
        for (j in 0 until sticker.c) {
            if (note[y+i][x+j] == 1 && sticker.paper[i][j] == 1) {
                return Pair(false, note)
            }
        }
    }

    for (i in 0 until sticker.r) {
        for (j in 0 until sticker.c) {
            if (sticker.paper[i][j] == 1) {
                note[y+i][x+j] = 1
            }
        }
    }

    return Pair(true, note)
}

fun rotate(sticker: Sticker2): Sticker2 {
//    val tmp = sticker.paper.copyOf()
    val tmp = Array(11) { IntArray(11) }
    val r = sticker.r
    val c = sticker.c

    for (i in 0 until c) {
        for (j in 0 until r) {
            tmp[i][j] = sticker.paper[r-1-j][i]
        }
    }

    return Sticker2(c, r, tmp)
}
// endregion
