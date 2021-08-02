package acmicpc._14499

lateinit var board: Array<IntArray>
//u:0, s:1, e:2, w:3, n:4, d:5
object DIR {
    const val U = 0
    const val S = 1
    const val E = 2
    const val W = 3
    const val N = 4
    const val D = 5
}
var dice = IntArray(6) { 0 }
var x = 0
var y = 0
fun main() {
    val tmp = readLine()!!.split(" ").map { it.toInt() }.toTypedArray()
    board = Array(tmp[0]) { IntArray(tmp[1]) }
    for(i in 0 until tmp[0]) {
        board[i] = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    }
    x = tmp[3]
    y = tmp[2]
    val cmd = readLine()!!.split(" ").map { it.toInt() }.toTypedArray()
    for(c in cmd) {
        if(isOver(c,tmp[0],tmp[1])) {
            continue
        }
        move(c)
        println(dice[0])
    }
}

val dx = intArrayOf(1,-1,0,0)
val dy = intArrayOf(0,0,-1,1)
fun isOver(c: Int, yy: Int, xx: Int): Boolean {
    val nx = (x+dx[c-1])
    val ny = (y+dy[c-1])
    return !((nx in 0 until xx) && (ny in 0 until yy))
}

fun move(c: Int) {
    val tmp = dice[DIR.U]
    when(c) {
        1 -> {
            dice[DIR.U] = dice[DIR.W]
            dice[DIR.W] = dice[DIR.D]
            dice[DIR.D] = dice[DIR.E]
            dice[DIR.E] = tmp
        }
        2 -> {
            dice[DIR.U] = dice[DIR.E]
            dice[DIR.E] = dice[DIR.D]
            dice[DIR.D] = dice[DIR.W]
            dice[DIR.W] = tmp
        }
        3 -> {
            dice[DIR.U] = dice[DIR.S]
            dice[DIR.S] = dice[DIR.D]
            dice[DIR.D] = dice[DIR.N]
            dice[DIR.N] = tmp
        }
        4 -> {
            dice[DIR.U] = dice[DIR.N]
            dice[DIR.N] = dice[DIR.D]
            dice[DIR.D] = dice[DIR.S]
            dice[DIR.S] = tmp
        }
    }
    x += dx[c-1]
    y += dy[c-1]
    //println("$y $x")
    if(board[y][x] != 0) {
        dice[DIR.D] = board[y][x]
        board[y][x] = 0
    } else {
        board[y][x] = dice[DIR.D]
    }
}