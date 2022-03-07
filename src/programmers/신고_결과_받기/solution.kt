package programmers.신고_결과_받기

class Solution {
    data class Reported(var cnt: Int, val list: MutableSet<String>)
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        val idxMap = mutableMapOf<String, Int>()
        id_list.forEachIndexed { index, s -> idxMap[s] = index }
        //id, pair<신고당한횟수, 신고당한목록>
        val map = mutableMapOf<String, MutableSet<String>>()

        id_list.forEach {
            map[it] = mutableSetOf()
        }

        report.forEach {
            val (a,b) = it.split(" ")
            map[b]?.add(a)
        }

        var answer = IntArray(id_list.size)

        id_list.forEach {
            map[it]?.let { set ->
                if(set.size >= k) {
                    set.forEach { s -> answer[idxMap[s]!!]++ }
                }
            }
        }

        return answer
    }
}