private class findtheduplicatenumber{
    class Solution {
        fun findDuplicate(nums: IntArray): Int {
            //숫자범위는 n인데 갯수는 n+1개 이므로 최소 하나의 숫자는 중복됨
            var list=mutableListOf<Int>()
            for(i in nums){
                if(list.contains(i)){
                    return i
                }
                list.add(i)
            }
            return -1
        }

        fun findDuplicate2(nums: IntArray): Int {
            var t=nums[0]
            var h=nums[0]
            do{
                t=nums[t]
                h=nums[nums[h]]
            }while(t!=h)
            //println("${t} ${h}")
            t=nums[0]
            while(t!=h){
                t=nums[t]
                h=nums[h]
            }
            return h
        }
    }
}
