package acmicpc._5557;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		cache = new long[n+1][21];
		for(int i = 0; i <= n; i++) {
			Arrays.fill(cache[i], 0);
		}
		
		cache[1][arr[0]]=1;
		for(int i = 2; i <= n; i++) {
			for(int sum = 0; sum <= 20; sum++) {
				if(cache[i-1][sum] != 0) {
					if(sum+arr[i-1]<=20) {
						cache[i][sum+arr[i-1]] += cache[i-1][sum];
					}
					if(sum-arr[i-1]>=0) {
						cache[i][sum-arr[i-1]] += cache[i-1][sum];
					}
				}
			}
		}
		System.out.println(cache[n-1][arr[n-1]]);
//		for(int i = 0; i < n; i++) {
//			System.out.println(Arrays.toString(cache[i]));
//		}
		/*
		System.out.println(solve(0,0,arr));
		for(int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(cache[i]));
		}
		*/
	}

	private static int n;
	private static long[][] cache;
	private static long solve(int pos, int sum, int[] arr) {
		if(pos==n-1) {
			return sum==arr[n-1]?1:0;
		}
		
		if(cache[pos][sum] != -1) {
			return cache[pos][sum];
		}
		
		long ret = 0;
		if(sum+arr[pos]<=20) {
			ret+=solve(pos+1,sum+arr[pos],arr);
		}
		//if((sum+arr[pos] != sum-arr[pos]) && (sum-arr[pos]>=0)) {
		if(sum-arr[pos]>=0) {
			ret+=solve(pos+1,sum-arr[pos],arr);
		}
		cache[pos][sum]=ret;
		return ret;
	}
}
