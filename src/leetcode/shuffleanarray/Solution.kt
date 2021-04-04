import java.util.*

private class shuffleanarray{
    class Solution(val nums: IntArray) {
        val n=nums.size
        /** Resets the array to its original configuration and return it. */
        fun reset(): IntArray {
            return nums
        }

        /** Returns a random shuffling of the array. */
        fun shuffle(): IntArray {
            var arr=nums.copyOf()
            for(i in 0..n-1){
                var a=arr[i]
                var b= Random().nextInt(n)
                arr[i]=arr[b]
                arr[b]=a
            }
            return arr
        }

    }

    /**
     * Your Solution object will be instantiated and called as such:
     * var obj = Solution(nums)
     * var param_1 = obj.reset()
     * var param_2 = obj.shuffle()
     */
}
