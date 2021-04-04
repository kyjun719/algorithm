private class sqrtx{
    class Solution {
        fun mySqrt(x: Int): Int {
            if(x<=1){return x}
            var low=0
            var high=x
            //maybe 32 also success
            for(i in 0..32){
                var mid=(low+high)/2
                if(x/mid>=mid){
                    low=mid
                }else{
                    high=mid
                }
                //println("${low} ${high} ${mid}")
            }
            return low
        }

        fun mySqrt2(x: Int): Int {
            return Math.sqrt(x.toDouble()).toInt()
        }
    }
}
