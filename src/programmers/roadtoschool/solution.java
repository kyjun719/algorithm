package programmers.roadtoschool;

import java.util.*;
class Solution {
    public int[][] cache;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        cache=new int[n][m];
        for(int i=0;i<n;i++){
            Arrays.fill(cache[i],-1);
        }
        answer=solve(n,m,puddles,n-1,m-1);
        /*
        for(int[] tmp:cache){
            System.out.println(Arrays.toString(tmp));
        }
        */
        return answer;
    }
    public int solve(int n, int m, int[][]puddles, int y, int x){
        if(y==0 && x==0){
            return 1;
        }
        if(cache[y][x]!= -1){
            return cache[y][x];
        }
        int ret = 0;
        if(canGo(n,m,y-1,x,puddles)){
            ret = solve(n,m,puddles,y-1,x);
        }
        
        if(canGo(n,m,y,x-1,puddles)){
            ret += solve(n,m,puddles,y,x-1);
        }
        ret%=1000000007;
        cache[y][x]=ret;
        return ret;
    }
    
    public boolean canGo(int n, int m, int y, int x, int[][]puddles){
        if(y<0 || x<0){
            return false;
        }
        for(int[] tmp : puddles){
            if(tmp[0]-1==x && tmp[1]-1==y){
                return false;
            }
        }
        return true;
    }
}