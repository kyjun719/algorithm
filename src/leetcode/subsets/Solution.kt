private class subsets{
    class Solution {
        fun subsets(nums: IntArray): List<List<Int>> {
            return solve(nums,0,mutableListOf<Int>())
        }
        fun solve(nums: IntArray, i: Int, list: MutableList<Int>): List<List<Int>> {
            var ret=mutableListOf<List<Int>>()
            if(i==nums.size){
                ret.add(list.toList())
                return ret
            }
            ret.addAll(solve(nums,i+1,list.toMutableList()))
            list.add(nums[i])
            ret.addAll(solve(nums,i+1,list.toMutableList()))
            return ret
        }

        fun subsets2(nums: IntArray): List<List<Int>> {
            var ret=mutableListOf<List<Int>>()
            for(i in 0..(1 shl nums.size)-1){
                ret.add(calc(nums,i))
            }
            return ret
        }
        fun calc(nums: IntArray, i: Int): List<Int>{
            var ret=mutableListOf<Int>()
            for(j in 0..nums.size-1){
                if((i and (1 shl j))>0){
                    ret.add(nums[j])
                }
            }
            return ret.toList()
        }
    }
}
