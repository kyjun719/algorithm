private class movezeroes{
    class Solution {
        fun moveZeroes(nums: IntArray): IntArray {
            var idx=0
            for(i in 0..nums.size-1) {
                if(nums[i]==0) {
                    continue
                }
                nums[idx]=nums[i]
                if(idx!=i) {
                    nums[i]=0
                }
                idx=idx+1
            }

            return nums
        }
    }
}
