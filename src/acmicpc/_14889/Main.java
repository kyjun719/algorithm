package acmicpc._14889;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://www.acmicpc.net/problem/14889
 * @author jun
 *
 */

public class Main {
	private static int n;
	private static int[][] stat;
	private static int answer;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			stat = new int[n][n];
			for(int i = 0; i < n; i++) {
				stat[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			
			answer = solve();
			System.out.println(answer);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve() {
		int start = (1<<n)-1;
		int end = (1<<(n/2))-1;
		
		for(int i = 0; i < (n/2); i++) {
			start ^= (1 << i);
		}
		
		int ret = 987654321;
		while(start > end) {
			int sumS=0,sumL=0;
			
			for(int i = 0; i < n; i++) {
				for(int j = i+1; j < n; j++) {
					if(((start & (1<<i)) > 0) && ((start & (1<<j)) > 0)) {
						sumS += stat[i][j];
						sumS += stat[j][i];
					}
					if(((start & (1<<i)) == 0) && ((start & (1<<j)) == 0)) {
						sumL += stat[i][j];
						sumL += stat[j][i];
					}
				}
			}
			ret = Math.min(ret, Math.abs(sumS-sumL));
			
			start -= 1;
		}
		
		return ret;
	}
}
