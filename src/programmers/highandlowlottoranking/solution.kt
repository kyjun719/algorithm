package programmers.highandlowlottoranking

class Solution {
    fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
        var map = hashMapOf<Int, Int>()
        var numCnt = 0
        for(i in lottos) {
            if(i == 0) {
                numCnt+=1
            } else {
                map[i] = 1
            }
        }

        var matchCnt = 0
        for(i in win_nums) {
            map[i]?.let {
                matchCnt+=1
            }
        }
        return intArrayOf(calc(matchCnt+numCnt), calc(matchCnt))
    }
    fun calc(cnt: Int) : Int {
        if(cnt >= 6) {
            return 1
        }
        return when(cnt) {
            5 -> 2
            4 -> 3
            3 -> 4
            2 -> 5
            else -> 6
        }
    }
}

fun main() {
    println(Solution().solution(intArrayOf(44, 1, 0, 0, 31, 25), intArrayOf(31, 10, 45, 1, 6, 19)).joinToString())
    println(Solution().solution(intArrayOf(0, 0, 0, 0, 0, 0), intArrayOf(38, 19, 20, 40, 15, 25)).joinToString())
    println(Solution().solution(intArrayOf(45, 4, 35, 20, 3, 9), intArrayOf(20, 9, 3, 45, 4, 35)).joinToString())
}