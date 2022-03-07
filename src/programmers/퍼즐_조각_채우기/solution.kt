package programmers.퍼즐_조각_채우기

const val minY = 0
const val maxY = 1
const val minX = 2
const val maxX = 3

class Solution {
    data class Block(
        var size: Int = 0,
        var list: MutableList<Set<Pair<Int, Int>>> = mutableListOf()
    )
    var n = 0
    val dy = intArrayOf(1,-1,0,0)
    val dx = intArrayOf(0,0,1,-1)
    lateinit var visited: Array<BooleanArray>
    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        n = table.size

        val tableBlockList = findBlock(table, 0)
        val gameBoardBlockList = findBlock(game_board, 1)

        val gameBlockUsed = BooleanArray(gameBoardBlockList.size)

        // println(tableBlockList)
        // println("============")
        // println(gameBoardBlockList)
        // println("============")

        var ret = 0
        tableBlockList.forEach b@{
            it.list.forEach { blockSet->
                gameBoardBlockList.forEachIndexed{ i,v ->
                    if(it.size != v.size) return@forEachIndexed
                    if(gameBlockUsed[i]) return@forEachIndexed
                    if(v.list.contains(blockSet)) {
                        // println("$blockSet == $v")
                        gameBlockUsed[i] = true
                        ret += v.size
                        return@b
                    }
                }
            }
        }

        return ret
    }

    fun findBlock(table: Array<IntArray>, passVal: Int): List<Block> {
        val list = mutableListOf<Block>()

        visited = Array(n) { BooleanArray(n) }
        for(y in 0 until n) {
            for(x in 0 until n) {
                if(visited[y][x]) continue
                if(table[y][x] == passVal) continue

                var blockBoard = getBlockBoard(table, y,x,passVal)

                val block = Block()
                for(i in 0..3) {
                    val ptrSet = getBlockPtrSet(blockBoard, passVal)

                    if(!block.list.contains(ptrSet)) {
                        block.list.add(ptrSet)
                        block.size = ptrSet.size
                    }

                    blockBoard = spinBoard(blockBoard)
                }

                list.add(block)
            }
        }
        return list
    }

    fun spinBoard(board: Array<IntArray>): Array<IntArray> {
        val size = board.size
        val newBoard = Array(size){ IntArray(size) }
        for(py in 0 until size) {
            for(px in 0 until size) {
                newBoard[px][size-py-1] = board[py][px]
            }
        }
        return newBoard
    }

    fun getBlockPtrSet(board: Array<IntArray>, passVal: Int): Set<Pair<Int,Int>> {
        val size = board.size
        val ptrSet = mutableSetOf<Pair<Int,Int>>()
        // blockBoard.forEach { println(it.joinToString()) }
        var minX = Int.MAX_VALUE
        var minY = Int.MAX_VALUE
        for(py in 0 until size) {
            for(px in 0 until size) {
                if(board[py][px]==passVal) continue
                ptrSet.add(Pair(py, px))
                minX = minOf(minX,px)
                minY = minOf(minY,py)
            }
        }

        return ptrSet.map{ Pair(it.first-minY, it.second-minX) }.toSet()
    }

    fun getBlockBoard(board: Array<IntArray>, y: Int, x: Int, passVal: Int): Array<IntArray> {
        val initPtrList = findAdjPtrList(board, y, x, passVal)
        val mxArr = findMinMax(initPtrList)

        // println("$minY-$maxY, $minX-$maxX")
        val size=maxOf(mxArr[maxY]-mxArr[minY], mxArr[maxX]-mxArr[minX])+1

        val fillVal = passVal xor 1
        val blockBoard = Array(size){ IntArray(size) { passVal } }
        initPtrList.forEach {
            blockBoard[it.first-mxArr[minY]][it.second-mxArr[minX]]=fillVal
        }
        return blockBoard
    }

    fun findMinMax(list: List<Pair<Int, Int>>): IntArray {
        val ret = IntArray(4)
        list.forEach {
            ret[minY] = minOf(ret[minY],it.first)
            ret[maxY] = maxOf(ret[maxY],it.first)
            ret[minX] = minOf(ret[minX],it.second)
            ret[maxX] = maxOf(ret[maxX],it.second)
        }
        return ret
    }

    fun findAdjPtrList(board: Array<IntArray>, y: Int, x: Int, passVal: Int): List<Pair<Int, Int>> {
        val q = mutableListOf<Pair<Int,Int>>()
        val initPtrList = mutableListOf(Pair(y,x))

        visited[y][x] = true
        q.add(Pair(y,x))
        while(q.isNotEmpty()) {
            val now = q[0]
            q.removeAt(0)
            for(i in 0 until dy.size) {
                val ny = now.first+dy[i]
                val nx = now.second+dx[i]
                if(!isInBound(ny, nx)) continue
                if(visited[ny][nx]) continue
                if(board[ny][nx]==passVal) continue

                initPtrList.add(Pair(ny,nx))
                q.add(Pair(ny,nx))
                visited[ny][nx] = true
            }
        }

        return initPtrList
    }

    fun isInBound(y: Int, x: Int): Boolean =
        (y in 0 until n) && (x in 0 until n)
}