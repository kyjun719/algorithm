package algospot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see https://algospot.com/judge/problem/read/BLOCKGAME
 * @author jun
 * input
3
.....
.##..
##..#
#.###
..#..
.....
.....
.....
.....
.....
#..##
##.##
##.##
#...#
##.##

 * output
WINNING
LOSING
WINNING
 */
public class BLOCKGAME {
	static byte[] cache = new byte[1<<25];
	static List<Integer> blockList = new ArrayList<>();
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		preCal();
		try {
			int c = Integer.parseInt(bf.readLine());
			for(int tc = 0; tc < c; tc++) {
				Arrays.fill(cache, (byte) 0x00);
				
				int board = 0;
				
				for(int i = 0; i < 5; i++) {
					char[] tmp = bf.readLine().toCharArray();
					for(int j = 0; j < 5; j++) {
						if(tmp[j] == '#') {
							board += cell(i, j);
						}
					}
				}
				System.out.println(game(board) == 2?"WINNING":"LOSING");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int cell(int y, int x) {
		return 1<<(y*5+x);
	}
	
	static void preCal() {
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 4; col++) {
				int sum = 0;
				int[] cellArr = new int[4];
				for(int dy = 0; dy < 2; dy++) {
					for(int dx = 0; dx < 2; dx++) {
						cellArr[dy*2+dx] = cell(row+dy, col+dx);
						sum += cellArr[dy*2+dx];
					}
				}
				for(int tmp : cellArr) {
					blockList.add(sum-tmp);
				}
			}
		}
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 4; j++) {
				blockList.add(cell(i, j) + cell(i, j+1));
				blockList.add(cell(j, i) + cell(j+1, i));
			}
		}
	}
	
	static int game(int board) {
		if(cache[board] != 0) {
			return cache[board];
		}
		
		int ret = 1;
		for(int block : blockList) {
			if((board & block) == 0) {
				if(game(board | block) == 1) {
					ret = 2;
					break;
				}
			}
		}
		cache[board] = (byte) ret;
		return ret;
	}
}
