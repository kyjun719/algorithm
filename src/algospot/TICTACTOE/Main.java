package algospot.TICTACTOE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	//i는 판정보로 한칸에 x일경우1, o일경우2를 더해 총 저장해야 하는 판의 갯수는 3^9 
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
				//현재 게임판 정보 및 다음 차례 저장
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

				//놓인 갯수로 다음차례 검색
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
	
	static int canWin(char[][] board, char turn) {
		//놓기 전 게임이 끝난 경우 이전차례의 사람 승리
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
				//해당칸에 말을 놓을수 있으면 놓고 다음 차례로 넘어감
				if(board[i][j] == '.') {
					board[i][j] = turn;
					ret = Math.min(ret, canWin(board, turn == 'o'? 'x':'o'));
					board[i][j] = '.';
				}
			}
		}
		//말을 놓지 못하거나 비긴경우
		if(ret == 2 || ret == 0) {
			ret = 0;
		} else {
			//다음차례가 진 경우 현재차례는 이김
			//다음차례가 이기니 경우 현재차례는 짐
			ret = -ret;
		}
		cache[index] = ret;
		return ret;
	}
	
	//현재 게임판에 대한 값 반환
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
	
	//이전차례의 사람의 승리여부 반환
	static boolean isFinish(char[][] board, char turn) {
		return getResult(board, turn == 'o'? 'x':'o') == 1? true: false;
	}
	
	//해당 차례의 사람 승리여부 반환 
	static int getResult(char[][] board, char turn) {
		//0~2:가로줄, 3~5: 세로줄, 6:/ 대각선, 7:\대각선 
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
			//한줄을 채운경우 차례에 따라 승리여부 반환
			if(tmp.equals("ooo") || tmp.equals("xxx")) {
				return tmp.charAt(0) == turn? 1: -1;
			}
		}
		
		//아직 게임이 끝나지 않음
		return 0;
	}
}
