class Solution {
    fun reverseString(s: CharArray): CharArray {
        var i=0
        var j=s.size-1
        while(i<j){
            swap(s,i,j)
            i+=1
            j-=1
        }
        return s
    }
    fun swap(s: CharArray, i: Int,j: Int){
        var tmp=s[i]
        s[i]=s[j]
        s[j]=tmp
    }
}