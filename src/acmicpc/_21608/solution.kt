package acmicpc._21608

import java.util.*

var n = 0
lateinit var emptyCntArr: Array<IntArray>
lateinit var board: Array<IntArray>
lateinit var pointArr: Array<Pair<Int, Int>?>
lateinit var infoArr: Array<BooleanArray>
fun main() {
    n = readLine()!!.toInt()
    emptyCntArr = Array(n) { IntArray(n) { 4 } }
    for(i in 1 until n-1) {
        emptyCntArr[0][i] = 3
        emptyCntArr[n-1][i] = 3
        emptyCntArr[i][0] = 3
        emptyCntArr[i][n-1] = 3
    }
    emptyCntArr[0][0] = 2
    emptyCntArr[0][n-1] = 2
    emptyCntArr[n-1][0] = 2
    emptyCntArr[n-1][n-1] = 2

    infoArr = Array(n*n) { BooleanArray(n*n) }
    pointArr = Array(n*n) { null }
    board = Array(n) { IntArray(n) { -1 } }

    for(i in 0 until (n*n)) {
        val tokenizer = StringTokenizer(readLine())
        var tmpBoard = Array(n) { IntArray(n) }
        val idx = tokenizer.nextToken().toInt()-1
        for(j in 1..4) {
            val dest = tokenizer.nextToken().toInt()-1
            infoArr[idx][dest] = true
            pointArr[dest]?.let { ptr ->
                ptrd.forEach {
                    val dy = ptr.first+it.first
                    val dx = ptr.second+it.second
                    if((dy in 0 until n) && (dx in 0 until n) && board[dy][dx]==-1) {
                        tmpBoard[dy][dx] += 1
                    }
                }
            }
        }

        var cnt = 0
        var best = Pair(0,0)
        var empty = 0
        var emptyCnt = -1
        var bestEmpty = Pair(0,0)
        for(y in 0 until n) {
            for(x in 0 until n) {
                if(tmpBoard[y][x] > cnt) {
                    best = Pair(y,x)
                    cnt = tmpBoard[y][x]
                    empty = emptyCntArr[y][x]
                }
                if((tmpBoard[y][x] == cnt) && (empty < emptyCntArr[y][x])) {
                    best = Pair(y,x)
                    empty = emptyCntArr[y][x]
                }

                if(emptyCntArr[y][x] > emptyCnt && board[y][x]==-1) {
                    bestEmpty = Pair(y,x)
                    emptyCnt = emptyCntArr[y][x]
                }
            }
        }

        if(cnt == 0) best = bestEmpty

        emptyCntArr[best.first][best.second] = 0
        ptrd.forEach {
            val dy = best.first+it.first
            val dx = best.second+it.second
            if((dy in 0 until n) && (dx in 0 until n)) {
                emptyCntArr[dy][dx] = maxOf(0, emptyCntArr[dy][dx]-1)
            }
        }
        pointArr[idx] = best
        board[best.first][best.second] = idx
        //printBoard(board)
    }

    var cnt = 0
    for(y in 0 until n) {
        for(x in 0 until n) {
            var num = 0
            var idx = board[y][x]
            ptrd.forEach {
                val dy = y+it.first
                val dx = x+it.second
                if((dy in 0 until n) && (dx in 0 until n)) {
                    if(infoArr[idx][board[dy][dx]]) {
                        num += 1
                    }
                }
            }
            cnt += when(num) {
                0 -> 0
                1 -> 1
                2 -> 10
                3 -> 100
                else -> 1000
            }
        }
    }
    println(cnt)
}

fun printBoard(board: Array<IntArray>) {
    board.forEach {
        println(it.joinToString())
    }
    println("=============")
}

val ptrd = arrayOf(Pair(0,1), Pair(0,-1), Pair(1,0), Pair(-1,0))
