package kaako.test2019;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @see  https://programmers.co.kr/learn/courses/30/lessons/42894
 * @author jun

10
0,0,0,0,0,0,0,0,0,0
0,0,0,0,0,0,0,0,0,0
0,0,0,0,0,0,0,0,0,0
0,0,0,0,0,0,0,0,0,0
0,0,0,0,0,0,4,0,0,0
0,0,0,0,0,4,4,0,0,0
0,0,0,0,3,0,4,0,0,0
0,0,0,2,3,0,0,0,5,5
1,2,2,2,3,3,0,0,0,5
1,1,1,0,0,0,0,0,0,5

2
 */
public class BoardGame {
	/*
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int[n][n];
			for(int i = 0; i < n; i++) {
				arr[i] = Arrays.stream(br.readLine().split(","))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			System.out.println(new BoardGame().solution(arr));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//*/
	private class Point {
		int y,x;
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public String toString() {
			return "("+y+","+x+")";
		}
		@Override
		public boolean equals(Object o) {
			if(!(o instanceof Point)) {
				return false;
			}
			Point obj = (Point) o;
			return (obj.y == this.y) && (obj.x == this.x);
		}
	}
	private class Block {
		ArrayList<Point> pointList = new ArrayList<>();
		void addPoint(Point ptr) {
			pointList.add(ptr);
		}
		@Override
		public String toString() {
			return pointList.toString();
		}
	}
	public int solution(int[][] board) {
		HashMap<Integer, Block> blockMap = new HashMap<>();
		ArrayList<Integer> keyList = new ArrayList<>();
		for(int y = 0; y < board.length; y++) {
			for(int x = 0; x < board[0].length; x++) {
				if(board[y][x] != 0) {
					int key = board[y][x];
					if(blockMap.containsKey(key)) {
						blockMap.get(key).addPoint(new Point(y,x));
					} else {
						Block block = new Block();
						block.addPoint(new Point(y,x));
						blockMap.put(key, block);
						keyList.add(key);
					}
				}
			}
		}
		int answer = 0;
		while(!blockMap.isEmpty()) {
			int crashBlockNum = -1;
			for(int key : keyList) {
				if(canCrash(board, blockMap.get(key))) {
					crashBlockNum = key;
					break;
				}
			}
			
			if(crashBlockNum != -1) {
				removeBoard(board, blockMap.get(crashBlockNum));
				keyList.remove(keyList.indexOf(crashBlockNum));
				blockMap.remove(crashBlockNum);
				answer++;
			} else {
				break;
			}
		}
		return answer;
    }
	private void removeBoard(int[][] board, Block block) {
		// TODO Auto-generated method stub
		ArrayList<Point> ptrList = block.pointList;
		for(Point ptr : ptrList) {
			board[ptr.y][ptr.x] = 0;
		}
	}
	private boolean canCrash(int[][] board, Block block) {
		// TODO Auto-generated method stub
		ArrayList<Point> ptrList = block.pointList;
		int minx=987654321,miny=987654321,maxx=-1,maxy=-1;
		for(Point ptr : ptrList) {
			miny = miny > ptr.y ? ptr.y:miny;
			maxy = maxy > ptr.y ? maxy:ptr.y;
			minx = minx > ptr.x ? ptr.x:minx;
			maxx = maxx > ptr.x ? maxx:ptr.x;
		}
		
		ArrayList<Point> blankList = new ArrayList<>();
		for(int y = miny; y <= maxy; y++) {
			for(int x = minx; x <= maxx; x++) {
				Point tmp = new Point(y,x);
				if(!ptrList.contains(tmp)) {
					blankList.add(tmp);
				}
			}
		}
		
		for(Point ptr : blankList) {
			if(!canGoHighest(ptr, board)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean canGoHighest(Point ptr, int[][] board) {
		int y = ptr.y;
		int x = ptr.x;
		while(y >= 0) {
			if(board[y][x] == 0) {
				y--;
			} else {
				return false;
			}
		}
		return true;
	}
}
