class Solution {
    fun rob(nums: IntArray): Int {
        var rob = IntArray(nums.size+1)
        var notRob=IntArray(nums.size+1)
        if(nums.size>0){
            rob[1]=nums[0]
        }
        for(i in 2..nums.size){
            rob[i]=maxOf(rob[i-2],notRob[i-1])+nums[i-1]
            notRob[i]=maxOf(rob[i-1],notRob[i-1])
            //println("${rob.joinToString()} >> ${notRob.joinToString()}")
        }
        
        return maxOf(rob[nums.size], notRob[nums.size])
    }
}