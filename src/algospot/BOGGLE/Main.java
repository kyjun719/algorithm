package algospot.BOGGLE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {	
	//cache[i][j][k] : 판 (i,j) 글자가 k번째 글자일때 단어 연결 가능 여부
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
					board[i] = tmp.toCharArray();
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
				//판의 글자와 단어의 첫글자가 맞는경우 해당 단어를 연결할 수 있는지 확인
				if(board[i][j] == ch) {
					result = search(j,i,word,0);
					if(result) {
						return result;
					}
				}
			}
		}
		return result;
	}
	
	static int[] dy = {-1,-1,-1, 0,0, 1,1,1};
	static int[] dx = {-1, 0, 1,-1,1,-1,0,1};
	static boolean search(int x, int y, String word, int idx) {
		//다 연결한 경우 참 반
		if(word.length()-1 == idx) {
			return true;
		}
		
		//이전에 계산한 값이 있는 경우 계산값 반환
		if(cache[y][x][idx] != -1) {
			return cache[y][x][idx] == 1;
		}
		
		char nextch = word.charAt(idx+1);
		
		//8방향으로 탐색
		for(int i = 0; i < dy.length; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			//판 범위 확인
			if(nx > 4 || nx < 0 || ny > 4 || ny < 0) {
				continue;
			}
			
			//다음 글자를 연결할 수 있는 경우
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
