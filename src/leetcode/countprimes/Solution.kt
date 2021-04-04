private class countprimes{
    class Solution {
        fun countPrimes(n: Int): Int {
            var len: Int=Math.sqrt(n.toDouble()).toInt()
            var arr=IntArray(n)
            for(i in 2..len){
                if(arr[i]==0){
                    var tmp=i*2
                    while(tmp<n){
                        arr[tmp]=1

                        tmp+=i
                    }
                }
            }

            var cnt=0
            for(i in 2..n-1){
                if(arr[i]==0){
                    cnt+=1
                }
            }
            return cnt
        }
    }
}
