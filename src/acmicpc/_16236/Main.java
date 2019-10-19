package acmicpc._16236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @see @see https://www.acmicpc.net/problem/16236
 * @author jun
 *
 */
public class Main {
	static int[][] board;
	static int n;
	static int MAX = 987654321;
	static int dist;
	
	private static class Point {
		int x,y,val;
		Point(int y, int x, int val) {
			this.y = y;
			this.x = x;
			this.val = val;
		}
		
		@Override
		public String toString() {
			return "("+y+","+x+")::"+val;
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof Point) {
				Point obj = (Point) o;
				return (this.x == obj.x) && (this.y == obj.y) && (this.val == obj.val);
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			int ret = 17;
			ret = ret*31+x;
			ret = ret*31+y;
			ret = ret*31+val;
			return ret;
		}
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			board = new int[n][n];
			Point shark = null;
			
			for(int i = 0; i < n; i++) {
				board[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				for(int j = 0; j < n; j++) {
					if(board[i][j] == 9) {
						shark = new Point(i,j,2);
						board[i][j] = 0;
						continue;
					}
				}
			}
			
			test(shark);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};

	private static boolean isInbound(int nextY, int nextX) {
		return (nextY >= 0) && (nextY < n) && (nextX >= 0) && (nextX < n);
	}
	
	private static void test(Point shark) {
		int sec = 0;
		int size = 2,cnt=0;
		while(true) {
			Queue<Point> queue = new LinkedList<Point>();
			queue.add(shark);
			boolean[][] check = new boolean[n][n];
			int dist = 0;
			boolean isEatable = false;
			ArrayList<Point> eatableList = new ArrayList<>();
			
			while(!queue.isEmpty()) {
				Queue<Point> nextQueue = new LinkedList<Point>();
				dist++;
				while(!queue.isEmpty()) {
					Point now = queue.poll();
					for(int i = 0; i < 4; i++) {
						int nextY = now.y+dir[i][0];
						int nextX = now.x+dir[i][1];
						if(isInbound(nextY, nextX) && !check[nextY][nextX]) {
							check[nextY][nextX] = true;
							if((board[nextY][nextX] > 0) && (board[nextY][nextX] < size)) {
								isEatable = true;
								eatableList.add(new Point(nextY, nextX, board[nextY][nextX]));
								continue;
							}
							if(board[nextY][nextX] == size || board[nextY][nextX] == 0) {
								nextQueue.add(new Point(nextY, nextX, size));
							}
						}
					}
				}
				if(isEatable) {
					break;
				}
				queue = nextQueue;
			}
			
			if(isEatable) {
				eatableList.sort(new Comparator<Point>() {
					@Override
					public int compare(Point arg0, Point arg1) {
						if(arg0.y == arg1.y) {
							return arg0.x-arg1.x;
						}
						return arg0.y - arg1.y;
					}
				});
				//System.out.println(eatableList);
				
				sec+=dist;
				cnt++;
				if(size == cnt) {
					size++;
					cnt = 0;
				}
				shark.y = eatableList.get(0).y;
				shark.x = eatableList.get(0).x;
				board[shark.y][shark.x]= 0; 
			} else {
				break;
			}
		}
		
		System.out.println(sec);
	}
}
