class Solution {
    fun maxSubArray(nums: IntArray): Int {
        var sum=nums[0]
        for(i in 1..nums.size-1) {
            nums[i]=maxOf(nums[i], nums[i]+nums[i-1])
            sum=maxOf(sum, nums[i])
        }
        return sum
    }
}