package programmers.interaccess

class Solution {
    fun solution(scores: Array<IntArray>): String {
        var answer: String = ""
        val n = scores.size
        val minArr=IntArray(n){ Int.MAX_VALUE }
        val minOnly=BooleanArray(n)
        val maxArr=IntArray(n)
        val maxOnly=BooleanArray(n)
        val sumArr=IntArray(n)
        scores.forEach{
            it.forEachIndexed{i,v ->
                if(minArr[i]>v){
                    minArr[i]=v
                    minOnly[i]=true
                }else if(minArr[i]==v){
                    minOnly[i]=false
                }

                if(maxArr[i]<v){
                    maxArr[i]=v
                    maxOnly[i]=true
                }else if(maxArr[i]==v){
                    maxOnly[i]=false
                }

                sumArr[i]+=v
            }
        }
        for(i in 0 until n){
            var num=n
            if((minArr[i]==scores[i][i] && minOnly[i])|| (maxArr[i]==scores[i][i] && maxOnly[i])){
                sumArr[i]-=scores[i][i]
                num-=1
            }
            val ret=((sumArr[i].toDouble())/num).toInt()
            //println("$num ${sumArr[i]} >> $ret")
            answer+=when(ret){
                in 90..100 -> "A"
                in 80 until 90 -> "B"
                in 70 until 80 -> "C"
                in 50 until 70 -> "D"
                else -> "F"
            }
        }
        return answer
    }
}