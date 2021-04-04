private class kthsmallestelementinasortedmatrix{
    class Solution {
        fun kthSmallest(matrix: Array<IntArray>, k: Int): Int {
            var list=Array<MutableList<Int>>(21,{mutableListOf<Int>()})
            var n=matrix.size
            for(i in 0..n-1){
                for(j in 0..n-1){
                    //println(getIdx(matrix[i][j]))
                    list[getIdx(matrix[i][j])].add(matrix[i][j])
                }
            }
            var cnt=k
            for(i in 0..list.size-1){
                if(cnt<=list[i].size){
                    return list[i].sorted()[cnt-1]
                }
                cnt-=list[i].size
            }
            return 0
        }
        fun getIdx(v: Int): Int{
            var cnt=v.toString().length
            if(v<0){
                return 10-cnt+1
            }else{
                return 10+cnt
            }
        }


        fun kthSmallest2(matrix: Array<IntArray>, k: Int): Int {
            var n=matrix.size
            var l=matrix[0][0]
            var r=matrix[n-1][n-1]
            while(l<=r) {
                var mid=(l+r)/2

                var cnt=0
                var last=0
                for(i in n-1 downTo 0) {
                    while(last<n && matrix[i][last]<=mid) {
                        last+=1
                    }
                    cnt+=last
                }
                if(cnt<k) {
                    l=mid+1
                } else {
                    r=mid-1
                }
            }
            return l
        }
    }
}
