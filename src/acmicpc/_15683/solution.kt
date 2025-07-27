package acmicpc._15683

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

val cameraList = mutableListOf<Pair<Int, Int>>()
var cnt = Int.MAX_VALUE
fun main() {
    readWriteWithBuffer { br, bw ->
        val (n, m) = br.readLine().split(" ").map { it.toInt() }

        val board = Array(n) { CharArray(m) }

        for (i in 0 until n) {
            board[i] = br.readLine().replace(" ", "").toCharArray()

            board[i].forEachIndexed { index, c ->
                if (c != '0' && c != '6') {
                    cameraList.add(Pair(i, index))
                }
            }
        }

//        solve(0, board)

        solve2(board)

        bw.write(cnt.toString())
    }
}

val cameraDirList = listOf(
    // 1
    listOf(
        listOf(Pair(1, 0)),
        listOf(Pair(-1, 0)),
        listOf(Pair(0, 1)),
        listOf(Pair(0, -1))
    ),

    // 2
    listOf(
        listOf(Pair(1, 0), Pair(-1, 0)),
        listOf(Pair(0, 1), Pair(0, -1))
    ),

    // 3
    listOf(
        listOf(Pair(-1, 0), Pair(0, 1)),
        listOf(Pair(1, 0), Pair(0, 1)),
        listOf(Pair(-1, 0), Pair(0, -1)),
        listOf(Pair(1, 0), Pair(0, -1))
    ),

    // 4
    listOf(
        listOf(Pair(-1, 0), Pair(0, 1), Pair(0, -1)),
        listOf(Pair(1, 0), Pair(0, 1), Pair(0, -1)),

        listOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1)),
        listOf(Pair(1, 0), Pair(-1, 0), Pair(0, -1))
    ),

    // 5
    listOf(
        listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))
    )
)
fun solve(cameraIdx: Int, board: Array<CharArray>) {
    if (cameraIdx == cameraList.size) {
        cnt = minOf(cnt, checkBoard(board))
        return
    }

    val camera = cameraList[cameraIdx].run {
        board[first][second]
    }

    cameraDirList[camera.code - 48 - 1].forEach { dirList ->
        var nextBoard = board.deepCopy()
        dirList.forEach { dir ->
            nextBoard = updateBoard(cameraList[cameraIdx], dir, nextBoard)
        }
        solve(cameraIdx+1, nextBoard)
    }
}

fun Array<CharArray>.deepCopy(): Array<CharArray> {
    return Array(this.size) {
        this[it].copyOf()
    }
}

fun checkBoard(board: Array<CharArray>): Int {
//    println(board.joinToString(separator = "\n") { it.joinToString(separator = " ") })
//    println()
    return board.sumOf { it.count { c -> c == '0' } }
}

fun updateBoard(src: Pair<Int, Int>, dir: Pair<Int, Int>, board: Array<CharArray>): Array<CharArray> {
    val ret = board.deepCopy()

    var ptr = src
    while (ptr.isInBound(ret.size, ret[0].size)) {
        if (ret[ptr.first][ptr.second] == '0') {
            ret[ptr.first][ptr.second] = '#'
        } else if (ret[ptr.first][ptr.second] == '6') {
            break
        }

        ptr += dir
    }

    return ret
}



val del = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))
fun getDel(idx: Int): Pair<Int, Int> {
    return del[idx % 4]
}
var board2 = arrayOf(charArrayOf())
fun solve2(board: Array<CharArray>) {
    // 카메라는 최대 4개의 방향을 가질수 있으므로 4^(cameraSize) 번 시뮬레이션을 해야함
    // 1 shl 2 = 2^2 = 4, 1 shl (2 * 2) = 2^4 = 16 이므로 1 shl (2 * cameraSize)번 진행함
    for (tmp in 0 until (1 shl (2 * cameraList.size))) {
        board2 = board.deepCopy()

        var brute = tmp

        cameraList.forEach {
            val dir = brute % 4
            brute /= 4

            when (board[it.first][it.second]) {
                '1' -> {
                    updateBoard2(it, getDel(dir))
                }
                '2' -> {
                    updateBoard2(it, getDel(dir))
                    updateBoard2(it, getDel(dir + 2))
                }
                '3' -> {
                    updateBoard2(it, getDel(dir))
                    updateBoard2(it, getDel(dir + 1))
                }
                '4' -> {
                    updateBoard2(it, getDel(dir))
                    updateBoard2(it, getDel(dir + 1))
                    updateBoard2(it, getDel(dir + 2))
                }
                else -> {
                    updateBoard2(it, getDel(dir))
                    updateBoard2(it, getDel(dir + 1))
                    updateBoard2(it, getDel(dir + 2))
                    updateBoard2(it, getDel(dir + 3))
                }
            }
        }

        cnt = minOf(cnt, checkBoard(board2))
    }
}

fun updateBoard2(src: Pair<Int, Int>, dir: Pair<Int, Int>) {
    var ptr = src
    while (ptr.isInBound(board2.size, board2[0].size)) {
        if (board2[ptr.first][ptr.second] == '0') {
            board2[ptr.first][ptr.second] = '#'
        } else if (board2[ptr.first][ptr.second] == '6') {
            break
        }

        ptr += dir
    }
}
