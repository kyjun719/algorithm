package acmicpc._9663;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int[n][n];
			System.out.println(solve(arr,0,n));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve(int[][] arr, int d, int n) {
		if(d==n) {
			/*
			for(int i = 0; i < n; i++) {
				System.out.println(Arrays.toString(arr[i]));
			}
			System.out.println();
			*/
			return 1;
		}
		
		int ret = 0;
		for(int i = 0; i < n; i++) {
			if(canDo(arr,d,i,n)) {
				arr[d][i]=1;
				ret += solve(arr,d+1,n);
				arr[d][i]=0;
			}
		}
		return ret;
	}

	private static boolean canDo(int[][] arr, int r, int c, int n) {
		for(int i = 0; i <= r; i++) {
			if(arr[i][c]==1) {
				return false;
			}
			if(r-i>=0 && c-i>=0 && arr[r-i][c-i]==1) {
				return false;
			}
			if(r-i>=0 && c+i<n && arr[r-i][c+i]==1) {
				return false;
			}
		}
		
		return true;
	}
}
