private class sortcolors{
    class Solution {
        fun sortColors(nums: IntArray): Unit {
            var l=0
            var r=nums.size-1
            var mid=0
            while(mid<=r){
                var tmp=nums[mid]
                if(tmp==0){
                    swap(l,mid,nums)
                    mid+=1
                    l+=1
                }else if(tmp==1){
                    mid+=1
                }else{
                    swap(r,mid,nums)
                    r-=1
                }
            }
        }
        fun swap(a: Int, b: Int, arr: IntArray){
            var tmp=arr[a]
            arr[a]=arr[b]
            arr[b]=tmp
        }


        fun sortColors2(nums: IntArray): Unit {
            var idx=0
            for(i in 0..nums.size-1){
                idx=i
                for(j in i+1..nums.size-1){
                    if(nums[idx]>nums[j]){
                        idx=j
                    }
                }
                var tmp=nums[i]
                nums[i]=nums[idx]
                nums[idx]=tmp
            }
        }
    }
}
