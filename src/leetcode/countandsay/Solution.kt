class Solution {
    fun countAndSay(n: Int): String {
        if(n==1) return "1"
        var tmp=countAndSay(n-1)
        var cnt=1
        var bef=tmp[0]
        var ret=StringBuilder()
        for(i in 1..tmp.length-1){
            var v=tmp[i]
            if(v!=bef){
                ret.append(cnt.toString(), bef)
                cnt=1
                bef=v
            }else{
                cnt+=1
            }
            //println("${v} ${bef} ${cnt} ${ret}")
        }
        ret.append(cnt.toString(), bef)
        return ret.toString()
    }
}