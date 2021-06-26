package programmers.multileveltoothbrushselling

class Solution {
    fun solution(enroll: Array<String>, referral: Array<String>, seller: Array<String>, amount: IntArray): IntArray {
        var nameMap = hashMapOf<String, Int>()
        for(i in 0 until enroll.size) {
            nameMap[enroll[i]] = i
        }

        var answer = IntArray(enroll.size)
        for( i in 0 until seller.size) {
            var selIdx = nameMap[seller[i]]
            var amount = amount[i]*100

            selIdx?.let {
                answer[it] += amount
            }

            while(selIdx != null && amount > 0) {
                amount = (amount*0.1).toInt()
                answer[selIdx] -= amount
                var parentIdx = nameMap[referral[selIdx]]

                if(parentIdx == null) {
                    selIdx = null
                } else {
                    answer[parentIdx] += amount
                    selIdx = parentIdx
                }
                // println(answer.joinToString())
            }
            // println("===============")
        }

        return answer
    }
}

fun main() {
    println(Solution().solution(
        arrayOf("john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"),
        arrayOf("-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"),
        arrayOf("young", "john", "tod", "emily", "mary"),
        intArrayOf(12, 4, 2, 5, 10)
    ).joinToString())

    println(Solution().solution(
        arrayOf("john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"),
        arrayOf("-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"),
        arrayOf("sam", "emily", "jaimie", "edward"),
        intArrayOf(2, 3, 5, 4)
    ).joinToString())
}