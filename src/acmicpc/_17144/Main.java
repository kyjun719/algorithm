package acmicpc._17144;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @see https://www.acmicpc.net/problem/17144
 * @author jun
 *
 */
public class Main {
	private static class Point{
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
			if(o instanceof Point) {
				Point obj = (Point)o;
				return (obj.x==this.x)&&(obj.y==this.y);
			}
			return false;
		}
		@Override
		public int hashCode() {
			int ret = 17;
			ret = ret*31+x;
			ret = ret*31+y;
			return ret;
		}
	}
	static int r,c,t;
	static int[][] board;
	static Point[] purifier;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			r = info[0];
			c = info[1];
			t = info[2];
			board = new int[r][c];
			Queue<Point> dustList = new LinkedList<>();
			purifier = new Point[2];
			Point tmp = new Point(0,0);
			for(int i = 0; i < r; i++) {
				board[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				for(int j = 0; j < c; j++) {
					if(board[i][j] > 0) {
						dustList.add(new Point(i,j));
					}
					if(board[i][j] == -1) {
						tmp = new Point(i,j);
					}
				}
			}
			purifier[0] = new Point(tmp.y-1, tmp.x);
			purifier[1] = tmp;
			
			System.out.println(solve(dustList));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
	private static int solve(Queue<Point> dustList) {
		for(int time = 0; time < t; time++) {
			Queue<Point> nextDustList = new LinkedList<>();
			int[][] addBoard = new int[r][c];
			HashSet<Point> addedList = new HashSet<>();
			while(!dustList.isEmpty()) {
				Point ptr = dustList.poll();
				int cnt = 0;
				int num = board[ptr.y][ptr.x]/5;
				
				if(num > 0) {
					for(int i = 0; i < 4; i++) {
						int nextY = ptr.y+dir[i][0];
						int nextX = ptr.x+dir[i][1];
						if(canSpread(nextY, nextX)) {
							cnt++;
							addedList.add(new Point(nextY,nextX));
							addBoard[nextY][nextX] += num;
						}
					}
				}
				
				addBoard[ptr.y][ptr.x]-=(num*cnt);
				addedList.add(ptr);
			}
			
			ArrayList<Point> rotatePoint = new ArrayList<>();
			for(Point point:addedList) {
				int y=point.y;
				int x=point.x;
				board[y][x] += addBoard[y][x];
				if(isRotatePoint(point)) {
					rotatePoint.add(point);
				} else {
					nextDustList.add(point);
				}
			}
			
			//int upperCnt = 2*c+2*(purifier[0].y-2);
			int upperCnt = 2*c+2*purifier[0].y-4;
			int y = purifier[0].y-1;
			int x = purifier[0].x;
			rotatePoint.remove(new Point(y,x));
			for(int i = 0; i < upperCnt; i++) {
				int nextY=y, nextX=x;
				if(x==0) {
					if(y==0) {
						nextX++;
					} else {
						nextY--;
					}
				} else if(y==0) {
					if(x==c-1) {
						nextY++;
					} else {
						nextX++;
					}
				} else if(x==c-1) {
					if(y==purifier[0].y) {
						nextX--;
					} else {
						nextY++;
					}
				} else {
					nextX--;
				}
				//System.out.println(new Point(y,x)+"<<"+new Point(nextY,nextX));
				board[y][x] = board[nextY][nextX];
				int tmpIdx = rotatePoint.indexOf(new Point(nextY,nextX)); 
				if(tmpIdx != -1) {
					rotatePoint.remove(tmpIdx);
					nextDustList.add(new Point(y,x));
				}
				y=nextY;
				x=nextX;
			}
			//System.out.println("remove<<"+new Point(y,x));
			rotatePoint.remove(new Point(y,x));
			board[purifier[0].y][1] = 0;
			
			//int lowerCnt = 2*c+2*(purifier[1].y-2);
			int lowerCnt = 2*c+2*r-2*purifier[1].y-6;
			y = purifier[1].y+1;
			x = purifier[1].x;
			rotatePoint.remove(new Point(y,x));
			for(int i = 0; i < lowerCnt; i++) {
				int nextY=y, nextX=x;
				if(x==0) {
					if(y==r-1) {
						nextX++;
					} else {
						nextY++;
					}
				} else if(y==r-1) {
					if(x==c-1) {
						nextY--;
					} else {
						nextX++;
					}
				} else if(x==c-1) {
					if(y==purifier[1].y) {
						nextX--;
					} else {
						nextY--;
					}
				} else {
					nextX--;
				}
				//System.out.println(new Point(y,x)+"<<"+new Point(nextY,nextX));
				board[y][x] = board[nextY][nextX];
				int tmpIdx = rotatePoint.indexOf(new Point(nextY,nextX)); 
				if(tmpIdx != -1) {
					rotatePoint.remove(tmpIdx);
					nextDustList.add(new Point(y,x));
				}
				y=nextY;
				x=nextX;
			}
			//System.out.println("remove<<"+new Point(y,x));
			rotatePoint.remove(new Point(y,x));
			board[purifier[1].y][x] = 0;

			dustList = nextDustList;
			//showBoard();
		}
		
		int ret = 0;
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(board[i][j] == -1) {
					continue;
				}
				ret += board[i][j];
			}
		}
		/*
		while(!dustList.isEmpty()) {
			Point ptr = dustList.poll();
			ret += board[ptr.y][ptr.x];
		}
		*/
		return ret;
	}
	
	private static void showBoard() {
		for(int i = 0; i < r; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println("======================");
	}

	private static boolean isRotatePoint(Point point) {
		int y = point.y;
		int x = point.x;
		if(y==0||y==r-1) {
			return true;
		}
		if(y==purifier[0].y||y==purifier[1].y) {
			return true;
		}
		if(x==0||x==c-1) {
			return true;
		}
		return false;
	}

	private static boolean canSpread(int y, int x) {
		for(int i = 0; i < 2; i++) {
			if((purifier[i].y==y)&&(purifier[i].x==x)) {
				return false;
			}
		}
		
		return (y>=0)&&(y<r)&&(x>=0)&&(x<c);
	}
}
