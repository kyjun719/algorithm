package programmers.최소직사각형

class Solution {
    fun solution(sizes: Array<IntArray>): Int {
        var a = 0
        var b = 0
        sizes.map {
            if(it[1] < it[0]) intArrayOf(it[1], it[0]) else it
        }.forEach {
            a = maxOf(a, it[0])
            b = maxOf(b, it[1])
        }
        return a*b
    }
}