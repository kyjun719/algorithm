private class excelsheetcolumnnumber{
    class Solution {
        fun titleToNumber1(s: String): Int {
            var n = s.length
            var ret = 0
            for(i in 0..n-1) {
                ret *= 26
                ret += (s[i]-'A'+1)
            }
            return ret
        }
        fun titleToNumber2(s: String): Int {
            return s.toCharArray().fold(0) { total, num -> total * 26 + num.toInt() - 64 }
        }
    }
}
