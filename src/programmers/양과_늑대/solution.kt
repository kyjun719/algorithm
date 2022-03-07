package programmers.양과_늑대

fun main() {
    println(Solution().solution(intArrayOf(0,0,1,1,1,0,1,0,1,0,1,1),
        arrayOf(intArrayOf(0,1),intArrayOf(1,2),intArrayOf(1,4),intArrayOf(0,8),intArrayOf(8,7),intArrayOf(9,10),intArrayOf(9,11),intArrayOf(4,3),intArrayOf(6,5),intArrayOf(4,6),intArrayOf(8,9))
    ))
}

class Solution {
    private lateinit var adj: Array<MutableList<Int>>
    var ret = 0
    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        adj = Array<MutableList<Int>>(info.size) { mutableListOf() }
        edges.forEach {
            adj[it[0]].add(it[1])
        }

        dfs(info, 0, 0, 0, mutableSetOf())

        return ret
    }

    private fun dfs(info: IntArray, idx: Int, sheep: Int, wolf: Int, nextList: MutableSet<Int>) {
        val nowSheep = sheep + (info[idx] xor 1)
        val nowWolf = wolf + info[idx]

        if(nowSheep <= nowWolf) {
            return
        }

        ret = maxOf(ret, nowSheep)

        nextList.addAll(adj[idx])

        nextList.forEach {
            val set = mutableSetOf<Int>()
            set.addAll(nextList)
            set.remove(it)
            dfs(info, it, nowSheep, nowWolf, set)
        }
    }
}