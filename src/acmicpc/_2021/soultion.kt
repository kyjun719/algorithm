package acmicpc._2021

/**
 * 100%에서 실패...!;;
 */
lateinit var ptrArr: Array<MutableList<Int>>
var n = 0
var l = 0
fun main() {
    var tmp = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    n = tmp[0]
    l = tmp[1]

    ptrArr = Array(n+1+l) { mutableListOf() }
    for(line in 0 until tmp[1]) {
        tmp = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        for(i in 0 until tmp.size-1) {
            ptrArr[tmp[i]].add(line+n+1)
            ptrArr[line+n+1].add(tmp[i])
        }
    }

    tmp = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    println(solve(tmp[0], tmp[1]))
}

fun solve(s: Int, e: Int): Int {
    val q = java.util.ArrayDeque<Int>()
    val dist = IntArray(n+1+l) { -1 }
    dist[s] = 0
    for(nl in ptrArr[s]) {
        dist[nl] = 0
        q.add(nl)
    }

    while(!q.isEmpty()) {
        val now = q.poll()
        //println(now)
        if(now == e) {
//            println(dist.joinToString())
            return dist[e]
        }

        for(next in ptrArr[now]) {
            if (dist[next]==-1) {
                //dist[next] = if(next>=(n+1) && befLine!=next) dist[now]+1 else dist[now]
                dist[next] = if(next>=(n+1))dist[now]+1 else dist[now]
//                println("$next >> ${dist[next]}")
                q.add(next)
            }
        }
    }
    return -1
}