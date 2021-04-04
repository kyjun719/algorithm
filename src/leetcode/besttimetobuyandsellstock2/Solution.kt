private class besttimetobuyandsellstock2{
    class Solution {
        fun maxProfit(prices: IntArray): Int {
            var sum = 0
            for(i in 1..prices.size-1) {
                if(prices[i]>prices[i-1]) {
                    sum+=prices[i]-prices[i-1]
                }
            }
            return sum
        }

        fun maxProfit2(prices: IntArray): Int {
            return prices.toList().windowed(2).fold(0) {
                    acc, (l, r) -> acc + maxOf(r-l, 0)
            }
        }
    }
}
