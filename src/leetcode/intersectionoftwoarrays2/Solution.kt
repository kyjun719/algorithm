private class intersectionoftwoarrays2{
    class Solution {
        fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
            var countMap = mutableMapOf<Int, Int>()
            nums1.forEach{ v ->
                if(countMap[v]==null) {
                    countMap[v]=0
                }
                countMap[v] = countMap[v]!!.inc()
            }

            var ret = mutableListOf<Int>()
            nums2.forEach{ v ->
                countMap[v]?.let {
                    ret.add(v)
                    countMap[v] = countMap[v]!!.dec()
                    if(countMap[v]==0) {
                        countMap.remove(v)
                    }
                }
            }

            return ret.toIntArray()
        }
    }
}
