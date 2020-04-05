package algospot.LIS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] cache;
	static int n;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				n = Integer.parseInt(br.readLine());
				cache = new int[n];
				
				Arrays.fill(cache, -1);
				int[] arr = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				int ret = solve(arr, -1);
				System.out.println(ret>1?ret-1:ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve(int[] arr, int idx) {
		// TODO Auto-generated method stub
		if(idx >= 0 && cache[idx] != -1) {
			return cache[idx];
		}
		
		int ret = 1;
		for(int i = idx+1; i < n; i++) {
			if(idx == -1 || arr[idx] < arr[i]) {
				ret = Math.max(ret, solve(arr, i)+1);
			}
		}
		if(idx >= 0) {
			cache[idx] = ret;
		}
		
		return ret;
	}
}
