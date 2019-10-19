package acmicpc._7569;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n,m,h,empty;
	static int[][][] board;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			m = info[0];
			n = info[1];
			h = info[2];
			board = new int[h][n][m];
			Queue<Integer[]> tomato = new LinkedList<>();
			for(int z = 0; z < h; z++) {
				for(int y = 0; y < n; y++) {
					board[z][y] = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
					for(int x = 0; x < m; x++) {
						if(board[z][y][x] == 1) {
							tomato.add(new Integer[] {z,y,x});
							//board[z][y][x] = 0;
						}
						
						if(board[z][y][x] == 0) {
							empty++;
						}
					}
				}
			}
			System.out.println(solve(tomato));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int[][] dir = {{0,0,1},{0,0,-1},{0,1,0},{0,-1,0},{1,0,0},{-1,0,0}}; 
	private static int solve(Queue<Integer[]> tomato) {
		int totalTime = 1;
		
		while(!tomato.isEmpty()) {
			Integer[] tmp = tomato.poll();
			int z = tmp[0];
			int y = tmp[1];
			int x = tmp[2];
			for(int i = 0; i < 6; i++) {
				int nz = z+dir[i][0];
				int ny = y+dir[i][1];
				int nx = x+dir[i][2];
				if(isInBound(nz,ny,nx)&&board[nz][ny][nx] == 0) {
					board[nz][ny][nx] = board[z][y][x]+1;
					totalTime = board[nz][ny][nx];
					tomato.add(new Integer[] {nz,ny,nx});
					empty--;
				}
			}
		}
		return empty==0?totalTime-1:-1;
	}
	private static boolean isInBound(int nz, int ny, int nx) {
		return (nz>=0)&&(nz<h)&&(ny>=0)&&(ny<n)&&(nx>=0)&&(nx<m);
	}
}
