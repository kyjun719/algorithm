package algospot.JUMPGAME;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static boolean isFinish;
	static int n;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				isFinish = false;
				n = Integer.parseInt(br.readLine());
				int[][] board = new int[n][n];
				for(int i = 0; i < n; i++) {
					board[i] = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
				}
				boolean[][] checked = new boolean[n][n];
				solve(0,0,board,checked);
				System.out.println(isFinish?"YES":"NO");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//오른쪽이나 아래로 움직일 수 있음
	static int[] dir_y = {1,0};
	static int[] dir_x = {0,1};
	private static void solve(int y, int x, int[][] board, boolean[][] checked) {
		// TODO Auto-generated method stub
		//이미 도달한 경우 종료
		if(isFinish) {
			return;
		}
		
		//끝에 도달한 경우 종료
		if(y == n-1 && x == n-1) {
			isFinish = true;
			return;
		}
		
		checked[y][x] = true;
		for(int i = 0; i < 2; i++) {
			int del = board[y][x];
			int dy = y+del*dir_y[i];
			int dx = x+del*dir_x[i];
			if(isInBound(dy,dx) && !checked[dy][dx]) {
				solve(dy,dx,board,checked);
			}
		}
	}
	
	private static boolean isInBound(int dy, int dx) {
		return (dy >= 0) && (dy < n) && (dx >= 0) && (dx < n);
	}
}
