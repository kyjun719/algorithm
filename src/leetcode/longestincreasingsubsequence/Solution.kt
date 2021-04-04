private class longestincreasingsubsequence{
    class Solution {
        lateinit var cache: IntArray
        fun lengthOfLIS(nums: IntArray): Int {
            cache=IntArray(nums.size){-1}
            var ret=solve(nums,-1)
            return if(ret>1)return ret-1 else return ret
        }
        fun solve(nums: IntArray, idx: Int): Int {
            if(idx>=0 && cache[idx]!=-1){
                return cache[idx]
            }
            var ret=1
            for(i in idx+1..nums.size-1){
                if(idx==-1 || nums[idx]<nums[i]){
                    ret=maxOf(ret,solve(nums,i)+1)
                }
            }
            if(idx>=0){
                cache[idx]=ret
            }
            return ret
        }
    }
}
