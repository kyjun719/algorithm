package programmers.사라지는_발판


class Solution {
    var del = arrayOf(Pair(1,0),Pair(-1,0),Pair(0,1),Pair(0,-1))
    var answer = Int.MAX_VALUE

    fun solution(board: Array<IntArray>, aloc: IntArray, bloc: IntArray): Int {
        var ret = solve(board, aloc, bloc, 0)
        // println("$ret")
        return ret.second
    }

    //a win, cnt
    fun solve(board: Array<IntArray>, aloc: IntArray, bloc: IntArray, cnt: Int): Pair<Boolean, Int> {
        var move = if(cnt%2==0) aloc else bloc

        if(board[move[0]][move[1]] == 0){
            // println("${aloc.joinToString()}, ${bloc.joinToString()} >> $cnt")
            answer = minOf(cnt, answer)
            return Pair(!(cnt%2==0), cnt)
        }

        var moved = false

        var aWin = false
        var bWin = false
        var result = mutableListOf<Pair<Boolean, Int>>()

        del.forEach {
            var na = aloc
            var nb = bloc
            if(cnt%2==0){
                na = getNext(aloc, it)
            } else {
                nb = getNext(bloc, it)
            }

            if(canGo(board, move, it)){
                moved = true
                board[move[0]][move[1]]=0
                var tmp = solve(board,na,nb,cnt+1)

                aWin = aWin or tmp.first
                bWin = bWin or !(tmp.first)
                result.add(tmp)
                board[move[0]][move[1]]=1
            }
        }

        // println("${if(cnt%2==0) "a turn" else "b turn"} $result")

        if(!moved) {
            //움직이지 못한경우
            return Pair(!(cnt%2==0), cnt)
        }

        if(cnt%2==0) {
            if(aWin) {
                //a turn && a win
                var num = Int.MAX_VALUE
                result.forEach {
                    if(it.first) {
                        num = minOf(num, it.second)
                    }
                }
                return Pair(true, num)
            } else {
                //a turn && a lose
                var num = 0
                result.forEach {
                    if(!it.first) {
                        num = maxOf(num, it.second)
                    }
                }
                return Pair(false, num)
            }
        } else {
            if(bWin) {
                //b turn && b win
                var num = Int.MAX_VALUE
                result.forEach {
                    if(!it.first) {
                        num = minOf(num, it.second)
                    }
                }
                return Pair(false, num)
            } else {
                //b turn && b lose
                var num = 0
                result.forEach {
                    if(it.first) {
                        num = maxOf(num, it.second)
                    }
                }
                return Pair(true, num)
            }
        }
    }

    fun canGo(board: Array<IntArray>, loc: IntArray, del: Pair<Int, Int>): Boolean {
        val (ny,nx) = getNext(loc, del)
        return ny>=0 && ny < board.size && nx >=0 && nx < board[0].size && board[ny][nx]==1
    }

    fun getNext(loc: IntArray, del: Pair<Int, Int>): IntArray {
        return intArrayOf(loc[0]+del.first, loc[1]+del.second)
    }
}