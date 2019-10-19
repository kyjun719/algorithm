package acmicpc._14502;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @see https://www.acmicpc.net/problem/14502
 * @author jun
 *
 */
public class Main {
	private static int n, m;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			n = info[0];
			m = info[1];
			int[][] board = new int[n][m];
			for(int i = 0; i < n; i++) {
				board[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			
			int answer = solve(0, 0, 3, board);
			System.out.println(answer);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve(int y, int x, int poll, int[][] board) {
		// TODO Auto-generated method stub
		if(poll == 0) {
			return spread(board);
		}
		
		int nextY = (x+1) >= board[0].length? y+1:y;
		int ret = 0;
		for(int i = nextY; i < n; i++) {
			int nextX = i == y? x:0;
			for(int j = nextX; j < m; j++) {
				if(board[i][j] == 0) {
					board[i][j] = 1;
					ret = Math.max(ret, solve(i,j, poll-1, board));
					board[i][j] = 0;
				}
			}
		}
		return ret;
	}

	private static class Point {
		int x,y;
		Point(int y, int x) {
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "("+y+","+x+")";
		}
	}
	private static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
	private static int spread(int[][] board) {
		// TODO Auto-generated method stub
		Queue<Point> queue = new LinkedList<>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(board[i][j] == 2) {
					queue.add(new Point(i,j));
				}
			}
		}
		
		int[][] tmpBoard = new int[n][m];
		for(int i = 0; i < n; i++) {
			tmpBoard[i] = Arrays.copyOf(board[i], m);
		}
		while(!queue.isEmpty()) {
			Set<Point> nextSet = new HashSet<>();
			while(!queue.isEmpty()) {
				Point tmp = queue.poll();
				for(int i = 0; i < 4; i++) {
					int nextY = tmp.y+dir[i][0];
					int nextX = tmp.x+dir[i][1];
					if(isInBound(nextY, nextX) && tmpBoard[nextY][nextX] == 0) {
						tmpBoard[nextY][nextX] = 2;
						nextSet.add(new Point(nextY, nextX));
					}
				}
			}
			queue.addAll(nextSet);
		}
		
		int ret = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(tmpBoard[i][j] == 0) {
					ret++;
				}
			}
		}
		
		return ret;
	}

	private static boolean isInBound(int nextY, int nextX) {
		return nextY >= 0 && nextY < n && nextX >= 0 && nextX < m;
	}
}
