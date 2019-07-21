package algospot.SNAIL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/SNAIL
 * @author jun
 * input
4
5 4
5 3
4 2
3 2
 * output
0.9960937500
0.8437500000
0.5625000000
0.9375000000
 */
public class Main {
	static int n, m;
	static double[][] cache = new double[1001][1001];
	public static void main(String[] args) {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			int c = Integer.parseInt(bf.readLine());
			
			for(int tc = 0; tc < c; tc++) {
				String[] tmp = bf.readLine().split(" ");
				n = Integer.parseInt(tmp[0]);
				m = Integer.parseInt(tmp[1]);
				
				for(int i = 0; i <= n; i++) {
					Arrays.fill(cache[i], -1);
				}
				System.out.println(snail(0, 0));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static double snail(int days, int meter) {
		if(meter >= n) {
			return 1;
		}
		
		if(days == m) {
			return meter >= n? 1 : 0;
		}
		
		if(cache[days][meter] != -1) {
			return cache[days][meter];
		}
		
		double ret = 0.25*snail(days+1, meter+1) + 0.75*snail(days+1, meter+2);
		cache[days][meter] = ret;
		
		return ret;
	}
}
