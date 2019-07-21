package algospot.BOGGLE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/BOGGLE
 * @author jun
 * input
1
URLPM
XPRET
GIAET
XTNZY
XOQRS
6
PRETTY
GIRL
REPEAT
KARA
PANDORA
GIAZAPX

 * output
PRETTY YES
GIRL YES
REPEAT YES
KARA NO
PANDORA NO
GIAZAPX YES
 */
public class Main {	
	static int[][][] cache = new int[5][5][10];
	static char[][] board;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				board = new char[5][5];
				for(int i = 0; i < 5; i++) {
					String tmp = br.readLine();
					for(int j = 0; j < 5; j++) {
						board[i][j] = tmp.charAt(j);
					}
				}
				
				int n = Integer.parseInt(br.readLine());
				for(int i = 0; i < n; i++) {
					for(int j = 0; j < 5; j++) {
						for(int k = 0; k < 5; k++) {
							Arrays.fill(cache[j][k], -1);
						}
					}
					String word = br.readLine();
					boolean result = solve(word);
					System.out.println(word + " "+ (result?"YES":"NO"));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean solve(String word) {
		char ch = word.charAt(0);
		boolean result = false;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(board[i][j] == ch) {
					result = search(j,i,word,0);
					if(result) {
						break;
					}
				}
			}
			if(result) {
				break;
			}
		}
		return result;
	}
	
	static int[] dy = {-1,-1,-1,0,0,0,1,1,1};
	static int[] dx = {-1,0,1,-1,0,1,-1,0,1};
	static boolean search(int x, int y, String word, int idx) {
		if(word.length()-1 == idx) {
			return true;
		}
		
		if(cache[y][x][idx] != -1) {
			return cache[y][x][idx] == 1;
		}
		
		char nextch = word.charAt(idx+1);
		
		for(int i = 0; i < 9; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(nx == x && ny == y) {
				continue;
			}
			
			if(nx > 4 || nx < 0 || ny > 4 || ny < 0) {
				continue;
			}
			
			if(nextch == board[ny][nx]) {
				if(search(nx, ny, word, idx+1)) {
					cache[y][x][idx] = 1;
					return true;
				}
			}
		}
		
		cache[y][x][idx] = 0;
		return false;
	}
}
