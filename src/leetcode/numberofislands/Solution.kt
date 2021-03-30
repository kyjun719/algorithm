class Solution {
    var cnt=0
    var n=0
    var m=0
    val del=arrayOf(-1,1)
    lateinit var visited: Array<BooleanArray>
    fun numIslands(grid: Array<CharArray>): Int {
        n=grid.size
        m=grid[0].size
        visited=Array(n){
            BooleanArray(m)
        }
        dfs(grid)
        return cnt
    }
    fun dfs(grid: Array<CharArray>){
        for(k in 0..m*n-1){
            var y=k/m
            var x=k%m
            if(!visited[y][x] && grid[y][x]=='1'){
                cnt+=1
                var q=mutableListOf(k)
                visited[y][x]=true
                while(q.size>0){
                    var a=q.removeAt(0)
                    var yy=a/m
                    var xx=a%m
                    for(yd in del){
                        if(canGo(grid,yy+yd,xx)){
                            visited[yy+yd][xx]=true
                            q.add((yy+yd)*m+(xx))
                        }
                    }
                    for(xd in del){
                        if(canGo(grid,yy,xx+xd)){
                            visited[yy][xx+xd]=true
                            q.add((yy)*m+(xx+xd))
                        }
                    }
                }
                //visited.forEach{println(it.joinToString())}
            }
        }
    }
    fun canGo(grid: Array<CharArray>, y: Int, x: Int): Boolean {
        return y<n && y>=0 && x<m && x>=0 && !visited[y][x] && grid[y][x]=='1'
    }
}