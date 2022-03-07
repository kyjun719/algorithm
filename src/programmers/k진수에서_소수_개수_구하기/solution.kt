package programmers.k진수에서_소수_개수_구하기

class Solution {
    fun solution(n: Int, k: Int): Int {
        return n.toString(k).split("0").filter{ prime(it) }.size
    }

    fun prime(s: String): Boolean {
        if(s.isEmpty() || s=="1") {
            return false
        }
        val num = s.toLong()
        for(i in 2..Math.sqrt(num.toDouble()).toInt()){
            if(num%i.toLong()==0L){
                return false
            }
        }
        return true
    }
}