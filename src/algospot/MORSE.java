package algospot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/MORSE
 * @author jun
 * input
3
2 2 4
4 8 13
6 4 1

 * output
o--o
--o-ooo-oooo
------oooo
 */
public class MORSE {
	static long[][] C = new long[201][201];
	static void preCal() {
		C[0][0] = 1;
		for(int i = 1; i <= 200; i++) {
			C[i][0] = 1;
			C[i][i] = 1;
			for(int j = 1; j < i; j++) {
				C[i][j] = Math.min(1000000100, C[i-1][j-1] + C[i-1][j]);
			}
		}
	}
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		preCal();
		
		try {
			int c = Integer.parseInt(bf.readLine());
			
			for(int tc = 0; tc < c; tc++) {
				String[] tmp = bf.readLine().split(" ");
				int n = Integer.parseInt(tmp[0]);
				int m = Integer.parseInt(tmp[1]);
				long k = Integer.parseInt(tmp[2]);
				
				System.out.println(morse(n,m,k));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//n : 단점갯수, m : 장점 갯수
	static String morse(int n, int m, long k) {
		if(n == 0) {
			char[] tmp = new char[m];
			Arrays.fill(tmp, 'o');
			return new String(tmp);
		}
		//단점이 n, 장점이 m일때 -로 시작하는 갯수 = (n+m-1)C(n-1)
		if(k <= C[n+m-1][n-1]) {
			return "-" + morse(n-1, m, k);
		} else {
			return "o" + morse(n, m-1, k - C[n+m-1][n-1]);
		}
	}
}
