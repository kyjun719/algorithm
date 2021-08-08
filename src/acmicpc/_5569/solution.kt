package acmicpc._5569

val cache = Array(101) { Array(101){ IntArray(5) { -1 } } }
const val C = -1
const val R = 1
var w = 0
var h = 0
fun main() {
    val tmp = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    w = tmp[0]
    h = tmp[1]
    println(solve(0,0,0))
}

fun solve(y: Int, x: Int, leftTurn: Int): Int {
//    println("$y $x >> $leftTurn")
    if(y == h-1 && x == w-1) {
        return 1
    }

    //val dir = if(leftTurn > 0) 1 else 0
    val dir = leftTurn+2

    if(cache[y][x][dir] != -1) {
        return cache[y][x][dir]
    }

    var ret = 0
    if((y+1 < h && leftTurn >= 0) || (leftTurn == -1)) {
        val left = when(leftTurn) {
            0 -> 1
            -1 -> 2
            else -> maxOf(leftTurn-R, 1)
        }
        ret = solve(y+1,x, left)
    }
    if((x+1 < w && leftTurn <= 0) || (leftTurn == 1)) {
        val left = when(leftTurn) {
            0 -> -1
            1 -> -2
            else -> minOf(leftTurn-C, -1)
        }
            //if(leftTurn == 0) -1 else minOf(leftTurn-C, 0)
        ret += solve(y,x+1, left)
    }
    ret %= 100000
    cache[y][x][dir] = ret
    return ret
}