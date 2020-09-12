package acmicpc._1053;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] chArr = br.readLine().toCharArray();
		int n = chArr.length;
		int cnt = 987654312;
		cache=new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = i+1; j < n; j++) {
				if(chArr[i]==chArr[j]) {
					continue;
				}
				
				for(int k = 0; k < n; k++) {
					Arrays.fill(cache[k], -1);
				}
				
				char tmp = chArr[i];
				chArr[i]=chArr[j];
				chArr[j]=tmp;
				cnt=Math.min(cnt, solve(0,n-1,chArr,n)+1);
//				System.out.println(new String(chArr)+">>"+cnt+","+(solve(0,n-1,chArr,n)+1));
				chArr[j]=chArr[i];
				chArr[i]=tmp;
			}
		}
		for(int k = 0; k < n; k++) {
			Arrays.fill(cache[k], -1);
		}
		cnt=Math.min(cnt, solve(0,n-1,chArr,n));
		System.out.println(cnt);
	}

	private static int[][] cache;
	private static int solve(int i, int j, char[] chArr, int n) {
		if(i==j) {
			return 0;
		}
		if(i+1==j) {
			return chArr[i]==chArr[j]?0:1;
		}
		if(cache[i][j]!=-1) {
			return cache[i][j];
		}
		int ret = solve(i+1,j,chArr,n)+1;
		ret=Math.min(ret, solve(i,j-1,chArr,n)+1);
		if(chArr[i]==chArr[j]) {
			ret=Math.min(ret, solve(i+1,j-1,chArr,n));
		} else {
			ret=Math.min(ret, solve(i+1,j-1,chArr,n)+1);
		}
		cache[i][j]=ret;
		return ret;
	}
}
