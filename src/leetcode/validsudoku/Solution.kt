class Solution {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        return solve(board, 0)
    }
    fun solve(board: Array<CharArray>, cnt: Int): Boolean {
        if(cnt==81) {
            return true
        }
        var x = cnt%9
        var y = cnt/9
        
        if(board[y][x]=='.'){
            return solve(board, cnt+1)
        }
        
        var ra=IntArray(10)
        var ca=IntArray(10)
        var recta=IntArray(10)
        for(i in 0..8) {
            if(board[i][x]!='.') {
                ra[board[i][x]-'0']+=1
            }
            if(board[y][i]!='.') {
                ca[board[y][i]-'0']+=1
            }
            if(board[(y/3)*3+(i/3)][(x/3)*3+(i%3)]!='.') {
                recta[board[(y/3)*3+(i/3)][(x/3)*3+(i%3)]-'0']+=1
            }
        }
        //println("${cnt} >> ${ra.joinToString()} ${ca.joinToString()} ${recta.joinToString()}")
        for(i in 1..9) {
            if(ra[i]+ca[i]+recta[i]>3) {
                return false
            }
        }
        return solve(board,cnt+1)
    }
}	