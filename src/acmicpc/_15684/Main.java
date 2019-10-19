package acmicpc._15684;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://www.acmicpc.net/problem/15684
 * @author jun
 *
 */
public class Main {
	private static int n,m,h;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			n = info[0]-1;
			m = info[1];
			h = info[2];
			if(m == 0) {
				System.out.println(0);
			} else {
				int[][] board = new int[h][n];
				for(int i = 0; i < m; i++) {
					int[] tmp = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
					board[tmp[0]-1][tmp[1]-1] = 1;
				}
				int ret = solve(board,0,0);
				System.out.println(ret == 987654321?-1:ret);
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve(int[][] board, int num, int cnt) {
		if(cnt > 3) {
			return 987654321;
		}
		
		if(isFinish(board)) {
			return cnt;
		}
		
		int ret = 987654321;
		int col = num%n;
		int row = num/n;
		for(int i = row; i < h; i++) {
			int start = i == row?col:0;
			for(int j = start; j < n; j++) {
				if(canDraw(board,i,j)) {
					board[i][j] = 1;
					ret = Math.min(ret, solve(board,i*n+j,cnt+1));
					board[i][j] = 0;
				}
			}
		}
		
		return ret;
	}

	private static boolean canDraw(int[][] board, int row, int col) {
		boolean ret = true;
		if(board[row][col] == 1) {
			ret = false;
		}
		if(col > 0) {
			ret &= board[row][col-1] == 0;
		}
		if(col < n-1) {
			ret &= board[row][col+1] == 0;
		}
		return ret;
	}

	private static boolean isFinish(int[][] board) {		
		int[] arr = new int[n+1];
		for(int i = 0; i <= n; i++) {
			arr[i] = i+1;
		}
		
		for(int row = 0; row < h; row++) {
			for(int col = 0; col < n; col++) {
				if(board[row][col] == 1) {
					int tmp = arr[col];
					arr[col] = arr[col+1];
					arr[col+1] = tmp;
				}
			}
		}

		for(int i = 0; i <= n; i++) {
			if(arr[i] != (i+1)) {
				return false;
			}
		}
		return true;
	}
}
