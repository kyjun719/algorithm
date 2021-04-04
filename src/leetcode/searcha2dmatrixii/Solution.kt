private class searcha2dmatrixii{
    class Solution {
        fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
            for(y in 0..matrix.size-1){
                if(matrix[y][0]>target){
                    break
                }
                var r=matrix[y].size-1
                var l=0
                while(l<=r){
                    //println("${y} ${l},${r}")
                    if(l==r){
                        if(matrix[y][l]==target){
                            return true
                        }else{
                            break
                        }
                    }
                    var mid: Int=(r+l)/2
                    if(matrix[y][mid]>=target){
                        r=mid
                    }else{
                        l=mid+1
                    }
                }
            }
            return false
        }
    }
}
