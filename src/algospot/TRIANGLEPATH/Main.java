package algospot.TRIANGLEPATH;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int n;
	static int[][] arr;
	static int[][] cache;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				n = Integer.parseInt(br.readLine());
				arr = new int[n][n];
				cache = new int[n][n];
				for(int i = 0; i < n; i++) {
					arr[i] = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
					Arrays.fill(cache[i], -1);
				}
				System.out.println(solve(0, 0));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve(int row, int col) {
		// TODO Auto-generated method stub
		if(row == n) {
			return 0;
		}
		
		if(cache[row][col] != -1) {
			return cache[row][col];
		}
		
		int ret = arr[row][col] + Math.max(solve(row+1, col), solve(row+1, col+1));
		cache[row][col] = ret;
		
		return ret;
	}
}
