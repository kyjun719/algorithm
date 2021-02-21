class Solution {
    //https://kplog.tistory.com/m/274
    fun getSum(a: Int, b: Int): Int {
        var an=a
        var bn=b
        while(bn!=0){
            var c = (an and bn) shl 1
            an=an xor bn
            bn=c
        }
        return an
    }
}