private class uniquepaths{
    class Solution {
        fun uniquePaths(m: Int, n: Int): Int {
            var visited=Array<IntArray>(m){IntArray(n){-1}}
            return solve(m-1,n-1,visited)
        }
        fun solve(m: Int, n: Int, visited: Array<IntArray>): Int {
            if(m==0 && n==0){
                return 1
            }
            if(visited[m][n]!=-1){
                return visited[m][n]
            }
            var ret=0
            if(m-1>=0){
                ret+=solve(m-1,n,visited)
            }
            if(n-1>=0){
                ret+=solve(m,n-1,visited)
            }
            visited[m][n]=ret
            return ret
        }

        fun uniquePaths2(m: Int, n: Int): Int {
            var arr=Array<IntArray>(m){IntArray(n)}
            for(i in 0..m-1){
                for(j in 0..n-1){
                    if(i==0 || j==0){
                        arr[i][j]=1
                    }else{
                        arr[i][j]=arr[i-1][j]+arr[i][j-1]
                    }
                }
            }
            return arr[m-1][n-1]
        }
    }
}
