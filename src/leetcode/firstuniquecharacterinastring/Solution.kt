private class firstuniquecharacterinastring{
    class Solution {
        fun firstUniqChar(s: String): Int {
            var tmp = s.groupingBy{ it }.eachCount().filter{ (k,v) -> v==1 }
            //println(tmp)
            return if(tmp.isEmpty()) -1 else s.indexOf(tmp.keys.toList().get(0))
        }
    }
}
