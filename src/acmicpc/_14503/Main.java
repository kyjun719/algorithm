package acmicpc._14503;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://www.acmicpc.net/problem/14503
 * @author jun
 *
 */
public class Main {
	static int n,m;
	static int[][] direction = {{-1,0},{0,1},{1,0},{0,-1}};
	static int[] next = {3,0,1,2};
	static int[][] board;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			n = info[0];
			m = info[1];
			
			int[] info2 = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			int y = info2[0];
			int x = info2[1];
			int d = info2[2];
			
			board = new int[n][m];
			for(int i = 0; i < n; i++) {
				board[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			
			System.out.println(solve(x,y,d));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve(int x, int y, int d) {		
		int ret = 0;
		if(board[y][x] == 0) {
			board[y][x] = 2;
			ret = 1;
		}
		
		boolean turned = false;
		int nextD = d;
		for(int i = 0; i < 4; i++) {
			nextD = next[nextD]; 
			if(canGo(x,y,nextD,board)) {
				ret += solve(x+direction[nextD][1], y+direction[nextD][0], nextD);
				turned = true;
				break;
			}
		}
		if(!turned) {
			nextD = (d+2)%4;
			int nextX = x+direction[nextD][1];
			int nextY = y+direction[nextD][0];
			if((board[nextY][nextX] != 1)) {
				ret += solve(nextX, nextY, d);
			}
		}
		return ret;
	}

	private static boolean canGo(int x, int y, int d, int[][] board) {
		int nextX = x+direction[d][1];
		int nextY = y+direction[d][0];
		return board[nextY][nextX] == 0;
	}
}
