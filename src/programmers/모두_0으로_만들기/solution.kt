class Solution {
    var adj =  Array<ArrayList<Int>>(300_000) {ArrayList<Int>()}
    val arr = Array<Long>(300_000){0}

    fun solution(a: IntArray, edges: Array<IntArray>): Long {
        var answer: Long = 0
        var n = a.size
        for(i in 0..n-1) {
            arr[i] = a[i].toLong()
        }

        edges.map{ adj[it[0]].add(it[1]); adj[it[1]].add(it[0]) }
        var sum = 0L
        arr.map{ sum+=it }
        if(sum!=0L) {
            return -1
        }

        return dfs(-1,0)
    }

    fun dfs(bef: Int, now: Int): Long {
        var ret = 0L
        for(i in 0..adj[now].size-1) {
            var next = adj[now][i]
            if(next == bef) {
                continue
            }
            ret += dfs(now, next)
        }
        if(bef != -1) {
            arr[bef] += arr[now]
        }
        return ret + Math.abs(arr[now])
    }
}