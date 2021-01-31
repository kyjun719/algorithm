class Solution {
    fun permute(nums: IntArray): List<List<Int>> {
        var q=mutableListOf<List<Int>>()
        q.add(nums.toList())
        
        var ret=mutableListOf<List<Int>>()
        while(q.size>0){
            var tmp=q[q.size-1]
            q.removeAt(q.size-1)
            if(ret.contains(tmp)){
                continue
            }
            ret.add(tmp)
            for(i in 0..tmp.size-1){
                for(j in i+1..tmp.size-1){
                    var next=tmp.toTypedArray()
                    var a=next[i]
                    next[i]=next[j]
                    next[j]=a
                    var nextList=next.toList()
                    if(ret.contains(nextList)){
                        continue
                    }
                    q.add(nextList)
                }
            }
            //println(q)
            //println(ret)
            //println()
        }
        return ret
    }
}