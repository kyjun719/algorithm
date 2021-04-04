private class numberof1bits{
    class Solution {
        // you need treat n as an unsigned value
        fun hammingWeight(n:Int):Int {
            //println(n.toUInt().toString(2))
            return n.toUInt().toString(2).count{ it=='1' }
        }
        fun hammingWeight2(n:Int):Int = Integer.toBinaryString(n).count { it == '1' }
    }
}
