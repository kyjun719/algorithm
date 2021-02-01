class Solution {
    fun rotate(matrix: Array<IntArray>): Unit {
        var n=matrix.size
        for(i in 0..(n-1)/2){
            for(j in i..n-i-2){
                var tmp=matrix[i][j]
				//왼쪽 위
                matrix[i][j]=matrix[n-j-1][i]
				//왼쪽 아래
                matrix[n-j-1][i]=matrix[n-i-1][n-j-1]
				//오른쪽 아래
                matrix[n-i-1][n-j-1]=matrix[j][n-i-1]
				//오른쪽 위
                matrix[j][n-i-1]=tmp
                //printMatrix(matrix)
                //println()
            }
        }
    }
    fun printMatrix(matrix: Array<IntArray>){
        matrix.forEach{
            println(it.joinToString())
        }
    }
}