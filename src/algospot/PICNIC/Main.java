package algospot.PICNIC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static int[][] arr;
	private static boolean[] used;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine().substring(0, 1));
			
			StringTokenizer st;
			
			for(int t = 0; t < tc; t++) {
				st = new StringTokenizer(br.readLine());
				
				n = Integer.parseInt(st.nextToken());
				used = new boolean[n];
				Arrays.fill(used, false);
				arr = new int[n][n];
				for(int i = 0; i < n; i++) {
					Arrays.fill(arr[i], -1);
				}
				
				int m = Integer.parseInt(st.nextToken());
				st = new StringTokenizer(br.readLine());
				
				for(int i = 0; i < m; i++) {
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					arr[a][b] = 1;
					arr[b][a] = 1;
				}
				
				System.out.println(solve());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve() {
		int startIdx = -1;
		
		for(int i = 0; i < n; i++) {
			if(!used[i]) {
				startIdx = i;
				break;
			}
		}
		
		if(startIdx == -1) {
			return 1;
		}
		
		int ret = 0;
		for(int i = startIdx+1; i < n; i++) {
			if(!used[i] && (arr[startIdx][i] == 1)) {
				used[startIdx] = true;
				used[i] = true;
				ret += solve();
				used[startIdx] = false;
				used[i] = false;
			}
		}
		
		return ret;
	}
}
