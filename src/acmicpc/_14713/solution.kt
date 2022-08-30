package acmicpc._14713

fun main() {
    val n = readLine()!!.toInt()
    val arr = Array<Array<String>>(n) { arrayOf() }
    // word : idx, arrIdx
    val wordMap = hashMapOf<String, Pair<Int, Int>>()
    for(i in 0 until n) {
        arr[i] = readLine()!!.split(" ").toTypedArray()
        wordMap[arr[i][0]] = Pair(i, 0)
    }

    val str = readLine()!!.split(" ").toTypedArray()
    str.forEach { s ->
        wordMap[s]?.let {
            wordMap.remove(s)
            if(it.second+1 < arr[it.first].size) {
                wordMap[arr[it.first][it.second+1]] = Pair(it.first, it.second+1)
            }
        } ?: run {
            println("Impossible")
            return
        }
    }
    if(wordMap.isNotEmpty()) {
        println("Impossible")
        return
    }
    println("Possible")
}