package algospot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see https://algospot.com/judge/problem/read/TICTACTOE
 * @author jun
 * input
3
...
...
...
xx.
oo.
...
xox
oo.
x.x

 * output
TIE
x
o
 */
public class TICTACTOE {
	static int cache[] = new int[19683];
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			for(int tc = 0; tc < c; tc++) {
				char[][] board = new char[3][3];
				Arrays.fill(cache, 2);
				int xCnt = 0;
				int oCnt = 0;
				for(int i = 0; i < 3; i++) {
					char[] tmp = bf.readLine().toCharArray();
					board[i] = tmp;
					for(int j = 0; j < 3; j++) {
						if(tmp[j] == 'x') {
							xCnt++;
						} else if(tmp[j] == 'o') {
							oCnt++;
						}
					}
				}

				char turn = xCnt <= oCnt? 'x':'o';
				int result = canWin(board, turn);
				if(result == 0) {
					System.out.println("TIE");
				} else {
					if(result == 1) {
						System.out.println(turn);
					} else {
						System.out.println(turn == 'x'? 'o':'x');
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int biBoard(char[][] board) {
		int ret = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				ret *= 3;
				if(board[i][j] == 'x') {
					ret += 1;
				} else if(board[i][j] == 'o') {
					ret += 2;
				}
			}
		}
		return ret;
	}
	
	static boolean isFinish(char[][] board, char turn) {
		return getResult(board, turn == 'o'? 'x':'o') == 1? true: false;
	}
	
	static int getResult(char[][] board, char turn) {
		String[] result = new String[8];
		Arrays.fill(result, "");
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				result[i] += board[i][j];
				result[j+3] += board[i][j];
				
				if(i+j == 2) {
					result[6] += board[i][j];
				}
				if(i == j) {
					result[7] += board[i][j];
				}
			}
		}
		
		for(String tmp : result) {
			if(tmp.equals("ooo") || tmp.equals("xxx")) {
				return tmp.charAt(0) == turn? 1: -1;
			}
		}
		
		return 0;
	}
	
	static int canWin(char[][] board, char turn) {
		if(isFinish(board, turn)) {
			return -1;
		}
		
		int index = biBoard(board);
		if(cache[index] != 2) {
			return cache[index];
		}
		
		int ret = 2; 
		for(int i = 0; i <3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == '.') {
					board[i][j] = turn;
					ret = Math.min(ret, canWin(board, turn == 'o'? 'x':'o'));
					board[i][j] = '.';
				}
			}
		}
		
		if(ret == 2 || ret == 0) {
			ret = 0;
		} else {
			ret = -ret;
		}
		cache[index] = ret;
		return ret;
	}
}
