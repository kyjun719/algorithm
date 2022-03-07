package programmers.피로도

class Solution {
    var answer = 0
    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        solve(k, dungeons, BooleanArray(dungeons.size), 0)
        return answer
    }

    fun solve(k: Int, dungeons: Array<IntArray>, visited: BooleanArray, cnt: Int) {
        for(i in 0 until dungeons.size) {
            if(!visited[i] && k>=dungeons[i][0]) {
                visited[i] = true
                solve(k-dungeons[i][1], dungeons, visited, cnt+1)
                visited[i] = false
            }
        }

        answer = maxOf(answer, cnt)
    }
}