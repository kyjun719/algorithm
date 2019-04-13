package codeforces;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P_393A {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String inputString = br.readLine();
			//nineteen
			//n=3,i=1,e=3,t=1
			int cnt_n = 0, cnt_i=0, cnt_e=0, cnt_t=0;
			for(byte b : inputString.getBytes()) {
				if(b == 'n') cnt_n++;
				else if(b == 'i') cnt_i++;
				else if(b == 'e') cnt_e++;
				else if(b == 't') cnt_t++;
			}
			cnt_n = cnt_n > 3? 1+(cnt_n-3)/2 : cnt_n/3;
			cnt_e /= 3;
			int answer = Math.min(cnt_t, Math.min(cnt_i, Math.min(cnt_n, cnt_e)));
			System.out.println(answer);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
