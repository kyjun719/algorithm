package algospot.CLOCKSYNC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int ret;
	static int[][] arrSwitch = {
			{0,1,2},
			{3, 7, 9, 11},
			{4, 10, 14, 15},
			{0, 4, 5, 6, 7},
			{6, 7, 8, 10, 12},
			{0, 2, 14, 15},
			{3, 14, 15},
			{4, 5, 7, 14, 15},
			{1, 2, 3, 4, 5},
			{3, 4, 5, 9, 13}
	};
	static int MAX_CNT = 4;
	public static void main(String[] args) {		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				st = new StringTokenizer(br.readLine());
				int[] cntSwitch = new int[10];
				int[] clock = new int[16];
				for(int i = 0; i < 16; i++) {
					clock[i] = Integer.parseInt(st.nextToken());
				}
				
				ret = 987654321;

				solve(0, 0, cntSwitch, clock);
				System.out.println(ret == 987654321?-1:ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void solve(int idx, int cnt, int[] cntSwitch, int[] clock) {
		if(isAllMatched(clock)) {
			ret = cnt;
			return;
		}
		
		if(cnt >= ret || idx >= 10) {
			return;
		}
		
		//System.out.println(cnt+">>"+Arrays.toString(cntSwitch)+","+Arrays.toString(clock));
		
		//press switch
		for(int i = 0; i < MAX_CNT; i++) {
			solve(idx+1, cnt+i, cntSwitch, clock);
			pressSwitch(clock, idx, 3);
		}
	}

	private static boolean isAllMatched(int[] clock) {
		for(int i = 0; i < 16; i++) {
			if(clock[i] != 12) {
				return false;
			}
		}
		return true;
	}

	private static void pressSwitch(int[] clock, int idx, int val) {
		// TODO Auto-generated method stub
		for(int i = 0; i < arrSwitch[idx].length; i++) {
			clock[arrSwitch[idx][i]] += val;
			if(clock[arrSwitch[idx][i]] > 12) {
				clock[arrSwitch[idx][i]] %= 12;
			}
			if(clock[arrSwitch[idx][i]] <= 0) {
				clock[arrSwitch[idx][i]] += 12;
			}
		}
	}
}

