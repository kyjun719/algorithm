private class findpeakelement{
    class Solution {
        fun findPeakElement(nums: IntArray): Int {
            return nums.indexOf(nums.max()!!)
        }

        fun findPeakElement2(nums: IntArray): Int {
            for(i in 0..nums.size-1) {
                var l=if(i-1>=0)nums[i-1] else Int.MIN_VALUE
                var r=if(i+1<nums.size)nums[i+1] else Int.MIN_VALUE
                if(nums[i]>=l && nums[i]>=r) {
                    return i
                }
            }
            return -1
        }

        fun findPeakElement3(nums: IntArray): Int {
            var l=0
            var r=nums.size-1
            while(l<r) {
                var mid=(l+r)/2
                if(nums[mid]>nums[mid+1]) {
                    r=mid
                } else {
                    l=mid+1
                }
            }
            return l
        }
    }
}
