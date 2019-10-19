package acmicpc._3190;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @see https://www.acmicpc.net/problem/3190
 * @author jun
 *
 */
public class Main {
	static int n,k;
	static List<Point> appleList;
	static HashMap<Integer, String> dirMap;
	
	static class Point {
		int x,y;
		Point(){ }
		Point(int y, int x) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object o) {
			if(!(o instanceof Point)) {
				return false;
			}
			
			Point p = (Point) o;
			return p.x == this.x && p.y == this.y;
		}
		
		@Override
		public String toString() {
			return "("+x+","+y+")";
		}
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			k = Integer.parseInt(br.readLine());
			appleList = new ArrayList<>();
			for(int i = 0; i < k; i++) {
				int[] info = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				appleList.add(new Point(info[0]-1, info[1]-1));
			}
			
			int l = Integer.parseInt(br.readLine());
			dirMap = new HashMap<>();
			for(int i = 0; i < l; i++) {
				String[] info = br.readLine().split(" ");
				dirMap.put(Integer.parseInt(info[0]), info[1]);
			}
			
			int ret = solve();
			System.out.println(ret);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int LEFT = 0;
	static int RIGHT = 1;
	static int UP = 2;
	static int DOWN = 3;
	static List<Integer> L_DIR = Arrays.asList(new Integer[] {RIGHT, UP, LEFT, DOWN});
	static List<Integer> R_DIR = Arrays.asList(new Integer[] {RIGHT, DOWN, LEFT, UP});
	static int[][] DIRECTION = {{0,-1},{0,1},{-1,0},{1,0}};
	private static int solve() {
		int dir = RIGHT;
		int[] dirVal = DIRECTION[dir];
		Point ptr = new Point(0,0);
		int time = 0;
		Deque<Point> snake = new ArrayDeque<>();
		snake.add(ptr);
		while(true) {
			time++;
			
			Point nextPtr = new Point(ptr.y+dirVal[0], ptr.x+dirVal[1]);
			if(isFinish(snake, nextPtr)) {
				break;
			}
			
			if(!appleList.contains(nextPtr)) {
				snake.removeLast();
			} else {
				appleList.remove(nextPtr);
			}
			snake.addFirst(nextPtr);
			ptr = nextPtr;
			
			if(dirMap.containsKey(time)) {
				if(dirMap.get(time).equals("D")) {
					dir = R_DIR.get((R_DIR.indexOf(dir)+1)%4);
				} else {
					dir = L_DIR.get((L_DIR.indexOf(dir)+1)%4);
				}
				dirVal = DIRECTION[dir];
				
				dirMap.remove(time);
			}
		}
		// TODO Auto-generated method stub
		return time;
	}
	
	private static boolean isFinish(Deque<Point> snake, Point nextPtr) {
		if(nextPtr.y < 0 || nextPtr.y >= n || nextPtr.x < 0 || nextPtr.x >= n) {
			return true;
		}
		
		return snake.contains(nextPtr);
	}
}
