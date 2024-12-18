package algospot.MORSE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	//c[i][j] : 조합순열
	static long[][] C = new long[201][201];
	static void preCal() {
		C[0][0] = 1;
		for(int i = 1; i <= 200; i++) {
			C[i][0] = 1;
			C[i][i] = 1;
			for(int j = 1; j < i; j++) {
				//탐색값의 최대가 1000000000이므로 overflow 방지
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
	//사전순으로 출력하므로 장점으로 시작하는 신호가 앞에 있음
	static String morse(int n, int m, long k) {
		//남은 장점이 없는 경우 단점으로 길이만큼 반환  
		if(n == 0) {
			char[] tmp = new char[m];
			Arrays.fill(tmp, 'o');
			return new String(tmp);
		}
		//단점이 n, 장점이 m일때 -로 시작하는 갯수 = (n+m-1)C(n-1)
		if(k <= C[n+m-1][n-1]) {
			//신호의 k번째가 장점으로 시작하는 갯수보다 작은경우
			return "-" + morse(n-1, m, k);
		} else {
			//신호의 k번째가 장점으로 시작하는 갯수보다 큰경우
			//장점으로 시작하는 갯수만큼 넘어감
			return "o" + morse(n, m-1, k - C[n+m-1][n-1]);
		}
	}
}
