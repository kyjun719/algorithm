package algospot.PALINDROMIZE;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int tc = Integer.parseInt(br.readLine());
			
			for(int t = 0; t<tc; t++) {
				String a = br.readLine();
				String b = new StringBuffer(a).reverse().toString();
				int overlap = maxOveralp(a, b);
				System.out.println(a.length()*2-overlap);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//원래 문자열과 뒤집은 문자열의 최대 중복길이 계산
	private static int maxOveralp(String a, String b) {
		int n = a.length();
		int m = b.length();
		//뒤집은 문자의 접두사와 접미사가 같은 길이 계산
		int[] p = getPartialMathes(b);
		
		int begin = 0, matched = 0;
		while(begin < n) {
			//원래 문자열과 뒤집은 문자열의 문자가 일치하는 경우
			if(matched < m && a.charAt(begin+matched) == b.charAt(matched)) {
				matched++;
				//문자열 끝에 도달한 경우
				if(begin+matched == n) {
					return matched;
				}
			} else {
				if(matched == 0) {
					begin++;
				} else {
					begin += matched - p[matched-1];
					matched = p[matched-1];
				}
			}
		}
		
		return 0;
	}

	//해당 문장에서 접미사와 접두사가 같은 최대 길이 반환(부분 일치 테이블)
	private static int[] getPartialMathes(String b) {
		int n = b.length();
		int begin=1, matched=0;
		int[] ret = new int[n];
		
		while(begin+matched < n) {
			if(b.charAt(begin+matched) == b.charAt(matched)) {
				matched++;
				ret[begin+matched-1] = matched;
			} else {
				if(matched == 0) {
					begin++;
				} else {
					begin += matched - ret[matched-1];
					matched = ret[matched-1];
				}
			}
		}
		return ret;
	}
}
