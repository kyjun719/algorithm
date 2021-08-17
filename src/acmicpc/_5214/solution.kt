package acmicpc._5214

lateinit var arr: Array<MutableList<Int>>
var l = 0
var n = 0
fun main() {
    var tmp = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    n = tmp[0]
    l = tmp[2]
    arr = Array(n+1+1000) { mutableListOf() }
    for(line in 0 until l) {
        tmp = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        for(i in 0 until tmp.size) {
            arr[tmp[i]].add(line+n+1)
            arr[line+n+1].add(tmp[i])
        }
    }

    println(solve(1,n))
}

fun solve(s: Int, e: Int): Int {
    val q = mutableListOf<Int>()
    val dist = IntArray(n+1+1000)
    dist[s] = 1
    q.add(s)

    while(q.isNotEmpty()) {
        val now = q.removeFirst()
        //println(now)
        if(now == e) {
            return dist[e]
        }

        arr[now].forEach {
            if (dist[it]==0) {
                dist[it] = if(it>=(n+1)) dist[now] else dist[now]+1
                q.add(it)
            }
        }
    }
    return -1
}
