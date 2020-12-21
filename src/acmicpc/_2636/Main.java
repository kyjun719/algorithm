package acmicpc._2636;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args )throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		n = tmp[0];
		m = tmp[1];
		int[][] board = new int[tmp[0]][tmp[1]];
		Queue<Point> q = new LinkedList<>();
		
		for(int i = 0; i < n; i++) {
			board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for(int j = 0; j < m; j++) {
				if(board[i][j]==1) {
					q.add(new Point(i,j));
				}
			}
		}
		
		int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
		
		int cnt=0;
		int befSize = q.size();
		while(true) {
			Queue<Point> removed = new LinkedList<>();
			
			Queue<Point> outQ = new LinkedList<>();
			outQ.add(new Point(0,0));
			board[0][0]=2;
			boolean[][] visited = new boolean[n][m];
			while(!outQ.isEmpty()) {
				Point now = outQ.poll();
				for(int[] del : dir) {
					int nx=now.x+del[0], ny=now.y+del[1];
					if(isInBound(nx, ny) && !visited[ny][nx] && board[ny][nx]!=1) {
						board[ny][nx]=2;
						visited[ny][nx] = true;
						outQ.add(new Point(ny,nx));
					}
				}
			}
			
			int left=0;
			for(int y = 0; y < n; y++) {
				for(int x = 0; x < m; x++) {
					if(board[y][x]==1) {
						for(int[] del : dir) {
							int nx=x+del[0], ny=y+del[1];
							if(isInBound(nx, ny) && board[ny][nx]==2) {
								removed.add(new Point(y,x));
								left++;
								break;
							}
						}
					}
				}
			}
			
			if(left!=0) {
				befSize=left;
			} else {
				break;
			}
			cnt++;
			
			
			while(!removed.isEmpty()) {
				Point now = removed.poll();
				board[now.y][now.x]=2;
			}
//			for(int i = 0; i < n; i++) {
//				System.out.println(Arrays.toString(board[i]));
//			}
		}
		System.out.println(cnt);
		System.out.println(befSize);
	}
	
	private static int n,m;
	private static boolean isInBound(int nx, int ny) {
		return nx>=0 && nx <m && ny>=0 && ny < n;
	}

	private static class Point {
		int y,x;
		public Point(int y, int x) {
			this.y=y;
			this.x=x;
		}
		@Override
		public String toString() {
			return "("+y+","+x+")";
		}
	}
}
