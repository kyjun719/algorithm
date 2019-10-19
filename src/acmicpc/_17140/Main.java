package acmicpc._17140;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @see @see https://www.acmicpc.net/problem/17140
 * @author jun
 *
 */
public class Main {
	static int r,c,k;
	static int[][] board;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			r = info[0]-1;
			c = info[1]-1;
			k = info[2];
			
			board = new int[100][100];
			for(int i = 0; i < 3; i++) {
				board[i] = Arrays.copyOf(Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray(), 100);
			}
			System.out.println(solve(3,3));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static int MAX_CNT = 987654321;
	private static class NumInfo {
		int cnt,num;
		NumInfo(int num) {
			this.num = num;
			this.cnt = MAX_CNT;
		}
		@Override
		public String toString() {
			return num+"::"+cnt;
		}
	}
	private static int solve(int row, int col) {
		int cnt;
		boolean isFind = false;
		for(cnt=0; cnt <= 100; cnt++) {
			if(board[r][c] == k) {
				isFind = true;
				break;
			}
			if(row >= col) {
				//R operator
				for(int y = 0; y < row; y++) {
					NumInfo[] arr = new NumInfo[101];
					
					for(int i = 0; i <= 100; i++) {
						arr[i] = new NumInfo(i);
					}
					
					for(int x = 0; x < col; x++) {
						if(board[y][x] == 0) {
							continue;
						}
						if(arr[board[y][x]].cnt == MAX_CNT) {
							arr[board[y][x]].cnt = 0;
						}
						arr[board[y][x]].cnt++;
						arr[board[y][x]].num = board[y][x]; 
					}
					Arrays.sort(arr, new Comparator<NumInfo>() {
						@Override
						public int compare(NumInfo arg0, NumInfo arg1) {
							// TODO Auto-generated method stub
							if((arg0.cnt != MAX_CNT) && (arg0.cnt == arg1.cnt)) {
								return arg0.num-arg1.num;
							}
							return arg0.cnt-arg1.cnt;
						}
					});
					//System.out.println(Arrays.toString(arr));
					
					int idx=-1;
					int[] newBoard = new int[100];
					for(NumInfo info : arr) {
						if(info.cnt == MAX_CNT) {
							break;
						}
						newBoard[++idx]=info.num;
						newBoard[++idx]=info.cnt;
					}
					board[y] = newBoard;
					col = Math.max(col, idx+1);
				}
			} else {
				//C operator
				for(int x = 0; x < col; x++) {
					NumInfo[] arr = new NumInfo[101];
					
					for(int i = 0; i <= 100; i++) {
						arr[i] = new NumInfo(i);
					}
					
					for(int y = 0; y < row; y++) {
						if(board[y][x] == 0) {
							continue;
						}
						if(arr[board[y][x]].cnt == MAX_CNT) {
							arr[board[y][x]].cnt = 0;
						}
						arr[board[y][x]].cnt++;
						arr[board[y][x]].num = board[y][x]; 
					}
					Arrays.sort(arr, new Comparator<NumInfo>() {
						@Override
						public int compare(NumInfo arg0, NumInfo arg1) {
							// TODO Auto-generated method stub
							if((arg0.cnt != MAX_CNT) && (arg0.cnt == arg1.cnt)) {
								return arg0.num-arg1.num;
							}
							return arg0.cnt-arg1.cnt;
						}
					});
					//System.out.println(Arrays.toString(arr));
					
					int idx=-1;
					int[] newBoard = new int[100];
					for(NumInfo info : arr) {
						if(info.cnt == MAX_CNT) {
							break;
						}
						newBoard[++idx]=info.num;
						newBoard[++idx]=info.cnt;
					}
					for(int i = 0; i <= idx; i++) {
						board[i][x] = newBoard[i];
					}
					for(int i = idx+1; i < row; i++) {
						board[i][x] = 0;
					}
					row = Math.max(row, idx+1);
				}
			}
			//System.out.println(row+","+col);
			//showBoard(row,col);
		}
		
		return isFind?cnt:-1;
	}
	
	private static void showBoard(int row, int col) {
		for(int i = 0; i < row; i++) {
			System.out.println(Arrays.toString(Arrays.copyOf(board[i], col)));
		}
		System.out.println("=========================");
	}
	
}
