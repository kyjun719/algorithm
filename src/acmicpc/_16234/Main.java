package acmicpc._16234;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @see https://www.acmicpc.net/problem/16234
 * @author jun
 *
 */
public class Main {
	static int N,L,R;
	static int[][] world;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
								.mapToInt(Integer::parseInt)
								.toArray();
			N = info[0];
			L = info[1];
			R = info[2];
			world = new int[N][N];
			for(int i = 0; i < N; i++) {
				world[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			
			System.out.println(solve());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static class Point {
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
				Point obj = (Point) o;
				return (this.x == obj.x) && (this.y == obj.y);
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
	
	private static int solve() {
		//showWorld();
		
		int[][] union = new int[N][N];
		for(int i = 0; i < N; i++) {
			Arrays.fill(union[i], -1);
		}
		
		HashMap<Integer, Set<Point>> unionMap = checkUnion(union);
		
		//showUnionMap(unionMap);
		
		if(unionMap.size() == 0) {
			return 0;
		}
		for(Set<Point> ptrList : unionMap.values()) {
			int val = 0;
			for(Point ptr:ptrList) {
				val += world[ptr.y][ptr.x];
			}
			val = val/ptrList.size();
			for(Point ptr:ptrList) {
				world[ptr.y][ptr.x] = val;
			}
		}
		
		return 1+solve();
	}

	private static void showUnionMap(HashMap<Integer, Set<Point>> unionMap) {
		System.out.println("unionSize::"+unionMap.size());
		for(Integer key : unionMap.keySet()) {
			System.out.println(key+">>"+unionMap.get(key).toString());
		}
		System.out.println("========================");
	}

	private static void showWorld() {
		for(int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(world[i]));
		}
		System.out.println("===============");
	}

	private static int[][] del = {{0,-1},{0,1},{1,0},{-1,0}};
	private static HashMap<Integer, Set<Point>> checkUnion(int[][] union) {
		HashMap<Integer, Set<Point>> ret = new HashMap<>();
		int cnt = 0;
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if(union[y][x] != -1) {
					continue;
				}
				
				Set<Point> list = new HashSet<>();
				calConnectedUnion(cnt,y,x,union,list);
				
				if(list.size() != 0) {
					ret.put(cnt, list);
					cnt++;
				}
			}
		}
		return ret;
	}


	private static void calConnectedUnion(int idx, int y, int x, 
			int[][] union, Set<Point> list) {
		// TODO Auto-generated method stub
		for(int i = 0; i < 4; i++) {
			int nextY = y+del[i][0];
			int nextX = x+del[i][1];
			
			if(!isInBound(nextY,nextX) || union[nextY][nextX] != -1) {
				continue;
			}
			
			if(canUnion(y,x,nextY,nextX)) {
				list.add(new Point(y,x));
				list.add(new Point(nextY,nextX));
				union[nextY][nextX] = idx;
				calConnectedUnion(idx, nextY, nextX, union, list);
			}
		}
	}

	private static void showUnionArr(int[][] union) {
		System.out.println("union::");
		for(int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(union[i]));
		}
		System.out.println("=======================");
	}

	private static boolean canUnion(int y, int x, int nextY, int nextX) {
		int del = Math.abs(world[y][x] - world[nextY][nextX]);
		return (del >= L) && (del <= R);
	}
	
	private static boolean isInBound(int nextY, int nextX) {
		return (nextY >= 0) && (nextY < N) && (nextX >= 0) && (nextX < N);
	}
	
	
}
