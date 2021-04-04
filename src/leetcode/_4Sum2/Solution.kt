fun main(args: Array<String>) {
    println(_4Sum2.Solution().fourSumCount(intArrayOf(1,2), intArrayOf(-2,-1), intArrayOf(-1,2), intArrayOf(0,2)))
}

private class _4Sum2 {
    class Solution {
        fun fourSumCount(A: IntArray, B: IntArray, C: IntArray, D: IntArray): Int {
            var map=mutableMapOf<Int,Int>()
            var n = A.size
            for(c in C){
                for(d in D){
                    var tmp=c+d
                    map[tmp]=map[tmp]?.plus(1) ?: 1
                }
            }
            //println(map)
            var ret=0
            for(a in A){
                for(b in B){
                    map[-(a+b)]?.let{
                        ret+=it
                    }
                }
            }
            return ret
        }
    }
}
