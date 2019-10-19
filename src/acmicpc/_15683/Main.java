package acmicpc._15683;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @see https://www.acmicpc.net/problem/15683
 * @author jun
 *
 */
public class Main {
	private static int[][][] camera1 = {{{0,1}},{{0,-1}},{{1,0}},{{-1,0}}};
	private static int[][][] camera2 = {{{0,1},{0,-1}},{{1,0},{-1,0}}};
	private static int[][][] camera3 = {{{0,1},{-1,0}},{{-1,0},{0,-1}},{{0,-1},{1,0}},{{1,0},{0,1}}};
	private static int[][][] camera4 = {{{0,1},{-1,0},{0,-1}},
												{{-1,0},{0,-1},{1,0}},
												{{0,-1},{1,0},{0,1}},
												{{1,0},{0,1},{-1,0}}};
	private static int[][][] camera5 = {{{0,1},{-1,0},{0,-1},{1,0}}};
	
	private static int n,m;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			n = info[0];
			m = info[1];
			int[][] board = new int[n][m];
			ArrayList<Integer[]> cameraPtr = new ArrayList<>();
			for(int i = 0; i < n; i++) {
				board[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				for(int j = 0; j < m; j++) {
					if(board[i][j] > 0 && board[i][j] < 6) {
						cameraPtr.add(new Integer[] {i,j});
					}
				}
			}
			System.out.println(solve(cameraPtr, 0, board));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve(ArrayList<Integer[]> cameraPtr, int idx, int[][] board) {
		// TODO Auto-generated method stub
		if(cameraPtr.size() == idx) {
			return calArea(board);
		}
		
		Integer[] ptr = cameraPtr.get(idx);
		int num = board[ptr[0]][ptr[1]];
		int ret = 987654321;
		switch(num) {
			case 1:
				for(int i = 0; i < camera1.length; i++) {
					int[][] nextBoard = new int[n][m];
					for(int j = 0; j < n; j++) {
						nextBoard[j] = Arrays.copyOf(board[j], m);
					}
					setBoard(ptr, camera1[i], nextBoard, -1);
					ret = Math.min(ret, solve(cameraPtr, idx+1, nextBoard));
				}
				return ret;
			case 2:
				for(int i = 0; i < camera2.length; i++) {
					int[][] nextBoard = new int[n][m];
					for(int j = 0; j < n; j++) {
						nextBoard[j] = Arrays.copyOf(board[j], m);
					}
					setBoard(ptr, camera2[i], nextBoard, -1);
					ret = Math.min(ret, solve(cameraPtr, idx+1, nextBoard));
				}
				return ret;
			case 3:
				for(int i = 0; i < camera3.length; i++) {
					int[][] nextBoard = new int[n][m];
					for(int j = 0; j < n; j++) {
						nextBoard[j] = Arrays.copyOf(board[j], m);
					}
					setBoard(ptr, camera3[i], nextBoard, -1);
					ret = Math.min(ret, solve(cameraPtr, idx+1, nextBoard));
				}
				return ret;
			case 4:
				for(int i = 0; i < camera4.length; i++) {
					int[][] nextBoard = new int[n][m];
					for(int j = 0; j < n; j++) {
						nextBoard[j] = Arrays.copyOf(board[j], m);
					}
					setBoard(ptr, camera4[i], nextBoard, -1);
					ret = Math.min(ret, solve(cameraPtr, idx+1, nextBoard));
				}
				return ret;
			case 5:
				for(int i = 0; i < camera5.length; i++) {
					int[][] nextBoard = new int[n][m];
					for(int j = 0; j < n; j++) {
						nextBoard[j] = Arrays.copyOf(board[j], m);
					}
					setBoard(ptr, camera5[i], nextBoard, -1);
					ret = Math.min(ret, solve(cameraPtr, idx+1, nextBoard));
				}
				return ret;
		}
		return ret;
	}

	private static void setBoard(Integer[] ptr, int[][] is, int[][] board, int val) {
		for(int i = 0; i < is.length; i++) {
			int[] dir = is[i];
			int[] tmp = new int[] {ptr[0],ptr[1]};
			while(true) {
				tmp[0] += dir[0];
				tmp[1] += dir[1];
				
				if(!((tmp[0] >= 0) && (tmp[0] < n) && (tmp[1] >= 0) && (tmp[1] < m))) {
					break;
				}
				if(board[tmp[0]][tmp[1]] == 6) {
					break;
				}
				if(board[tmp[0]][tmp[1]] <= 0) {
					board[tmp[0]][tmp[1]] = val;
				}
			}
		}
	}

	private static int calArea(int[][] board) {
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(board[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}
