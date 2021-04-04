private class containerwithmostwater{
    class Solution {
        fun maxArea(height: IntArray): Int {
            var ret=0
            var r=height.size-1
            var l=0
            while(r>l) {
                ret = maxOf(ret,(r-l)*minOf(height[r],height[l]))
                if(height[r]>height[l]) {
                    l+=1
                } else {
                    r-=1
                }
            }
            return ret
        }
    }
}
