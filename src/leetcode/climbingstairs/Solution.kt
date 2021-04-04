private class climbingstairs{
    class Solution {
        var sum: IntArray=IntArray(46){ -1 }
        fun climbStairs(n: Int): Int {
            if(n==0) {
                return 1
            }
            if(n<0) {
                return 0
            }
            if(sum[n]!=-1) {
                return sum[n]
            }
            var ret=climbStairs(n-1)
            ret += climbStairs(n-2)
            sum[n]=ret
            return ret
        }
        fun climbStairs2(n: Int): Int {
            var bef1=0; var bef2=1;
            for(i in 1..n) {
                var tmp = bef1+bef2
                bef1=bef2
                bef2=tmp
            }
            return bef2
        }
    }
}
