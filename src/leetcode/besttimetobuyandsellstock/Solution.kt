class Solution {
    fun maxProfit(prices: IntArray): Int {
        var ret=0
        var n=prices.size
        for(i in 0..n-1){
            for(j in i..n-1){
                ret=maxOf(ret,prices[j]-prices[i])
            }
        }
        if(ret<0) return 0
        return ret
    }
    fun maxProfit2(prices: IntArray): Int {
        var minPrice = 987654321
        var maxVal = 0
        for(i in prices) {
            minPrice = minOf(minPrice, i)
            maxVal = maxOf(maxVal, i-minPrice)
        }
        return maxVal
    }
}