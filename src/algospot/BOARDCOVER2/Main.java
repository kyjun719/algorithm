package algospot.BOARDCOVER2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @see https://algospot.com/judge/problem/read/BOARDCOVER2
 * @author jun
 * input
2
4 7 2 3
##.##..
#......
#....##
#..####
###
#..
5 10 3 3
..........
..........
..........
..........
..........
.#.
###
..#

 * output
3
8
 */
public class Main {
	static int[][] board;
	static int h,w,r,c,blockSize,answer;
	static List<List<Block>> blockList;
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		try {
			int t = Integer.parseInt(bf.readLine());
			
			for(int tc = 0; tc < t; tc++) {
				String[] tmp = bf.readLine().split(" ");
				h = Integer.parseInt(tmp[0]);
				w = Integer.parseInt(tmp[1]);
				r = Integer.parseInt(tmp[2]);
				c = Integer.parseInt(tmp[3]);
				
				board = new int[h][w];
				answer = 0;
				
				int left = 0;
				for(int i = 0; i < h; i++) {
					String arrTmp = bf.readLine();
					for(int j = 0; j < arrTmp.length(); j++) {
						if(arrTmp.charAt(j) == '#') {
							board[i][j] = 1;
						} else {
							board[i][j] = 0;
							left++;
						}
					}
				}
				
				char[][] block = new char[r][c];
				for(int i = 0; i < r; i++) {
					block[i] = bf.readLine().toCharArray();
				}
				
				setBlock(block);
				/*
				Iterator iter = blockSet.iterator();
				while(iter.hasNext()) {
					System.out.println(iter.next());
				}
				*/
				search(0, left);
				System.out.println(answer);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void setBlock(char[][] block) {
		blockList = new ArrayList<>();
		for(int k = 0; k < 4; k++) {
			int y = -1;
			int x = -1;
			List<Block> tmpBlock = new ArrayList<>();
			for(int i = 0; i < block.length; i++) {
				for(int j = 0; j < block[0].length; j++) {
					if(block[i][j] == '#') {
						if(y == -1) {
							y = i;
							x = j;
						}
						
						tmpBlock.add(new Block(i-y, j-x));
					}
				}
			}

			tmpBlock.sort(new Comparator<Block>() {
				@Override
				public int compare(Block arg0, Block arg1) {
					// TODO Auto-generated method stub
					return arg0.y - arg1.y;
				}
			});
			
			if(!blockList.contains(tmpBlock)) {
				blockList.add(tmpBlock);
			}
			char[][] nextBlock = new char[block[0].length][block.length];
			for(int i = 0; i < block.length; i++) {
				for(int j = 0; j < block[0].length; j++) {
					nextBlock[j][block.length - i -1] = block[i][j];
				}
			}
			block = nextBlock;
		}
		
		blockSize = blockList.get(0).size();
	}
	
	static void search(int placed, int left) {
		if(answer >= placed + left/blockSize) {
			return;
		}
		
		int y = -1;
		int x = -1;
		
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(board[i][j] == 0) {
					y = i;
					x = j;
					break;
				}
			}
			if(y != -1) {
				break;
			}
		}
		
		if(y == -1) {
			answer = Math.max(answer, placed);
			return;
		}
		
		for(int p = 0; p < blockList.size(); p++) {
			List<Block> block = blockList.get(p);
			int k;
			for(k = 0; k < blockSize; k++) {
				int cy = y + block.get(k).y;
				int cx = x + block.get(k).x;
				if(cy < 0 || cy >= h || cx < 0 || cx >= w || board[cy][cx] == 1) {
					break;
				}
			}
			
			if(k == blockSize) {
				for(int m = 0; m < blockSize; m++) {
					int cy = y + block.get(m).y;
					int cx = x + block.get(m).x;
					board[cy][cx] = 1;
				}
				
				search(placed+1, left-blockSize);
				
				for(int m = 0; m < blockSize; m++) {
					int cy = y + block.get(m).y;
					int cx = x + block.get(m).x;
					board[cy][cx] = 0;
				}
			}
		}
		
		board[y][x] = 1;
		search(placed, left-1);
		board[y][x] = 0;
	}
	
	static class Block {
		public int y;
		public int x;
		Block(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		@Override
		public boolean equals(Object o) {
			if(!(o instanceof Block)) {
				return false;
			}
			
			Block block = (Block) o;
			if(this.x == block.x && this.y == block.y) {
				return true;
			}
			
			return false;
		}
		
		@Override
		public int hashCode() {
			return super.hashCode() + x*31 + y;
		}
		
		public String toString() {return "[" + y + "," + x + "]";}
	}
}
