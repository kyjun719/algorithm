private class palindromepartitioning{
    class Solution {
        lateinit var ret: MutableList<List<String>>
        fun partition(s: String): List<List<String>> {
            ret=mutableListOf<List<String>>()
            solve(s,0,mutableListOf<String>())
            return ret
        }
        fun solve(s: String, i: Int, list: MutableList<String>) {
            //println("${s} ${i} ${list}")
            if(i==s.length){
                ret.add(list.toList())
                return
            }
            var ret=mutableListOf<List<String>>()
            for(idx in 1..(s.length-i)){
                var str=s.substring(i,i+idx)
                //println("${str} ${isPalindrome(str)}")
                if(isPalindrome(str)){
                    list.add(str)
                    solve(s,i+str.length,list)
                    list.removeAt(list.size-1)
                }
            }
        }
        fun isPalindrome(s: String): Boolean {
            var r=s.length-1
            for(i in 0..(s.length/2-1)){
                if(s[i]!=s[r]){
                    return false
                }
                r-=1
            }
            return true
        }
    }
}
