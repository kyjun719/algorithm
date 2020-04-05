package algospot.POLY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			for(int tc = 0; tc < c; tc++) {
				int n = Integer.parseInt(bf.readLine());
				
				for(int i = 0; i <= n; i++) {
					Arrays.fill(cache[i], -1);
				}
				
				int ret = 0;
				for(int i = 1; i <= n; i++) {
					ret += poly(n, i);
					ret %= MOD;
				}
				System.out.println(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int[][] cache = new int[101][101];
	static int MOD = 10000000;
	//poly(n, first) = sum(sec=1~n-first)(sec+first-1)*poly(n-first, sec)
	public static int poly(int n, int first) {
		if(n == first) {
			return 1;
		}
		
		if(cache[n][first] != -1) {
			return cache[n][first];
		}
		
		int ret = 0;
		for(int sec = 1; sec <= n-first; sec++) {
			ret += (sec+first-1)*poly(n-first, sec);
			ret %= MOD;
		}
		cache[n][first] = ret;
		return ret;
	}
}
