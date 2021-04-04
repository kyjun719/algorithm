private class factorialtrailingzeroes{
    class Solution {
        fun trailingZeroes(n: Int): Int {
            //https://brilliant.org/wiki/trailing-number-of-zeros/
            var ret: Int=0
            var powNum=5
            while(n>=powNum){
                ret+=(n/powNum)
                powNum*=5
            }
            return ret
        }
    }
}
