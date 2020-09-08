package acmicpc._12865;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int n = tmp[0];
			int k = tmp[1];
			int[] w = new int[n];
			int[] v = new int[n];
			for(int i = 0; i < n; i++) {
				tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				w[i]=tmp[0];
				v[i]=tmp[1];
			}
			cache=new int[n][k+1];
			for(int i = 0; i < n; i++) {
				Arrays.fill(cache[i], -1);
			}
			System.out.println(solve(-1,0,k,w,v));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static int[][] cache;
	private static int solve(int idx, int weight, int k, int[] w, int[] v) {
		int n = w.length;
		if(idx >= n) {
			return 0;
		}
		
		if(idx!=-1 && cache[idx][weight]!=-1) {
			return cache[idx][weight];
		}
		
		int ret = 0;
		for(int i = idx+1; i < n; i++) {
			if(weight+w[i]<=k) {
				ret = Math.max(ret, v[i]+solve(i,weight+w[i],k,w,v));
			}
		}
		ret=Math.max(ret, solve(idx+1,weight,k,w,v));
//		System.out.println(idx+","+weight+">>"+ret);
		if(idx!=-1) {
			cache[idx][weight]=ret;
		}
		return ret;
	}
}
