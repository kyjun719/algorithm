package acmicpc._20437

fun main(){
    var t = readLine()!!.toInt()
    while(t-- > 0) {
        val str = readLine()!!
        val k = readLine()!!.toInt()
        val idxListArr = Array<MutableList<Int>>(26) { mutableListOf() }
        str.forEachIndexed { index, c ->
            idxListArr[c-'a'].add(index)
        }

        var minLen = 987654321
        var maxLen = -1
        idxListArr.forEach {
            for(i in 0 until it.size-k+1) {
                minLen = minOf(minLen, it[i+k-1]-it[i]+1)
                maxLen = maxOf(maxLen, it[i+k-1]-it[i]+1)
            }
        }
        if(maxLen == -1) {
            println(-1)
        } else {
            println("$minLen $maxLen")
        }
    }
}
