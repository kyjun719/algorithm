private class validanagram{
    class Solution {
        fun isAnagram(s: String, t: String): Boolean {
            if(s.length != t.length) return false

            var sMap = mutableMapOf<Char,Int>()
            for(i in 0..s.length-1) {
                sMap.put(s[i], if(sMap[s[i]]==null) 1 else sMap[s[i]]!!.plus(1))
                sMap.put(t[i], if(sMap[t[i]]==null) -1 else sMap[t[i]]!!.minus(1))
            }
            for((k,v) in sMap) {
                // println("${k} ${v}")
                if(v!=0) {
                    return false
                }
            }
            return true
        }
        fun isAnagram2(s: String, t: String)
                = s.groupingBy { it }.eachCount() == t.groupingBy { it }.eachCount()
    }
}
