package acmicpc._5052

class Trie {
    var isFinish = false
    val ch = Array<Trie?>(26) { null }
}
fun main() {
    val t = readLine()!!.toInt()
    for(tc in 0 until t) {
        val n = readLine()!!.toInt()
        val trie = Trie()
        var ret = true
        val list = mutableListOf<String>()
        for(i in 0 until n) {
            list.add(readLine()!!)
        }
        list.sortBy { it.length }
        list.forEach { s ->
            var now = trie
            s.map { it-'0' }.forEach {
                if(now.isFinish) {
                    ret = false
                    return@forEach
                }
                if(now.ch[it] == null) now.ch[it] = Trie()
                now = now.ch[it]!!
            }
            now.isFinish = true
        }

        println(if(ret) "YES" else "NO")
    }
}