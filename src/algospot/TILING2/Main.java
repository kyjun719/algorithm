package algospot.TILING2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] tiling = new int[101];
	static int MOD = 1000000007;
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		//칸을 채울수 있는 경우의 수 미리 계산
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
	
	//2xn을 채우는 경우의 수 = 1칸을 채운 후 n-1칸을 채우는 경우의 수 + 2칸을 채운 후 n-2칸을 채우는 경우의 수
	public static void precal() {
		tiling[1] = 1;
		tiling[2] = 2;
		for(int i = 3; i < 101; i++) {
			tiling[i] = (tiling[i-1] + tiling[i-2])%MOD;
		}
	}
}
