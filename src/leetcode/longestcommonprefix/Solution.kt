private class longestcommonprefix{
    class Solution {
        fun longestCommonPrefix(strs: Array<String>): String {
            var ret=""
            var minLen=987654321
            for(str in strs){
                if(minLen>=str.length){
                    minLen=str.length
                    ret=str
                }
            }
            for(i in 0..minLen){
                var status=true
                for(str in strs){
                    if(!str.startsWith(ret)){
                        status=false
                        break
                    }
                }
                //println("${ret} ${status}")
                if(status){
                    break
                }
                ret=ret.substring(0,ret.length-1)
            }
            return ret
        }
    }
}
