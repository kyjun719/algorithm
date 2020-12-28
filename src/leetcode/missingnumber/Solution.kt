class Solution {
    fun missingNumber(nums: IntArray): Int {
        var num=0
        for(i in 1..nums.size) {
            num += i-nums[i-1]
        }
        return num
    }
	fun missingNumber2(nums: IntArray): Int = nums.foldIndexed(nums.size) { acc, i, num -> acc + i - num }
}
