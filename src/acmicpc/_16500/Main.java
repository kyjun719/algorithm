package acmicpc._16500;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String ret = br.readLine();
		n = Integer.parseInt(br.readLine());
		cache = new int[n+1][ret.length()+1];
        for(int i = 0; i < n+1; i++) {
            Arrays.fill(cache[i], -1);
        }
		String[] strArr = new String[n];
		for(int i = 0; i < n; i++) {
			strArr[i]=br.readLine();
		}
		boolean[] used = new boolean[n];
		System.out.println(solve(0 ,0, used, ret, strArr));
	}
	
	private static int[][] cache;
	private static int n;
	
	private static int solve(int strPtr, int cnt, boolean[] used, String ret, String[] strArr) {
//		System.out.println(strPtr+","+cnt);
		if(strPtr>=ret.length()) {
			return 1;
		}
		if(cache[cnt][strPtr]!=-1) {
			return cache[cnt][strPtr];
		}
		int ans = 0;
		for(int i = 0; i < n; i++) {
			String next=strArr[i];
			if(used[i] || strPtr+next.length()>ret.length()) {
				continue;
			}
//			System.out.println(next+">>"+ret.substring(strPtr, strPtr+next.length()));
			if(ret.substring(strPtr, strPtr+next.length()).equals(next)) {
				used[i]=true;
				ans |= solve(strPtr+next.length(), cnt+1, used, ret, strArr);
				used[i]=false;
			}
		}
		cache[cnt][strPtr] = ans;
		return ans;
	}
}
