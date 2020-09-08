package acmicpc._11066;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				int k = Integer.parseInt(br.readLine());
				int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				psum=new int[k+1];
				cache=new int[k][k];
				for(int i = 0; i < k; i++) {
					Arrays.fill(cache[i], INF);
				}
				for(int i=1; i<=k;i++) {
					psum[i]=psum[i-1]+arr[i-1];
				}
				System.out.println(solve(0,k-1));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int[] psum;
	private static int[][] cache;
	private static int INF = 987654321;
	private static int solve(int s, int e) {
		if(s==e) {
			return 0;
		}
		
		if(cache[s][e] != INF) {
			return cache[s][e];
		}
		
		int ret = INF;
		for(int k=s; k<e; k++) {
			ret=Math.min(ret, solve(s,k)+solve(k+1,e)+psum[e+1]-psum[s]);
		}
		cache[s][e]=ret;
		return ret;
	}
}
