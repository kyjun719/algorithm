private class removeduplicatesfromsortedarray{
    class Solution {
        fun removeDuplicates(nums: IntArray): Int {
            if(nums.size<=1) {
                return nums.size
            }

            var len = 1
            var s = 1
            var e = nums.size-1
            while((len==s) && (s<nums.size)) {
                if(nums[s-1]>=nums[s]) {
                    for(i in s..nums.size-1) {
                        if(nums[s-1]<nums[i]) {
                            len+=1
                            nums[s]=nums[i]
                            break
                        }
                    }
                } else {
                    len+=1
                }
                // println("${s} >> ${nums.joinToString()}")
                s+=1
            }
            return len
        }
        fun removeDuplicates2(nums: IntArray): Int {
            var idx=1
            for(i in 0..nums.size-2) {
                if(nums[i] != nums[i+1]) {
                    nums[idx]=nums[i+1]
                    idx+=1
                }
            }
            return idx
        }
    }
}
