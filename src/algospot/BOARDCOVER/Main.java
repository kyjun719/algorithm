package algospot.BOARDCOVER;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {	
	static int[][] board;
	static int h,w;
	static int[][][] block = {
			{{0,0}, {1,0}, {1,1}},
			{{0,0}, {1,0}, {1,-1}},
			{{1,1}, {0,1}, {0,0}},
			{{0,1}, {0,0}, {1,0}}
	};
	public static void main(String[] args) {		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				st = new StringTokenizer(br.readLine());
				h = Integer.parseInt(st.nextToken());
				w = Integer.parseInt(st.nextToken());
				board = new int[h][w];
				
				int left = 0;
				for(int i = 0; i < h; i++) {
					char[] tmp = br.readLine().toCharArray();
					for(int j = 0; j < w; j++) {
						if(tmp[j] == '#') {
							board[i][j] = 1;
						} else {
							left++;
						}
					}
				}

				System.out.println(solve(0,left));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void showBoard() {
		for(int i = 0; i < board.length; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println("=======================");
	}

	private static int solve(int cnt, int left) {
		if(left % 3 != 0) {
			return 0;
		}
		
		if(left == 0) {
			return 1;
		}
		
		if(cnt >= w*h) {
			return 0;
		}
		
		int ret = 0;
		int y = cnt/w;
		int x = cnt%w;
		
		if(board[y][x] == 0) {
			for(int i = 0; i < block.length; i++) {
				if(canCover(y,x,block[i])) {
					coverBoard(y,x,block[i], 1);
					ret += solve(cnt+1,left-3);
					coverBoard(y,x,block[i], 0);
				}
			}
		} else {
			ret += solve(cnt+1, left);
		}
		return ret;
	}

	private static void coverBoard(int y, int x, int[][] block, int val) {
		for(int i = 0; i < block.length; i++) {
			board[block[i][0]+y][block[i][1]+x] = val;
		}
	}

	private static boolean canCover(int y, int x, int[][] block) {
		// TODO Auto-generated method stub
		for(int i = 0; i < block.length; i++) {
			int dy = y + block[i][0];
			int dx = x + block[i][1];
			if(dy >= h || dy < 0 || dx >= w || dx < 0) {
				return false;
			}
			
			if(board[dy][dx] == 1) {
				return false;
			}
		}
		return true;
	}
}
