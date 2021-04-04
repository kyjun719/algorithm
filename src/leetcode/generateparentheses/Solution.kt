private class generateparentheses{
    class Solution {
        data class Item(var s: Int, var e: Int, var str: String)
        fun generateParenthesis(n: Int): List<String> {
            var ret=mutableListOf<String>()
            var q=mutableListOf<Item>()
            q.add(Item(0,0,""))
            while(q.size>0){
                var tmp=q[q.size-1]
                q.removeAt(q.size-1)
                if(tmp.s<n){
                    q.add(Item(tmp.s+1,tmp.e,tmp.str+"("))
                }
                if(tmp.s>tmp.e&&tmp.e<n){
                    q.add(Item(tmp.s,tmp.e+1,tmp.str+")"))
                }
                if(tmp.s==n && tmp.e==n){
                    ret.add(tmp.str)
                }
                //println(tmp)
                //println(ret)
            }
            return ret
        }
    }
}
