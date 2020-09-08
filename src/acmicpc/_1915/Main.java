package acmicpc._1915;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		//https://private-space.tistory.com/25
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int n = info[0];
			int m = info[1];
			int[][] arr = new int[n][m];
			for(int i = 0; i<n; i++) {
				String tmp = br.readLine();
				for(int j = 0; j < m; j++) {
					arr[i][j] = tmp.charAt(j)-'0';
				}
			}
			
			//System.out.println(solve(arr,n,m));
			System.out.println(solve2(arr,n,m));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve(int[][] arr, int n, int m) {
		int max=0;
		for(int y = 0; y < n; y++) {
			for(int x=0; x<n; x++) {
				if(arr[y][x]==0) {
					continue;
				}
				
				int len=0;
				
				while(true) {
					int cnt=1;
					for(int i = 0; i < len; i++) {
						cnt+=arr[y+len][x+i];
						cnt+=arr[y+i][x+len];
					}
					if(cnt!=(2*len+1)) {
						break;
					}
					len++;
					if(len+x>=m || len+y>=n) {
						len--;
						break;
					}
				}
				max=Math.max(max, len*len);
			}
		}
		return max;
	}
	
	private static int solve2(int[][] arr, int n, int m) {
		int[][] cntArr = new int[n+1][m+1];
		
		int len=0;
		for(int y=1; y <= n; y++) {
			for(int x=1; x<=m; x++) {
				if(arr[y-1][x-1]==1) {
					cntArr[y][x]=Math.min(cntArr[y-1][x-1], Math.min(cntArr[y-1][x], cntArr[y][x-1]))+1;
					len=Math.max(len, cntArr[y][x]);
				}
			}
		}

		return len*len;
	}
}

