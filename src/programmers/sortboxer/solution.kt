package programmers.sortboxer

class Solution {
    data class Boxer(
        var cnt: Int = 0,
        var wins: Int = 0,
        var hwins: Int = 0,
        val w: Int,
        val idx: Int
    )
    fun solution(weights: IntArray, head2head: Array<String>): IntArray {
        val bx=mutableListOf<Boxer>()
        for(i in 0 until weights.size){
            bx.add(Boxer(w=weights[i],idx=i+1))
            val s=head2head[i]
            for(j in 0 until s.length){
                if(s[j]=='W' || s[j]=='L'){
                    bx[i].cnt+=1
                }
                if(s[j]=='W'){
                    bx[i].wins+=1
                    if(bx[i].w<weights[j]){
                        bx[i].hwins+=1
                    }
                }
            }
        }
        bx.sortWith( Comparator<Boxer> { o1, o2 ->
            val p1 = if(o1.cnt==0) 0.0 else o1.wins.toDouble()/o1.cnt
            val p2 = if(o2.cnt==0) 0.0 else o2.wins.toDouble()/o2.cnt
            if (p1 == p2) {
                if (o1.hwins != o2.hwins) o2.hwins - o1.hwins
                else if (o1.w != o2.w) o2.w - o1.w
                else o1.idx - o2.idx
            } else {
                if(p1>p2)-1 else 1
            }
        })
        return bx.map { it.idx }.toIntArray()
    }
}