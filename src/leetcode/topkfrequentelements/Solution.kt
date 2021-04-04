private class topkfrequentelements{
    class Solution {
        fun topKFrequent(nums: IntArray, k: Int): IntArray {
            nums.sort()
            var cntList=mutableListOf<Int>()
            var map=mutableMapOf<Int, MutableList<Int>>()
            var cnt=1
            var bef=nums[0]
            for(i in 1..nums.size-1){
                if(bef==nums[i]){
                    cnt+=1
                }else{
                    if(map[cnt]==null){
                        cntList.add(cnt)
                        map[cnt]=mutableListOf<Int>(bef)
                    }else{
                        map[cnt]?.add(bef)
                    }
                    bef=nums[i]
                    cnt=1
                }
            }
            if(map[cnt]==null){
                cntList.add(cnt)
                map[cnt]=mutableListOf<Int>(bef)
            }else{
                map[cnt]?.add(bef)
            }

            cntList.sort()
            var ret=mutableListOf<Int>()
            for(i in cntList.size-1 downTo 0){
                if(ret.size>=k){
                    break
                }else{
                    ret.addAll(map[cntList.get(i)]!!.toList())
                }
            }
            return ret.toIntArray()
        }
    }
}
