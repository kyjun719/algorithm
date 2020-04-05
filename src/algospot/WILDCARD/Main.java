package algospot.WILDCARD;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	private static int[][] cache;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				String w = br.readLine();
				int n = Integer.parseInt(br.readLine());
				ArrayList<String> answer = new ArrayList<>();
				for(int i = 0; i < n; i++) {
					String tmp = br.readLine();
					cache = new int[w.length()+1][tmp.length()+1];
					for(int j = 0; j < cache.length; j++) {
						Arrays.fill(cache[j], -1);
					}
					
					if(solve(0,0,w,tmp) == 1) {
						answer.add(tmp);
					}
				}
				
				Collections.sort(answer);
				for(String tmp : answer) {
					System.out.println(tmp);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static int solve(int w_cnt, int s_cnt, String w, String s) {
		//System.out.println(w+","+s+">>"+w_cnt+","+s_cnt);
		//인덱스가 넘어간 경우
		if(w_cnt > w.length() || s_cnt > s.length()) {
			return 0;
		}
		
		//이전에 검색한 경우
		if(cache[w_cnt][s_cnt] != -1) {
			return cache[w_cnt][s_cnt];
		}
		
		int ret = 0;
		while(true) {
			//두 단어중 하나라도 끝까지 검색한 경우
			if(w_cnt >= w.length() || s_cnt >= s.length()) {
				break;
			}
			
			//?이거나 같은경우 다음글자 비교
			if(w.charAt(w_cnt) == '?' || (w.charAt(w_cnt) == s.charAt(s_cnt))) {
				w_cnt++;
				s_cnt++;
			} else {
				break;
			}
		}
		
		//둘다 끝까지 검색한 경우
		if(w_cnt == w.length() && s_cnt==s.length()) {
			ret = 1;
		}
		//* 문자인 경우
		if(w_cnt < w.length() && s_cnt < s.length() && w.charAt(w_cnt) == '*') {
			for(int i = 0; i < s.length()-s_cnt+1; i++) {
				if(solve(w_cnt+1, s_cnt+i, w,s) == 1) {
					ret = 1;
				}
			}
		}
		cache[w_cnt][s_cnt] = ret;
		return ret;
	}
}
