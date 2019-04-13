package codeforces;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P_1032B {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String name = br.readLine();
			int len = name.length();
			for(int i = 1; i <= 5; i++) {
				for(int j = 0; j <= i; j++) {
					int tmp = len + j;
					if((tmp/i <= 20) && (tmp%i == 0)) {
						int asterisk_num = j;
						int col_num = (int)tmp/i;
						System.out.println(String.format("%d %d", i, col_num));
						int next_start_idx = 0;
						for(int k = 1; k <= i; k++) {
							if(asterisk_num == 0) {
								System.out.println(	name.substring(next_start_idx, col_num + next_start_idx));
								next_start_idx += col_num;
							} else {
								asterisk_num--;
								System.out.println("*" + 
										name.substring(next_start_idx, col_num + next_start_idx - 1));
								next_start_idx += col_num - 1;
							}
						}
						return;
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
