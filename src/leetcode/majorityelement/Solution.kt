private class majorityelement{
    class Solution {
        fun majorityElement(nums: IntArray): Int {
            var map=mutableMapOf<Int,Int>()
            var major=-1
            var n: Int=nums.size/2
            for(i in nums){
                map[i]=map[i]?.inc() ?: 1
                if(map[i]!!>n){
                    return i
                }
            }
            return -1
        }
    }
}
