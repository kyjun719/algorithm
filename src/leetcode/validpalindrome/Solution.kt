private class validpalindrome{
    class Solution {
        fun String.isAccept(idx: Int): Boolean {
            //println("${this} ${idx}")
            return (this[idx]>='a' && this[idx]<='z') || (this[idx]>='0' && this[idx]<='9')
        }
        fun isPalindrome(s: String): Boolean {
            var tmp=s.toLowerCase()
            var h=0
            var t=s.length-1
            while(h<=t) {
                when{
                    !(tmp.isAccept(h)) -> h+=1
                    !(tmp.isAccept(t)) -> t-=1
                    else -> {
                        //println("${tmp}>>${h} ${t}")
                        if(tmp[h]!=tmp[t]) {
                            return false
                        } else {
                            h+=1
                            t-=1
                        }
                    }
                }
            }
            return true
        }

        fun isPalindrome2(s: String): Boolean {
            var tmp = Regex("""\W|\_""").replace(s,"").toLowerCase()
            var h = 0
            var t = tmp.length-1
            while(h<=t) {
                if(tmp[h] != tmp[t]) {
                    return false
                }
                h+=1
                t-=1
            }
            return true
        }
    }
}
