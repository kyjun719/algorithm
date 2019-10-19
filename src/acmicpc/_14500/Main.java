package acmicpc._14500;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://www.acmicpc.net/problem/14500
 * @author jun
 *
 */
public class Main {
	static int Y = 0;
	static int X = 1;
	//ㅣ
	static int[][][] c = { {{0,0},{0,1},{0,2},{0,3}}, {{0,0},{1,0},{2,0},{3,0}} };
	//ㅁ
	static int[][][] y = { {{0,0},{0,1},{1,1},{1,0}} };
	
	//ㄴ
	static int[][][] o = {{{0,0},{1,0},{2,0},{2,1}},
								{{1,0},{0,0},{0,1},{0,2}},
								{{0,0},{0,1},{1,1},{2,1}},
								{{1,0},{1,1},{1,2},{0,2}},
								
								{{0,1},{1,1},{2,1},{2,0}},
								{{0,0},{1,0},{1,1},{1,2}},
								{{0,0},{0,1},{1,0},{2,0}},
								{{0,0},{0,1},{0,2},{1,2}},
								};
	//ㄴㄱ
	static int[][][] g = {{{0,0},{1,0},{1,1},{2,1}}, 
								{{1,0},{1,1},{0,1},{0,2}},
	
								{{0,1},{1,1},{1,0},{2,0}}, 
								{{0,0},{0,1},{1,1},{1,2}},
								};
	//ㅗ
	static int[][][] p = {{{0,0},{0,1},{0,2},{1,1}},
								{{1,0},{0,1},{1,1},{2,1}},
								{{1,0},{1,1},{1,2},{0,1}},
								{{0,0},{1,0},{2,0},{1,1}},
								};
	
	static int n,m;
	static int[][] board;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] nm = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			n = nm[0];
			m = nm[1];
			board = new int[n][m];
			for(int i = 0; i < n; i++) {
				board[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			int ret = solve();
			System.out.println(ret);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static int solve() {
		int ret = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				ret = Math.max(ret, getMaxVal(i,j,c));
				ret = Math.max(ret, getMaxVal(i,j,y));
				ret = Math.max(ret, getMaxVal(i,j,o));
				ret = Math.max(ret, getMaxVal(i,j,g));
				ret = Math.max(ret, getMaxVal(i,j,p));
			}
		}
		return ret;
	}

	private static int getMaxVal(int y, int x, int[][][] c) {
		int ret = 0;
		for(int i = 0; i < c.length; i++) {
			boolean isSuccess = true;
			int tmp = 0;
			int[][] dir = c[i];
			for(int j = 0; j < dir.length; j++) {
				if(isInBound(y+dir[j][Y], x+dir[j][X])) {
					tmp += board[y+dir[j][Y]][x+dir[j][X]];
				} else {
					isSuccess = false;
					break;
				}
			}
			
			if(isSuccess) {
				ret = Math.max(ret, tmp);
			}
		}
		
		return ret;
	}

	private static boolean isInBound(int y, int x) {
		return (y >= 0) && (y < n) && (x >= 0) && (x < m);
	}
}
