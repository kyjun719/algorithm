package acmicpc._17281;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static int n;
	private static int[][] board;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			board = new int[n][9];
			for(int i = 0; i < n; i++) {
				board[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			int[] hitter = new int[9];
			boolean[] isSelected = new boolean[9];
			System.out.println(solve(0,isSelected,hitter));
			
			hitter[0] = 3;
			hitter[1] = 2;
			hitter[2] = 1;
			hitter[3] = 0;
			hitter[4] = 8;
			hitter[5] = 7;
			hitter[6] = 6;
			hitter[7] = 5;
			hitter[8] = 4;
			//System.out.println(calScore(hitter));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve(int idx, boolean[] isSelected, int[] hitter) {
		if(idx == 9) {
			return calScore(hitter);
		}
		
		int ret = 0;
		
		if(idx == 3) {
			isSelected[0] = true;
			hitter[idx] = 0;
			ret = Math.max(ret, solve(idx+1,isSelected,hitter));
		} else {
			for(int i = 0;i<9;i++) {
				if(i == 0) {
					continue;
				}
				if(!isSelected[i]) {
					isSelected[i] = true;
					hitter[idx] = i;
					ret = Math.max(ret, solve(idx+1,isSelected,hitter));
					isSelected[i] = false;
					hitter[idx] = 0;
				}
			}
		}
		
		return ret;
	}
	
	private static int calScore(int[] hitter) {
		int idx = 0;
		int ret = 0;
		for(int i = 0; i < n; i++) {
			int outCnt = 0;
			int game = 0;
			int score = 0;
			while(outCnt < 3) {
				if(board[i][hitter[idx]]==0) {
					outCnt++;
				} else if(board[i][hitter[idx]] == 4){
					score += 1+Integer.bitCount(game);
					game = 0;
				} else {
					game <<= board[i][hitter[idx]];
					game |= 1<<(board[i][hitter[idx]]-1);
					score += Integer.bitCount(game>>3);
					game &= 7;
				}
				idx = (idx+1)%9;
			}
			ret += score;
		}
		return ret;
	}
}
