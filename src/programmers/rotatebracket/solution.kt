package programmers.rotatebracket

class Solution {
    fun solution(s: String): Int {
        var answer: Int = 0

        for(i in 0..s.length-1) {
            var tmp = s.substring(i)+s.substring(0,i)
            if(isRight(tmp)) {
                answer+=1
            }
        }
        return answer
    }

    fun isRight(str: String): Boolean {
        var list = mutableListOf<Char>()
        var endCh = charArrayOf(']',')','}')

        for(ch in str) {
            if(endCh.contains(ch)) {
                if(list.isEmpty()) {
                    return false
                }
                var tmp=list.removeAt(list.size-1)
                when(ch) {
                    ']' -> if(tmp!='[') return false
                    ')' -> if(tmp!='(') return false
                    '}' -> if(tmp!='{') return false
                }
            } else {
                list.add(ch)
            }
        }
        return list.size==0
    }
}