private class reverseinteger{
    class Solution {
        fun reverse(x: Int): Int {
            var tmp=x
            var rev=0
            var sig=if(x<0)-1 else 1
            if(sig<0){
                tmp*=-1
            }
            while(tmp>0){
                rev=mul(rev,10)
                if(rev<0) {
                    return 0
                }
                if(Int.MAX_VALUE-tmp%10<rev) {
                    return 0
                }
                rev+=tmp%10
                tmp=tmp/10
            }
            return rev*sig
        }
        fun mul(x: Int, t: Int): Int {
            var ret=x
            for(i in 1..t-1) {
                if(Int.MAX_VALUE-x<ret) {
                    return -1
                }
                ret+=x
            }
            return ret
        }
    }
}
