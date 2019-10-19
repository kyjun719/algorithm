package acmicpc._14890;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://www.acmicpc.net/problem/14890
 * @author jun
 *
 */
public class Main {
	private static int n, l;
	private static int[][] board;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] tmp = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			n = tmp[0];
			l = tmp[1];
			board = new int[n][n];
			for(int i = 0; i < n; i++) {
				board[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			System.out.println(solve());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve() {
		int ret = 0;
		//row
		for(int i = 0; i < n; i++) {
			int cnt = 1;
			boolean canGo = true;
			for(int j = 1; j < n; j++) {
				if(board[i][j] == board[i][j-1]) {
					cnt++;
					continue;
				}
				//down
				if(board[i][j]+1 == board[i][j-1]) {
					if(cnt < 0) {
						canGo = false;
						break;
					}
					cnt = -l+1;
					continue;
				}
				//up
				if(board[i][j] == board[i][j-1]+1) {
					if(cnt < l) {
						canGo = false;
						break;
					}
					cnt = 1;
					continue;
				}
				
				canGo = false;
				break;
			}
			if(cnt < 0) {
				canGo = false;
			}

			if(canGo) {
				ret++;
			}
		}
		
		for(int i = 0; i < n; i++) {
			int cnt = 1;
			boolean canGo = true;
			for(int j = 1; j < n; j++) {
				if(board[j][i] == board[j-1][i]) {
					cnt++;
					continue;
				}
				
				if(board[j][i]+1 == board[j-1][i]) {
					if(cnt < 0) {
						canGo = false;
						break;
					}
					cnt = -l+1;
					continue;
				}
				
				if(board[j][i] == board[j-1][i]+1) {
					if(cnt < l) {
						canGo = false;
						break;
					}
					cnt = 1;
					continue;
				}
				
				canGo = false;
				break;
			}
			if(cnt < 0) {
				canGo = false;
			}
			int[] tmp = new int[n];
			for(int k = 0; k < n; k++) {
				tmp[k] = board[k][i];
			}

			if(canGo) {
				ret++;
			}
		}
			
		return ret;
	}
}
