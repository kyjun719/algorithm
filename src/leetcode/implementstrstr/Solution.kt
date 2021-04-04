private class implementstrstr{
    class Solution {
        fun strStr(haystack: String, needle: String): Int {
            if(needle==""){
                return 0
            }
            //접두사와 접미사가 일치하는 길이
            var matched=0
            //접미사의 시작부분
            var begin=1
            var arr=IntArray(needle.length)
            //needle에서 needle(0..len-1)를 움직이면서 일치하는길이 검색
            while(begin+matched<needle.length){
                if(needle[matched]==needle[begin+matched]) {
                    /*
                    0~begin+matched까지의 부분문자열에서 matched길이만큼의
                    접미사와 접두사가 일치함
                    */
                    matched+=1
                    arr[begin+matched-1]=matched
                } else {
                    if(matched==0) {
                        //직전 부분문자열에서 하나도 일치하지 않은경우 접미사 시작점 증가
                        begin+=1
                    } else {
                        //직전 부분문자열에서 matched길이만큼 일치하였으므로
                        //matched길이 문자의 접미사 만큼은 일치하고, 그다음 문자열이 일치하지 않음
                        //따라서 다음비교전 matched길이 문자의 접미사부터 비교함
                        begin+=matched-arr[matched-1] //matched에서 접미사 시작부분으로 이동
                        matched=arr[matched-1] //matched의 접미사 다음부분부터 비교함
                    }
                }
            }

            begin=0
            matched=0
            //haystack과 needle 비교
            while(begin<=haystack.length-needle.length) {
                if(matched<needle.length && haystack[begin+matched]==needle[matched]) {
                    //matched만큼 일치하는 경우
                    matched+=1
                    if(matched==needle.length){
                        //전부 일치하는경우
                        return begin
                    }
                } else {
                    if(matched==0){
                        begin+=1
                    } else {
                        //matched만큼 일치하였으므로 matched의 접미사로 바로 이동하여 다음일치 확인
                        //matched에서 다음일치가 일어나는 부분은 접미사 부분임
                        begin+=matched-arr[matched-1]
                        matched=arr[matched-1]
                    }
                }
            }
            return -1
        }

        fun strStr2(haystack: String, needle: String): Int {
            if(needle.length==0){
                return 0
            }
            var nh=needle.hashCode()
            var len=needle.length
            for(i in 0..haystack.length-needle.length){
                if(haystack.substring(i,i+len).hashCode()==nh){
                    return i
                }
            }
            return -1
        }


        val MOD=1000000007
        var powArr=IntArray(50001)
        fun strStr3(haystack: String, needle: String): Int {
            if(needle.length==0){
                return 0
            }
            if(haystack.length==0){
                return -1
            }

            powArr[0]=1
            for(i in 1..50000) {
                powArr[i]=(powArr[i-1]*7)%MOD
            }

            var nh=convertToInt(needle)
            var len=needle.length
            var tmp=convertToInt(haystack.substring(0,minOf(len,haystack.length)))

            for(i in 0..haystack.length-needle.length){
                //println("${tmp} ${nh}")
                if(tmp==nh){
                    return i
                }
                tmp-=(haystack[i]-'a')
                //print("${tmp} ")
                tmp/=7
                //print("${tmp} ")
                if(len+i<haystack.length) {
                    tmp=(tmp+(haystack[len+i]-'a')*powArr[len-1])%MOD
                }
                //println(tmp)
            }
            return -1
        }
        fun convertToInt(s: String): Int {
            var ret=0
            for(i in 0..s.length-1){
                ret+=powArr[i]*(s[i]-'a')
                ret%=MOD
                //println("${s[i]} ${ret}")
            }
            return ret
        }
    }
}
