class Solution {
    fun plusOne(digits: IntArray): IntArray {
        digits[digits.size-1]+=1
        for(i in digits.size-1 downTo 1){
            if(digits[i]>=10){
                digits[i-1]+=1
                digits[i]-=10
            }else{
                break
            }
        }
        if(digits[0]>=10){
            var ret = IntArray(digits.size+1)
            ret[0]=1
            return ret
        }else{
            return digits
        }
    }
}