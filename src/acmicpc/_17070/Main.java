package acmicpc._17070;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n;
	static int[][] board;
	static class Pipe {
		int y,x,type;
		Pipe(int y, int x, int type) {
			this.y = y;
			this.x = x;
			this.type = type;
		}
		public String toString() {
			return "("+y+","+x+")::"+type;
		}
		public boolean equals(Object o) {
			if(o instanceof Pipe) {
				Pipe obj = (Pipe)o;
				return obj.x == this.x && obj.y == this.y && obj.type == this.type;
			}
			return false;
		}
		public int hashCode() {
			int ret = 17;
			ret = ret*31+x;
			ret = ret*31+y;
			ret = ret*31+type;
			return ret;
		}
	}
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			board = new int[n][n];
			for(int i = 0; i < n; i++) {
				board[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			System.out.println(solve(0,1,COL));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int COL = 0;
	static int ROW = 1;
	static int CROSS = 2;
	private static int solve(int y, int x, int type) {		
		if(y == n-1 && x == n-1) {
			return 1;
		}
		
		int ret = 0;
		
		if(type == COL) {
			if(isInBound(y,x+1) && (board[y][x+1] == 0)) {
				ret += solve(y, x+1, COL);
			}
			if(isInBound(y+1,x+1) && (board[y+1][x+1] == 0) 
					&& (board[y+1][x] == 0) && (board[y][x+1] == 0)) {
				ret += solve(y+1, x+1, CROSS);
			}
		}
		if(type == ROW) {
			if(isInBound(y+1,x) && (board[y+1][x] == 0)) {
				ret += solve(y+1, x, ROW);
			}
			if(isInBound(y+1,x+1) && (board[y+1][x+1] == 0) 
					&& (board[y+1][x] == 0) && (board[y][x+1] == 0)) {
				ret += solve(y+1, x+1, CROSS);
			}
		}
		if(type == CROSS) {
			if(isInBound(y,x+1) && (board[y][x+1] == 0)) {
				ret += solve(y, x+1, COL);
			}
			if(isInBound(y+1,x) && (board[y+1][x] == 0)) {
				ret += solve(y+1, x, ROW);
			}
			if(isInBound(y+1,x+1) && (board[y+1][x+1] == 0) 
					&& (board[y+1][x] == 0) && (board[y][x+1] == 0)) {
				ret += solve(y+1, x+1, CROSS);
			}
		}
		
		return ret;
	}

	private static boolean isInBound(int y, int x) {
		return (y>=0) && (y<n)&&(x>=0)&&(x<n);
	}
}
