package acmicpc._12100;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://www.acmicpc.net/problem/12100
 * @author jun
 *
 */
public class Main {
	static int n;
	static int ret;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			int[][] board = new int[n][n];
			for(int i = 0; i < n; i++) {
				board[i] = Arrays.stream(br.readLine().split(" "))
								.mapToInt(Integer::parseInt)
								.toArray();
			}
			ret = 0;
			solve(board, 0);
			System.out.println(ret);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	private static final int UP = 2;
	private static final int DOWN = 3;
	private static final int MAX_CNT = 4;
	private static void solve(int[][] board, int cnt) {
		if(cnt > MAX_CNT) {
			//find max value
			int tmp = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					tmp = tmp > board[i][j] ? tmp : board[i][j];
				}
			}
			ret = ret > tmp ? ret : tmp;
			return;
		}
		
		for(int dir = 0; dir < 4; dir++) {
			//copy to new board
			int[][] newBoard = new int[n][n];
			for(int i = 0; i < n; i++) {
				newBoard[i] = Arrays.copyOf(board[i], n);
			}
			//move
			newBoard = move(newBoard, dir);
			//merge
			newBoard = merge(newBoard, dir);
			//remove 0 after merge
			newBoard = move(newBoard, dir);
			
			solve(newBoard,cnt+1);
		}
	}
	
	private static int[][] merge(int[][] board, int dir) {
		switch(dir) {
			case LEFT:
				for(int y = 0; y < n; y++) {
					for(int x = 0; x < n-1; x++) {
						if((board[y][x] != 0) && (board[y][x] == board[y][x+1])) {
							board[y][x] += board[y][x+1];
							board[y][x+1] = 0;
						}
					}
				}
				break;
			case RIGHT:
				for(int y = 0; y < n; y++) {
					for(int x = n-1; x > 0; x--) {
						if((board[y][x] != 0) && (board[y][x] == board[y][x-1])) {
							board[y][x] += board[y][x-1];
							board[y][x-1] = 0;
						}
					}
				}
				break;
			case UP:
				for(int x = 0; x < n; x++) {
					for(int y = 0; y < n-1; y++) {
						if((board[y][x] != 0) && (board[y][x] == board[y+1][x])) {
							board[y][x] += board[y+1][x];
							board[y+1][x] = 0;
						}
					}
				}
				break;
			case DOWN:
				for(int x = 0; x < n; x++) {
					for(int y = n-1; y > 0; y--) {
						if((board[y][x] != 0) && (board[y][x] == board[y-1][x])) {
							board[y][x] += board[y-1][x];
							board[y-1][x] = 0;
						}
					}
				}
				break;
		}
		// TODO Auto-generated method stub
		return board;
	}

	private static int[][] move(int[][] board, int dir) {
		switch(dir) {
			case LEFT:
				for(int y = 0; y < n; y++) {
					int newIdx = 0;
					for(int x = 0; x < n; x++) {
						if(board[y][x] != 0) {
							int tmp = board[y][x];
							board[y][x] = 0;
							board[y][newIdx] = tmp;
							newIdx++;
						}
					}
				}
				break;
			case RIGHT:
				for(int y = 0; y < n; y++) {
					int newIdx = n-1;
					for(int x = n-1; x >= 0; x--) {
						if(board[y][x] != 0) {
							int tmp = board[y][x];
							board[y][x] = 0;
							board[y][newIdx] = tmp;
							newIdx--;
						}
					}
				}
				break;
			case UP:
				for(int x = 0; x < n; x++) {
					int newIdx = 0;
					for(int y = 0; y < n; y++) {
						if(board[y][x] != 0) {
							int tmp = board[y][x];
							board[y][x] = 0;
							board[newIdx][x] = tmp;
							newIdx++;
						}
					}
				}
				break;
			case DOWN:
				for(int x = 0; x < n; x++) {
					int newIdx = n-1;
					for(int y = n-1; y >= 0; y--) {
						if(board[y][x] != 0) {
							int tmp = board[y][x];
							board[y][x] = 0;
							board[newIdx][x] = tmp;
							newIdx--;
						}
					}
				}
				break;
		}
		// TODO Auto-generated method stub
		return board;
	}

	private static void showBoard(int[][] board) {
		for(int i = 0; i < board.length; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
	}

	private static boolean isInBound(int[] ptr) {
		return (ptr[0] >= 0 && ptr[0] < n) && (ptr[1] >= 0 && ptr[1] < n);
	}

	private static boolean isSameNextCell(int[][] board, int[] ptr, int[] dir) {
		if(!isInBound(new int[] {ptr[0]+dir[0], ptr[1]+dir[1]} )) {
			return false;
		}
		int a = board[ptr[0]][ptr[1]];
		int b = board[ptr[0]+dir[0]][ptr[1]+dir[1]];
		return a == b;
	}
}
