package programmers.문자열_압축

class Solution {
    fun solution(s: String): Int {
        var answer = s.length
        val l = s.length
        for(i in 1 until l/2+1){
            var cre = ""
            var ret = ""
            var j = 0
            var cnt = 1
            while(j<l){
                val tmp = s.substring(j,minOf(l,i+j))
                if(cre==tmp){
                    cnt+=1
                }else{
                    ret+=appendStr(cnt,cre)
                    cnt=1
                }
                cre=tmp
                j+=i
            }
            ret+=appendStr(cnt,cre)
            //println("$i $ret")
            answer=minOf(answer,ret.length)
        }
        return answer
    }
    fun appendStr(cnt: Int, cre: String): String {
        return if(cnt>1){
            cnt.toString()+cre
        }else{
            cre
        }
    }
}