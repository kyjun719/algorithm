package programmers.entranceexit

class Solution {
    fun solution(enter: IntArray, leave: IntArray): IntArray {
        val q = mutableListOf<Int>()
        val n = enter.size
        var li=0
        val entered = BooleanArray(enter.size)
        var answer: IntArray = IntArray(enter.size)
        for(e in enter){
            entered[e-1]=true
            answer[e-1] = q.size
            for(num in q){
                answer[num.toInt()-1]+=1
            }
            q.add(e)
            if(li<n && leave[li]==e){
                while(li<n && entered[leave[li]-1]){
                    q.remove(leave[li])
                    li+=1
                }
            }
            //println("$q ${answer.joinToString()}")
        }
        return answer
    }
}