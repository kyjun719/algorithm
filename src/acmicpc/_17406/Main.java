package acmicpc._17406;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static int n,m,k;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			n = info[0];
			m = info[1];
			k = info[2];
			int[][] board = new int[n][m];
			for(int i = 0; i < n; i++) {
				board[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			int[][] dir = new int[k][3];
			for(int i = 0; i < k; i++) {
				dir[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			
			boolean[] checked = new boolean[k];
			System.out.println(solve(board, dir, checked, 0));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve(int[][] board, int[][] dir, boolean[] checked, int cnt) {
		if(cnt == k) {
			return calcMin(board);
		}
		
		int ret = 987654321;
		for(int i = 0; i < k; i++) {
			if(!checked[i]) {
				int[][] copyBoard = new int[n][m];
				for(int j = 0; j < n; j++) {
					copyBoard[j] = Arrays.copyOf(board[j], m);
				}
				rotate(copyBoard, dir[i]);
				checked[i] = true;
				ret = Math.min(ret, solve(copyBoard,dir,checked,cnt+1));
				checked[i] = false;
			}
		}
		return ret;
	}

	private static int calcMin(int[][] board) {
		int ret = 987654321;
		for(int i = 0; i < n; i++) {
			ret = Math.min(ret, Arrays.stream(board[i]).sum());
		}
		return ret;
	}

	private static void rotate(int[][] copyBoard, int[] dir) {
		int r = dir[0]-1;
		int c = dir[1]-1;
		int del = dir[2];
		for(int i = del; i > 0; i--) {
			int startx = c-i;
			int endx = c+i;
			int starty = r-i;
			int endy = r+i;
			int[] tmpArr = {copyBoard[starty][startx],copyBoard[starty][endx],
					copyBoard[endy][endx],copyBoard[endy][startx]};
			for(int tmp = 2*i; tmp > 0; tmp--) {
				//1,1->1,2, 1,2->1,3, ... 1,n-2->1,n-1
				copyBoard[starty][startx+tmp] = copyBoard[starty][startx+tmp-1];
				
				//1,n-1->2,n-1, 2,n-1->3,n-1, ... n-2,n-1->n-1,n-1
				copyBoard[starty+tmp][endx] = copyBoard[starty+tmp-1][endx];
				
				//n-1,n-1->n-1,n-2, n-1,n-2->n-1,n-3, ... n-1,2->n-1,1
				copyBoard[endy][endx-tmp] = copyBoard[endy][endx-tmp+1];
				
				//n-1,1->n-2,1, ... 2,1->1,1
				copyBoard[endy-tmp][startx] = copyBoard[endy-tmp+1][startx];
			}
			copyBoard[starty][startx+1] = tmpArr[0];
			copyBoard[starty+1][endx] = tmpArr[1];
			copyBoard[endy][endx-1] = tmpArr[2];
			copyBoard[endy-1][startx] = tmpArr[3];
		}
	}
}
