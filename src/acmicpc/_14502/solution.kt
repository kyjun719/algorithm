package acmicpc._14502

var n = 0
var m = 0
var zeroCnt = 0
fun main() {
    val tmp = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    n = tmp[0]
    m = tmp[1]
    val board = Array(n) { IntArray(m) }
    for(i in 0 until n) {
        board[i] = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        zeroCnt += board[i].count { it==0 }
    }
    println(solve(-1, 0, board))
}

fun solve(ptr: Int, cnt: Int, board: Array<IntArray>): Int {
    if(cnt == 3) {
        return calc(board)
    }

    if(ptr >= n*m) {
        return 0
    }

    var ret = 0
    for(i in ptr+1 until n*m) {
        val y = i/m
        val x = i%m
        if(board[y][x] != 0) {
            continue
        }

        board[y][x] = 1
        ret = maxOf(ret, solve(i,cnt+1,board))
        board[y][x] = 0
    }
    return ret
}

val d = listOf(Pair(0,-1), Pair(0,1), Pair(-1,0), Pair(1,0))
fun calc(board: Array<IntArray>): Int {
    val checked = Array(n) { BooleanArray(m) }
    var ret = zeroCnt-3
    val calcBoard = Array(n) { board[it].copyOf() }
    for(y in 0 until n) {
        for(x in 0 until m) {
            if(calcBoard[y][x] == 2 && !checked[y][x]) {
                val list = mutableListOf<Pair<Int, Int>>()
                list.add(Pair(y,x))
                checked[y][x] = true
                while(list.isNotEmpty()) {
                    val now = list.removeAt(0)
                    d.forEach {
                        val ny = now.first+it.first
                        val nx = now.second+it.second
                        if((ny in 0 until n) && (nx in 0 until m) && calcBoard[ny][nx]==0) {
                            calcBoard[ny][nx] = 2
                            checked[ny][nx] = true
                            list.add(Pair(ny,nx))
                            ret-=1
                        }
                    }
                }
            }
        }
    }
//    println("================")
//    calcBoard.forEach { println(it.joinToString()) }
//    println(ret)
//    println("================")
    return ret
}