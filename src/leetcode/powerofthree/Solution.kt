private class powerofthree{
    class Solution {
        fun isPowerOfThree(n: Int): Boolean {
            if(n==1)return true
            if(n==0)return false
            if(n%3!=0)return false
            return isPowerOfThree(n/3)
        }

        fun isPowerOfThree2(n: Int): Boolean {
            return n>0 && 1162261467%n==0
        }
    }
}
