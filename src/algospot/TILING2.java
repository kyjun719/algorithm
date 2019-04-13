package algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @see https://algospot.com/judge/problem/read/TILING2
 * @author jun
 * input
3
1
5
100
 * output
1
8
782204094
 */
public class TILING2 {
	static int[] tiling = new int[101];
	static int MOD = 1000000007;
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		precal();
		
		int c;
		try {
			c = Integer.parseInt(bf.readLine());
			for(int i = 0; i < c; i++) {
				int n = Integer.parseInt(bf.readLine());
				System.out.println(tiling[n]);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void precal() {
		tiling[1] = 1;
		tiling[2] = 2;
		for(int i = 3; i < 101; i++) {
			tiling[i] = (tiling[i-1] + tiling[i-2])%MOD;
		}
	}
}
