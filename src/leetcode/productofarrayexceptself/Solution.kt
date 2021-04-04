private class productofarrayexceptself{
    class Solution {
        fun productExceptSelf(nums: IntArray): IntArray {
            var n=nums.size
            var forward=IntArray(n)
            var backward=IntArray(n)
            var ans=IntArray(n)
            forward[0]=1
            backward[n-1]=1
            for(i in 1..n-1){
                forward[i]=forward[i-1]*nums[i-1]
                backward[n-i-1]=backward[n-i]*nums[n-i]
            }
            for(i in 0..n-1){
                ans[i]=forward[i]*backward[i]
            }
            //println(forward.joinToString())
            //println(backward.joinToString())

            return ans
        }

        fun productExceptSelf2(nums: IntArray): IntArray {
            var n=nums.size
            var ans=IntArray(n)
            ans[0]=1
            for(i in 1..n-1){
                ans[i]=ans[i-1]*nums[i-1]
            }
            //println(ans.joinToString())
            var bef=1
            for(i in n-2 downTo 0){
                bef*=nums[i+1]
                ans[i]*=bef
            }
            //println(ans.joinToString())
            return ans
        }
    }
}
