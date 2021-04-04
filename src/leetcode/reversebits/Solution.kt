private class reversebits{
    class Solution {
        // you need treat n as an unsigned value
        //solution1
        fun reverseBits(n:Int):Int {
            var charArr = n.toUInt().toString(2).toCharArray()
            var len = charArr.size
            var bitArr = CharArray(32) { i -> if(len>i)charArr[len-i-1] else '0'}
            return String(bitArr).toUInt(2).toInt()
        }

        //solution2
        fun reverseBits2(n:Int):Int {
            var ret = 0
            var num = n
            var power = 31
            while(num != 0) {
                ret += ((1 and num) shl power)
                num = (num ushr 1)
                power -= 1
                // println("${ret.toUInt().toString(2)} >> ${num.toUInt().toString(2)}")
            }
            return ret
        }

        //solution4
        fun reverseBits3(n:Int):Int {
            //0 : 0-15 16-31
            var ret = n.toLong()
            //1 : 16-31 0-15
            ret = ((ret and 0xffff0000) ushr 16) or ((ret and 0x0000ffff) shl 16)
            // println(ret.toString(2))
            //2 : 24-31 16-23 8-15 0-7
            ret = ((ret and 0xFF00FF00) ushr 8) or ((ret and 0x00FF00FF) shl 8)
            // println(ret.toUInt().toString(2))

            // 3 : 28-31 24-27 20-23 16-19 12-15 8-11 4-7 0-3
            ret = ((ret and 0xF0F0F0F0) ushr 4) or ((ret and 0x0F0F0F0F) shl 4)
            // println(ret.toUInt().toString(2))

            // 4 : 30-31 28-29 26-27 24-25 22-23 20-21 18-19 16-17 14-15 12-13 10-11 8-9 6-7 4-5 2-3 0-1
            ret = ((ret and 0xCCCCCCCC) ushr 2) or ((ret and 0x33333333) shl 2)
            // println(ret.toUInt().toString(2))

            // 5 : 31 30 29 28 27 26 25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 0
            ret = ((ret and 0xAAAAAAAA) ushr 1) or ((ret and 0x55555555) shl 1)

            return ret.toInt()
        }
    }
}
