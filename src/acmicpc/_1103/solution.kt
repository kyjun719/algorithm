package acmicpc._1103

lateinit var visited : Array<BooleanArray>
lateinit var arr : Array<IntArray>
lateinit var cache : Array<IntArray>
var n = 0
var m = 0
val INF = 987654321
val dirArr = arrayOf(intArrayOf(1,0), intArrayOf(-1,0), intArrayOf(0,1), intArrayOf(0,-1))
fun main() {
    val tmp = readLine()!!.split(" ")
    n = tmp[0].toInt()
    m = tmp[1].toInt()
    visited = Array<BooleanArray>(n) { BooleanArray(m) }
    arr = Array<IntArray>(n){ IntArray(m) }
    cache = Array<IntArray>(n){ IntArray(m) }
    for(i in 0..n-1) {
        arr[i] = readLine()!!.map { if(it == 'H') INF else it-'0' }.toIntArray()
    }
    visited[0][0] = true

    val ret = solve(0,0)
    println(if(ret==INF)-1 else ret)
}

fun solve(x: Int, y: Int): Int {
    if(cache[y][x] != 0) {
        return cache[y][x]
    }
    var del = arr[y][x]
    var ret = 1
    for(dir in dirArr) {
        var nx = x+del*dir[0]
        var ny = y+del*dir[1]
        //println("${dir.joinToString()} >> $ny,$nx")
        if(canGo(nx, ny)) {
            if(visited[ny][nx]) {
                ret = INF
                break
            } else {
                visited[ny][nx] = true
                var tmp = solve(nx,ny)
                visited[ny][nx] = false
                if(tmp == INF) {
                    ret = INF
                    break
                } else {
                    ret = maxOf(ret, tmp+1)
                }
            }
        }
    }
    cache[y][x] = ret
    return ret
}

fun canGo(x: Int, y: Int) : Boolean {
    return (x in 0 until m) && (y in 0 until n) && arr[y][x]!=INF
}