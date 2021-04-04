private class gameoflife {
    class Solution {
        var m: Int = 0
        var n: Int = 0
        fun gameOfLife(board: Array<IntArray>): Unit {
            m=board.size
            n=board[0].size
            var next=Array<IntArray>(m){IntArray(n)}
            for(i in 0..m-1){
                for(j in 0..n-1){
                    next[i][j]=solve(i,j,board)
                }
            }

            for(i in 0..m-1){
                for(j in 0..n-1){
                    board[i][j]=next[i][j]
                }
            }
        }
        fun solve(i: Int, j: Int, board: Array<IntArray>): Int {
            var liveCnt=0
            var deadCnt=0
            for(id in -1..1){
                for(jd in -1..1){
                    if(id==0 && jd==0){
                        continue
                    }
                    if(!isInBound(i+id,j+jd)){
                        continue
                    }
                    if(board[i+id][j+jd]==1){
                        liveCnt+=1
                    }else{
                        deadCnt+=1
                    }
                }
            }
            when{
                liveCnt<2 -> return 0
                liveCnt==2 -> return board[i][j]
                liveCnt==3 -> return 1
                else -> return 0
            }
        }
        fun isInBound(i: Int, j: Int): Boolean{
            return i>=0 && i<m && j>=0 && j<n
        }
    }
}
