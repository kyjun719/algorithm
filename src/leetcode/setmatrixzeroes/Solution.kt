private class setmatrixzeroes{
    class Solution {
        fun setZeroes(matrix: Array<IntArray>): Unit {
            var rowSet=mutableSetOf<Int>()
            var colSet=mutableSetOf<Int>()
            var n=matrix.size
            var m=matrix[0].size
            for(y in 0..n-1){
                for(x in 0..m-1){
                    if(matrix[y][x]==0){
                        rowSet.add(y)
                        colSet.add(x)
                    }
                }
            }
            for(y in rowSet){
                for(x in 0..m-1){
                    matrix[y][x]=0
                }
            }
            for(x in colSet){
                for(y in 0..n-1){
                    matrix[y][x]=0
                }
            }
        }
    }
}
