package programmers.전력망을_둘로_나누기

class Solution {
    lateinit var adj: Array<BooleanArray>
    lateinit var visited: IntArray
    fun solution(n: Int, wires: Array<IntArray>): Int {
        adj = Array(n){ BooleanArray(n) }
        wires.forEach {
            var a = it[0]-1
            var b = it[1]-1
            adj[a][b] = true
            adj[b][a] = true
        }
        return solve(n)
    }
    fun solve(n: Int): Int {
        var ans = n-1
        for(a in 0 until n){
            for(b in a+1 until n){
                if(!adj[a][b]) continue

                visited = IntArray(n){-1}
                adj[a][b] = false
                adj[b][a] = false
                var ac = bfs(n,a,0)
                var bc = bfs(n,b,2)
                ans = minOf(ans, Math.abs(ac-bc).toInt())
                adj[a][b] = true
                adj[b][a] = true
            }
        }
        return ans
    }
    fun bfs(n: Int, s: Int, num: Int): Int {
        var cnt = 1
        var q = mutableListOf(s)
        visited[s] = num
        while(q.size != 0){
            var a = q[0]
            q.removeAt(0)
            for(b in 0 until n){
                if(!adj[a][b]) continue
                if(visited[b]==-1){
                    visited[b]=num
                    cnt+=1
                    q.add(b)
                }
                else if(visited[b] != num){
                    return 101*(num-1)
                }
            }
        }
        return cnt
    }
}