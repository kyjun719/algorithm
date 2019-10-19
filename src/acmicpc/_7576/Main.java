package acmicpc._7576;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @see https://www.acmicpc.net/problem/7576
 * @author jun
 *
 */
public class Main {
	static int n,m,empty;
	static int[][] board,time;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			m = info[0];
			n = info[1];
			board = new int[n][m];
			time = new int[n][m];
			for(int i = 0; i < n; i++) {
				Arrays.fill(time[i], -1);
			}
			Queue<Integer[]> tomato = new LinkedList<>();
			for(int i = 0; i < n; i++) {
				board[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				for(int j = 0; j < m; j++) {
					if(board[i][j] == 1) {
						tomato.add(new Integer[] {i,j});
						time[i][j] = 0;
						board[i][j] = 0;
					}
					if(board[i][j] == 0) {
						empty++;
					}
				}
			}
			System.out.println(solve(tomato));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
	private static int solve(Queue<Integer[]> queue) {
		int totalTime = 0;
		
		while(!queue.isEmpty()) {
			Integer[] tmp = queue.poll();
			int y = tmp[0];
			int x = tmp[1];
			empty--;
			for(int i = 0; i < 4; i++) {
				int ny = y+dir[i][0];
				int nx = x+dir[i][1];
				if(isInBound(ny,nx) && board[ny][nx] == 0 && time[ny][nx] == -1) {
					time[ny][nx] = time[y][x]+1;
					totalTime = time[y][x]+1;
					queue.add(new Integer[] {ny,nx});
				}
			}
		}
		//showTime();
		return empty==0?totalTime:-1;
	}
	
	private static void showTime() {
		for(int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(time[i]));
		}
	}

	private static boolean isInBound(int ny, int nx) {
		return (ny>=0)&&(ny<n)&&(nx>=0)&&(nx<m);
	}
}
