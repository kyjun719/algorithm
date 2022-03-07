package programmers.주차_요금_계산


class Solution {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        var numSet = mutableSetOf<String>()
        var timeMap = mutableMapOf<String, Int>()
        var inMap = mutableMapOf<String, Int>()

        records.forEach {
            var tmp = it.split(" ")

            if(tmp[2] == "IN") {
                numSet.add(tmp[1])
                inMap[tmp[1]] = convTime(tmp[0])
            } else {
                val time = convTime(tmp[0]) - inMap[tmp[1]]!!
                timeMap[tmp[1]] = (timeMap[tmp[1]] ?: 0) + time
                inMap.remove(tmp[1])
            }
        }

        inMap.forEach { (k, v) -> timeMap[k] = (timeMap[k] ?: 0)+convTime("23:59")-v }

        var answer: IntArray = IntArray(numSet.size)
        numSet.toList().sorted().forEachIndexed { index, s ->
            answer[index] = calcFee(timeMap[s]!!, fees)
        }

        return answer
    }

    private fun convTime(s: String): Int {
        var ret = s.split(":")
        return ret[0].toInt()*60+ret[1].toInt()
    }

    private fun calcFee(time: Int, fees: IntArray): Int {
        if(fees[0] > time) {
            return fees[1]
        } else {
            return fees[1] + Math.ceil((time-fees[0]).toDouble()/fees[2]).toInt() * fees[3]
        }
    }
}