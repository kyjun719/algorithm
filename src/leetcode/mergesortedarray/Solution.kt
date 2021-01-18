class Solution {
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        //이미 정렬된 배열이므로, 큰수부터 채워넣어도됨
        var np=n-1
        var mp=m-1
        var l=n+m-1
        while(np>=0 && mp>=0){
            if(nums1[mp]>nums2[np]){
                nums1[l]=nums1[mp]
                mp-=1
            }else{
                nums1[l]=nums2[np]
                np-=1
            }
            l-=1
            //println("${mp} ${np} ${nums1.joinToString()}")
        }
        
        while(np>=0){
            nums1[l]=nums2[np]
            np-=1
            l-=1
        }
    }
	
	fun merge2(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        if(nums2.size==0)return
        nums2.copyInto(nums1,m,0,n)
        nums1.sort()
    }
}