private class containsduplicate{
    class Solution {
        fun containsDuplicate(nums: IntArray): Boolean {
            nums.toList().groupingBy { it }.eachCount().forEach {
                    (k,v) ->
                // println("${k} ${v}")
                if(v>1) return true
            }

            return false
        }

        fun containsDuplicate2(nums: IntArray): Boolean {
            return nums.toSet().size!=nums.size
        }
    }
}
