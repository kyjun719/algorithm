private class romantointeger{
    class Solution {
        var map = mutableMapOf<Char, Int>(
            'I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500,
            'M' to 1000)
        fun romanToInt(s: String): Int {
            var n = s.length
            val isSet = { idx: Int, s: String ->
                if(idx >= n-1) {
                    false
                } else {
                    var now = s[idx]
                    var next = s[idx+1]
                    (now=='I' && next=='V') || (now=='I' && next=='X') ||
                            (now=='X' && next=='L') || (now=='X' && next=='C') ||
                            (now=='C' && next=='D') || (now=='C' && next=='M')
                }
            }
            var sum = 0
            var i = 0
            while(i<n) {
                if(isSet(i,s)) {
                    sum += map[s[i+1]]!!.minus(map[s[i]]!!)
                    i += 1
                } else {
                    sum += map[s[i]]!!
                }
                i+=1
            }
            return sum
        }
    }
}
