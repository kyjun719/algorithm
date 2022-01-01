package acmicpc._2294

fun main(){
    val t = readLine()!!.split(" ")
    val n = t[0].toInt()
    val k = t[1].toInt()
    val cntCache = IntArray(k+1) { -1 }

    val list = mutableListOf<Int>()
    for(i in 0 until n) {
        list.add(readLine()!!.toInt())
    }

    val idxList = mutableListOf<Int>()
    idxList.add(0)
    cntCache[0] = 0
    while(idxList.isNotEmpty()) {
        val now = idxList.removeAt(0)
        val nowCnt = cntCache[now]

        list.forEach {
            if(now + it <= k) {
                val next = now+it
                val nextCnt = cntCache[next]
                if(nextCnt==-1 || nextCnt > nowCnt+1) {
                    cntCache[next] = nowCnt+1
                    idxList.add(next)
                }
            }
        }
    }

    println(cntCache[k])
}