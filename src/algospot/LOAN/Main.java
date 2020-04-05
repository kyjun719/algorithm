package algospot.LOAN;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int tc = Integer.parseInt(bf.readLine());
			
			for(int t = 0; t < tc; t++) {
				String[] tmp = bf.readLine().split(" ");
				//잔금
				double n = Double.parseDouble(tmp[0]);
				//갚을 월수
				int m = Integer.parseInt(tmp[1]);
				//월 이자
				double p = Double.parseDouble(tmp[2])/100;
				
				//0개월 잔액: n, 1개월 잔액: np-c, 2개월 잔액: np^2-c(p+1)
				//...
				//m개월 잔액: np^m-c*(p^m-1+p^(m-1)+...+1)
				double seed = 1+(p/12);
				double power = 1;
				double sig = 0;
				for(int i = 0; i < m; i++) {
					sig += power;
					power *= seed;
				}
				
				//n*power - c*sig = 0
				//c=power/sig*n
				System.out.println(String.format("%.12f", power/sig*n));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
