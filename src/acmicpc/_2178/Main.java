package acmicpc._2178;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @see https://www.acmicpc.net/problem/2178
 * @author jun
 *
 */
public class Main {
	static int n,m;
	static int[][] board;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			n = info[0];
			m = info[1];
			board = new int[n][m];
			for(int i = 0; i < n; i++) {
				String tmp = br.readLine();
				for(int j = 0; j < m; j++) {
					board[i][j] = tmp.charAt(j)-48;
				}
			}
			solve();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
	private static void solve() {
		int[][] time = new int[n][m];
		boolean[][] checked = new boolean[n][m];
		Queue<Integer[]> search = new LinkedList<>();
		search.add(new Integer[] {0,0});
		time[0][0] = 1;
		while(!search.isEmpty()) {
			Integer[] ptr = search.poll();
			if(!checked[ptr[0]][ptr[1]]) {
				checked[ptr[0]][ptr[1]] = true;
				if(board[ptr[0]][ptr[1]] == 1) {
					for(int i = 0; i < 4; i++) {
						int ny = ptr[0]+dir[i][0];
						int nx = ptr[1]+dir[i][1];
						if(isInBound(ny,nx) && board[ny][nx]==1) {
							if(time[ny][nx] == 0) {
								time[ny][nx] = time[ptr[0]][ptr[1]]+1;
							} else {
								time[ny][nx] = Math.min(time[ny][nx], time[ptr[0]][ptr[1]]+1);
							}
							
							search.add(new Integer[] {ny,nx});
						}
					}
				}
			}
		}
		System.out.println(time[n-1][m-1]);
	}
	private static boolean isInBound(int ny, int nx) {
		return (ny>=0)&&(ny<n)&&(nx>=0)&&(nx<m);
	}
}
