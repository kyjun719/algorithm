package acmicpc._14500

val blockList = mutableListOf<Array<Pair<Int, Int>>>()
var n = 0
var m = 0
lateinit var board: Array<IntArray>
fun main() {
    init()
    var tmp = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    n = tmp[0]
    m = tmp[1]
    board = Array(n) { IntArray(m) }
    for(i in 0 until n) {
        board[i] = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    }
    println(solve())
}

fun solve(): Int {
    var ret = 0
    val checked = Array(n) { IntArray(m){-1} }
    for(y in 0 until n) {
        for(x in 0 until m) {
            blockList.forEach {
                ret = maxOf(ret, calcBlock(y,x,it))
            }
        }
    }

    return ret
}

fun calcBlock(y: Int, x: Int, point: Array<Pair<Int, Int>>): Int {
    var sum = 0
    point.forEach {
        var ny = y+it.first
        var nx = x+it.second
        if(ny>=n || nx>=m || ny<0 || nx<0) return 0
        sum += board[ny][nx]
    }
    return sum
}

fun init() {
    blockList.add(arrayOf(Pair(0,0), Pair(0,1), Pair(0,2), Pair(0,3)))
    blockList.add(arrayOf(Pair(0,0), Pair(1,0), Pair(2,0), Pair(3,0)))

    blockList.add(arrayOf(Pair(0,0), Pair(0,1), Pair(1,1), Pair(1,0)))

    blockList.add(arrayOf(Pair(0,0), Pair(1,0), Pair(2,0), Pair(2,1)))
    blockList.add(arrayOf(Pair(0,0), Pair(0,1), Pair(0,2), Pair(1,0)))
    blockList.add(arrayOf(Pair(0,0), Pair(0,1), Pair(1,1), Pair(2,1)))
    blockList.add(arrayOf(Pair(0,0), Pair(0,1), Pair(0,2), Pair(-1,2)))

    blockList.add(arrayOf(Pair(0,0), Pair(1,0), Pair(2,0), Pair(2,-1)))
    blockList.add(arrayOf(Pair(0,0), Pair(1,0), Pair(1,1), Pair(1,2)))
    blockList.add(arrayOf(Pair(0,0), Pair(0,1), Pair(1,0), Pair(2,0)))
    blockList.add(arrayOf(Pair(0,0), Pair(0,1), Pair(0,2), Pair(1,2)))

    blockList.add(arrayOf(Pair(0,0), Pair(1,0), Pair(1,1), Pair(2,1)))
    blockList.add(arrayOf(Pair(0,0), Pair(0,1), Pair(-1,1), Pair(-1,2)))
    blockList.add(arrayOf(Pair(0,0), Pair(1,0), Pair(1,-1), Pair(2,-1)))
    blockList.add(arrayOf(Pair(0,0), Pair(0,1), Pair(1,1), Pair(1,2)))

    blockList.add(arrayOf(Pair(0,0), Pair(0,1), Pair(0,2), Pair(1,1)))
    blockList.add(arrayOf(Pair(0,0), Pair(1,0), Pair(2,0), Pair(1,-1)))
    blockList.add(arrayOf(Pair(0,0), Pair(0,1), Pair(0,2), Pair(-1,1)))
    blockList.add(arrayOf(Pair(0,0), Pair(1,0), Pair(2,0), Pair(1,1)))
}