package acmicpc._17136;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static int MAX = 987654321;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[][] board = new int[10][10];
			int left = 0;
			for(int i = 0; i < 10; i++) {
				board[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				left += Arrays.stream(board[i]).sum();
			}
			int[] cnt = new int[5];
			for(int i = 0; i < 5; i++) {
				cnt[i] = 5;
			}
			int ret = solve(0,0,left,board,cnt);
			System.out.println(ret == MAX?-1:ret);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve(int y, int x, int left, int[][] board, int[] cnt) {
		if(left == 0) {
			return 0;
		}
		
		if(y > 9) {
			return MAX;
		}
		
		int nextX=x, nextY=y;
		if(x == 9) {
			nextX = 0;
			nextY++;
		} else {
			nextX++;
		}
		
		int ret = MAX;
		if(board[y][x] == 1) {
			for(int i = 0; i < 5; i++) {
				if(cnt[i] > 0 && canCover(y,x,board,i+1)) {
					coverBoard(y,x,board,i+1,0);
					cnt[i]--;
					int nextLeft = left-(i+1)*(i+1);
					ret = Math.min(ret, solve(nextY,nextX,nextLeft,board,cnt)+1);
					coverBoard(y,x,board,i+1,1);
					cnt[i]++;
				}
			}
		} else {
			ret = solve(nextY,nextX,left,board,cnt);
		}
		return ret;
	}

	private static void showBoard(int[][] board) {
		for(int i = 0; i < 10; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println("===========================");
	}

	private static boolean canCover(int y, int x, int[][] board, int size) {
		if((y+size>10) || (x+size>10)) {
			return false;
		}
		
		for(int i = 0; i <size; i++) {
			for(int j = 0; j < size; j++) {
				if(board[y+i][x+j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static void coverBoard(int y, int x, int[][] board, int size, int val) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				board[y+i][x+j] = val;
			}
		}
	}
}
