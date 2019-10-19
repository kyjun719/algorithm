package acmicpc._2667;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/2667
 * @author jun
 *
 */
public class Main {
	static int n;
	static int[][] board;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			board = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				String tmp = br.readLine();
				for(int j = 0; j < n; j++) {
					board[i][j] = tmp.charAt(j)-48;
				}
			}
			solve();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
	private static void solve() {
		// TODO Auto-generated method stub
		ArrayList<Integer> cntList = new ArrayList<>();
		boolean[][] checked = new boolean[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(board[i][j] == 1 && !checked[i][j]) {
					Queue<Integer[]> pointQueue = new LinkedList<>();
					pointQueue.add(new Integer[] {i,j});
					int cnt = 0;
					while(!pointQueue.isEmpty()) {
						Integer[] ptr = pointQueue.poll();
						if(!checked[ptr[0]][ptr[1]]) {
							cnt++;
							checked[ptr[0]][ptr[1]] = true;
							for(int idx = 0; idx < 4; idx++) {
								int ny = dir[idx][0]+ptr[0];
								int nx = dir[idx][1]+ptr[1];
								if(isInBound(ny,nx) && board[ny][nx] == 1 && !checked[ny][nx]) {
									pointQueue.add(new Integer[] {ny,nx});
								}
							}
						}
					}
					cntList.add(cnt);
				}
			}
		}
		System.out.println(cntList.size());
		cntList.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg0-arg1;
			}
		});
		for(Integer tmp : cntList) {
			System.out.println(tmp);
		}
	}
	private static boolean isInBound(int ny, int nx) {
		return (nx>=0)&&(nx<n)&&(ny>=0)&&(ny<n);
	}
}
