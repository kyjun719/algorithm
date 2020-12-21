class Solution {
    fun singleNumber(nums: IntArray): Int {
        nums.sort()
        var n = nums.size
        for(i in 0..n) {
            var isLeft = if(i<=0)false else nums[i]==nums[i-1]
            var isRight = if(i>=n-1)false else nums[i]==nums[i+1]
            if(!(isLeft || isRight)) {
                return nums[i]
            }
        }
        return -1
    }
	
	fun singleNumber(nums: IntArray): Int {
        return nums.reduce { acc, int -> acc xor int }
    }
}