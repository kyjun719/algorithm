private class validparentheses{
    class Solution {
        fun isValid(s: String): Boolean {
            var st=mutableListOf<Char>()
            for(ch in s){
                if(ch=='(' || ch=='{' || ch=='['){
                    st.add(ch)
                }else{
                    //println(st)
                    var last=if(st.size>0)st[st.size-1] else ' '
                    when(ch){
                        ')' -> if(last!='(')return false
                        '}' -> if(last!='{')return false
                        ']' -> if(last!='[')return false
                    }
                    st.removeAt(st.size-1)
                }
            }
            return st.size==0
        }
    }
}
