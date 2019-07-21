package algospot.NUMB3RS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/NUMB3RS
 * @author jun
 * input
2
5 2 0
0 1 1 1 0
1 0 0 0 1
1 0 0 0 0
1 0 0 0 0
0 1 0 0 0
3
0 2 4
8 2 3
0 1 1 1 0 0 0 0
1 0 0 1 0 0 0 0
1 0 0 1 0 0 0 0
1 1 1 0 1 1 0 0
0 0 0 1 0 0 1 1
0 0 0 1 0 0 0 1
0 0 0 0 1 0 0 0
0 0 0 0 1 1 0 0
4
3 1 2 6
 * output
0.83333333 0.00000000 0.16666667
0.43333333 0.06666667 0.06666667 0.06666667
 */
public class Main {
	static int n,d,p;
	static int[][] connected = new int[50][50];
	static int[] deg = new int[50];
	static double[][] cache = new double[50][100];
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			
			for(int tc = 0; tc < c; tc++) {
				String[] tmp = bf.readLine().split(" ");
				n = Integer.parseInt(tmp[0]);
				d = Integer.parseInt(tmp[1]);
				p = Integer.parseInt(tmp[2]);
				for(int i = 0; i < n; i++) {
					Arrays.fill(cache[i], -1);
					int cnt = 0;
					String[] line = bf.readLine().split(" ");
					for(int j = 0; j < n; j++) {
						connected[i][j] = Integer.parseInt(line[j]);
						cnt += connected[i][j] == 1? 1:0;
					}
					deg[i] = cnt;
				}
				
				int t = Integer.parseInt(bf.readLine());
				String[] q_arr = bf.readLine().split(" ");
				for(int i = 1; i <= t; i++) {
					int q = Integer.parseInt(q_arr[i-1]);
					System.out.print(search(q, d));
					if(i != t) {
						System.out.print(" ");
					}
				}
				System.out.println();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static double search(int here, int days) {
		if(days == 0) {
			return here == p? 1:0;
		}
		
		if(cache[here][days] != -1) {
			return cache[here][days];
		}
		
		double ret = 0;
		for(int there = 0; there < n; there++) {
			if(connected[here][there] == 1) {
				ret += search(there, days-1)/deg[there];
			}
		}
		cache[here][days] = ret;
		return ret;
	}
}
