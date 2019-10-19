package acmicpc._17471;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n;
	static int[] arr;
	static boolean[][] linked;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			arr = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			linked = new boolean[n][n];
			for(int i = 0; i < n; i++) {
				int[] info = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				for(int j = 1; j < info.length; j++) {
					linked[i][info[j]-1] = true;
				}
			}

			boolean[] selected = new boolean[n];
			int ret = solve(0, selected);
			
			System.out.println(ret==MAX?-1:ret);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	static int MAX = 987654321;
	private static int solve(int idx, boolean[] selected) {
		if(idx >= n) {
			return calc(selected);
		}
		
		int ret = solve(idx+1, selected);
		selected[idx] = true;
		ret = Math.min(ret, solve(idx+1, selected));
		selected[idx] = false;
		return ret;
	}

	private static int calc(boolean[] selected) {
		int cntA = 0, cntB = 0;
		for(int i = 0; i < n; i++) {
			if(selected[i]) {
				cntA = search(i, selected, true);
				break;
			}
		}
		
		for(int i = 0; i < n; i++) {
			if(!selected[i]) {
				cntB = search(i, selected, false);
				break;
			}
		}
		
		if(cntA == 0 || cntB == 0 || (cntA+cntB) != n) {
			return MAX;
		}
		
		int a=0,b=0;
		for(int i = 0; i < n; i++) {
			if(selected[i]) {
				a += arr[i];
			} else {
				b += arr[i];
			}
		}
		return Math.abs(a-b);
	}

	private static int search(int i, boolean[] selected, boolean b) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(i);
		boolean[] visited = new boolean[n];
		
		ArrayList<Integer> tmp = new ArrayList<>();
		tmp.add(i);
		int cnt = 0;
		while(!queue.isEmpty()) {
			int idx = queue.poll();
			if(visited[idx]) {
				continue;
			}
			visited[idx] = true;
			cnt++;
			
			for(int j = 0; j < n; j++) {
				if((selected[j] == b) && (linked[idx][j]) && !visited[j]) {
					queue.add(j);
					tmp.add(j);
				}
			}
		}
		
		return cnt;
	}
}
