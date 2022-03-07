package programmers.파괴되지_않은_건물

class Solution {
    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
        val n = board.size
        val m = board[0].size
        val arr = Array<IntArray>(n) { IntArray(m) }

        skill.forEach {
            val num = it[5]*(if(it[0]==1) -1 else 1)

            arr[it[1]][it[2]] += num

            if(it[3]+1 < n) {
                arr[it[3]+1][it[2]] -= num
            }

            if(it[4]+1 < m) {
                arr[it[1]][it[4]+1] -= num
            }

            if(it[3]+1 < n && it[4]+1 < m) {
                arr[it[3]+1][it[4]+1] += num
            }
        }

        for(r in 0 until n) {
            for(c in 1 until m) {
                arr[r][c] += arr[r][c-1]
            }
        }

        // added.forEach {
        //     println("${it.joinToString()}")
        // }
        // println("=================")

        for(c in 0 until m) {
            for(r in 1 until n) {
                arr[r][c] += arr[r-1][c]
            }
        }

        // added.forEach {
        //     println("${it.joinToString()}")
        // }

        var answer: Int = 0

        for(r in 0 until n) {
            for(c in 0 until m) {
                if(board[r][c]+arr[r][c] > 0) answer++
            }
        }


        return answer
    }
}