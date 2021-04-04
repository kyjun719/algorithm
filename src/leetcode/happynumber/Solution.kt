private class happynumber{
    class Solution {
        fun isHappy(n: Int): Boolean {
            var numSet = mutableSetOf<Int>()
            var num = n
            while(num != 1) {
                if(!numSet.add(num)) {
                    return false
                }
                num = getNextNum(num)
            }
            return true
        }
        fun isHappy2(n: Int): Boolean {
            var slow=getNextNum(n)
            var fast=getNextNum(slow)
            while(slow!=1 && fast!=1) {
                if(slow==fast){
                    return false
                }

                slow=getNextNum(slow)
                fast=getNextNum(getNextNum(fast))
                println("${slow} ${fast}")
            }
            return true
        }
        fun getNextNum(n: Int): Int {
            var sum = 0
            var num = n
            while(num != 0) {
                sum += (num%10)*(num%10)
                num = num/10
            }
            return sum
        }
    }

}
