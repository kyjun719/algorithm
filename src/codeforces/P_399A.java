package codeforces;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P_399A {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String[] tmp = br.readLine().split(" ");
			int n = Integer.parseInt(tmp[0]);
			int p = Integer.parseInt(tmp[1]);
			int k = Integer.parseInt(tmp[2]);
			
			String answer = "";
			int start_num, end_num;
			if(p - k <= 1) {
				start_num = 1;
			} else {
				start_num = p-k;
				answer += "<< ";
			}
			
			if(p + k >= n) {
				end_num = n;
			} else {
				end_num = p+k;
			}
			for(int i = start_num; i <= end_num; i++) {
				if(i != start_num) {
					answer += " ";
				}
				
				if(i == p) {
					answer += "(" + i + ")";
				} else {
					answer += i;
				}
			}
			
			if(end_num != n) {
				answer += " >>";
			}
			System.out.println(answer);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
