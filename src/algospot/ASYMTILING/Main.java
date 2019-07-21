package algospot.ASYMTILING;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @see https://algospot.com/judge/problem/read/ASYMTILING
 * @param args
 * asyncTiling[width] = tiling[width] - syncTiling[width]
 *                    = tiling[width] - tiling[width/2]  
 * 
 * input
3
2
4
92
 * output
0
2
913227494
 */
public class Main {
	static int[] asynctiling = new int[101];
	static int MOD = 1000000007;
	static void precal() {
		int[] tiling = new int[101];
		tiling[1] = 1;
		tiling[2] = 2;
		
		asynctiling[1] = 0;
		asynctiling[2] = 0;
		for(int i = 3; i < 101; i++) {
			tiling[i] = (tiling[i-1] + tiling[i-2])%MOD;
			asynctiling[i] = tiling[i] + MOD;
			asynctiling[i] -= tiling[i/2];
			asynctiling[i] %= MOD;
			if(i%2 == 0) {
				asynctiling[i] += MOD;
				asynctiling[i] -= tiling[i/2-1];
				asynctiling[i] %= MOD;
			}
		}
	}
	public static void main(String[] args) {
		try {
			precal();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int c = Integer.parseInt(br.readLine());
			for(int tc = 0; tc < c; tc++) {
				int n = Integer.parseInt(br.readLine());
				System.out.println(asynctiling[n]);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
