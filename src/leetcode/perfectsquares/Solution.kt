private class perfectsquares{
    class Solution {
        var num=IntArray(101){
            it*it
        }
        var ans=987654321
        fun numSquares(n: Int): Int {
            solve(n,0)
            return ans
        }
        fun solve(n: Int, cnt: Int){
            if(n==0){
                ans=minOf(ans,cnt)
                return
            }
            if(ans<=cnt){
                return
            }
            for(i in 100 downTo 1){
                if(num[i]>n){
                    continue
                }
                var k: Int = n/num[i]
                solve(n-num[i]*k,cnt+k)
            }
        }
    }
}
