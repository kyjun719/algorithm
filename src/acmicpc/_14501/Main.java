package acmicpc._14501;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://www.acmicpc.net/problem/14501
 * @author jun
 *
 */
public class Main {
	static int n;
	static int[] t,p;
	static int[] cache;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			t = new int[n];
			p = new int[n];
			cache = new int[n];
			Arrays.fill(cache, -1);
			for(int i = 0; i < n; i++) {
				int[] tmp = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				t[i] = tmp[0];
				p[i] = tmp[1];
			}
			System.out.println(solve(0));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static int solve(int i) {
		if(i > n) {
			return -1;
		}
		
		if(i == n) {
			return 0;
		}
		
		if(cache[i] != -1) {
			return cache[i];
		}
		
		//System.out.println(i+","+t[i]);
		int ret = 0;
		int work = solve(i+t[i]);
		int notWork = solve(i+1);
		if(work != -1) {
			ret = Math.max(ret, solve(i+t[i])+p[i]);
		}
		if(notWork != -1) {
			ret = Math.max(ret, solve(i+1));
		}		
		
		return cache[i] = ret;
	}
}
