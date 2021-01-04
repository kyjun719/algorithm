class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        var ret = mutableListOf<Int>()
        solve(nums, target, 0, ret)
        return ret.toIntArray()
    }
    fun solve(nums: IntArray, target: Int, idx: Int, ret: MutableList<Int>): Boolean {
        if(ret.size==2) {
            return target==0
        }
        
        if(idx >= nums.size) {
            return false
        }
        
        ret.add(idx)
        if(solve(nums, target-nums[idx], idx+1, ret)) {
            return true
        } else {
            ret.removeAt(ret.size-1)
            return solve(nums, target, idx+1, ret)
        }
    }
	
	fun twoSum2(nums: IntArray, target: Int): IntArray {
        var map = mutableMapOf<Int, Int>()
        for(i in 0..nums.size-1) {
            if(map[nums[i]] != null) {
                return intArrayOf(map[nums[i]]!!, i)
            }
            map[target-nums[i]] = i
        }
        return intArrayOf(-1,-1)
    }
}