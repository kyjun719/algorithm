package algospot.ASYMTILING;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
			//넓이가 width의 비대칭 타일의 갯수 = 넓이가 width의 전체 타일 갯수 - 넓이가 width/2의 타일 갯수  
			tiling[i] = (tiling[i-1] + tiling[i-2])%MOD;
			asynctiling[i] = tiling[i] + MOD;
			asynctiling[i] -= tiling[i/2];
			asynctiling[i] %= MOD;
			if(i%2 == 0) {
				//짝수개의 경우 경계에 블록이 누워있는 경우도 있어 뺌
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
