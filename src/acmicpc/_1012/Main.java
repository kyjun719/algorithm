package acmicpc._1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/1012
 * @author jun
 *
 */
public class Main {
	static int n,m,k;
	static int[][] board;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				int[] info = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				m = info[0];
				n = info[1];
				k = info[2];
				board = new int[n][m];
				for(int i = 0; i < k; i++) {
					int[] tmp = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
					board[tmp[1]][tmp[0]] = 1;
				}
				solve();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
	private static void solve() {
		// TODO Auto-generated method stub
		boolean[][] checked = new boolean[n][m];
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(!checked[i][j] && board[i][j] == 1) {
					cnt++;
					Queue<Integer[]> search = new LinkedList<>();
					search.add(new Integer[] {i,j});
					
					while(!search.isEmpty()) {
						Integer[] ptr = search.poll();
						if(!checked[ptr[0]][ptr[1]]) {
							checked[ptr[0]][ptr[1]] = true;
							for(int idx=0;idx<4;idx++) {
								int ny = dir[idx][0]+ptr[0];
								int nx = dir[idx][1]+ptr[1];
								if(isInBound(ny,nx) && board[ny][nx]==1 && !checked[ny][nx]) {
									search.add(new Integer[] {ny,nx});
								}
							}
						}
					}
				}
			}
		}
		System.out.println(cnt);
	}
	private static boolean isInBound(int ny, int nx) {
		return (ny>=0)&&(ny<n)&&(nx>=0)&&(nx<m);
	}
}
