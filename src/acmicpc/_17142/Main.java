package acmicpc._17142;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @see https://www.acmicpc.net/problem/17142
 * @author jun
 *
 */
public class Main {
	static int n,m,empty;
	static int[][] board;
	private static class Point {
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
				return (obj.x == this.x) && (obj.x == this.x);
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
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			n = info[0];
			m = info[1];
			empty = 0;
			board = new int[n][n];
			ArrayList<Point> virusList = new ArrayList<>();
			for(int i = 0; i < n; i++) {
				board[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				for(int j = 0; j < n; j++) {
					if(board[i][j] == 2) {
						virusList.add(new Point(i,j));
					}
					if(board[i][j] == 0) {
						empty++;
					}
				}
			}
			min = MAX;
			solve(0,0,virusList,new boolean[virusList.size()]);
			System.out.println(min==MAX?-1:min);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static int MAX = 987654321;
	private static int min;
	private static void solve(int idx, int cnt, ArrayList<Point> virusList, 
			boolean[] selected) {
		if(cnt == m) {
			ArrayList<Point> selectVirusList = new ArrayList<>();
			for(int i = 0; i < virusList.size(); i++) {
				Point virus = virusList.get(i);
				if(selected[i]) {
					selectVirusList.add(virus);
				}
			}
			min = Math.min(min, calTime(selectVirusList));
		}
		if(idx > virusList.size()) {
			return;
		}
		for(int i = idx;i<virusList.size();i++) {
			if(!selected[i]) {
				selected[i]=true;
				solve(i+1,cnt+1,virusList,selected);
				selected[i]=false;
			}
		}
	}
	
	static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
	private static int calTime(ArrayList<Point> selectVirusList) {
		int[][] time = new int[n][n];
		for(int i = 0; i < n; i++) {
			Arrays.fill(time[i], -1);
		}
		
		for(Point ptr : selectVirusList) {
			time[ptr.y][ptr.x] = 0; 
		}
		int tmp = empty;

		Queue<Point> virusQueue = new LinkedList<>(selectVirusList);
		int totalTime = 0;
		while(!virusQueue.isEmpty()) {
			if(totalTime >= min) {
				return MAX;
			}
			
			Point virus = virusQueue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nextY = virus.y+dir[i][0];
				int nextX = virus.x+dir[i][1];
				if(isInBound(nextY, nextX)) {
					if((board[nextY][nextX]!=1) && (time[nextY][nextX]==-1)) {
						time[nextY][nextX] = time[virus.y][virus.x]+1;
						if(board[nextY][nextX] == 0) {
							totalTime = Math.max(totalTime, time[nextY][nextX]);
							tmp--;
						}
						virusQueue.add(new Point(nextY, nextX));
					}
				}
			}
			
			if(tmp == 0) {
				break;
			}
		}
		
		return tmp==0?totalTime:MAX;
	}
	private static void showBoard(int[][] board) {
		for(int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println("==================");
	}
	private static boolean isInBound(int y, int x) {
		return (y>=0) && (y<n) && (x>=0) && (x<n);
	}
}
