package algospot.NAMING;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 시간초과
 */
public class Main {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println(solve(br.readLine()+br.readLine()));
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	private static String solve(String s) {
		//s의 부분 문자열중 접미사와 접두사가 같은길이 계산 
		//문자열의 각 위치에서 접두사와 접미사 계산(부분 일치 테이블)
		int[] p = getParitalMatches(s);
		StringBuffer sb = new StringBuffer();
		
		//문자열 전체는 접두사도 되고 접미사도 됨
		int k = s.length();
		//길이 k보다 짧은 접미사 길이 = S[..k-1]의 접미사 길이 = p[k-1]
		while(k > 0) {
			sb.append(k+" ");
			k = p[k-1];
		}
		
		return sb.toString().substring(0, sb.length()-1);
	}

	//각 위치에서의 접두사와 접미사의 최대길이 반환(부분 일치 테이블)
	private static int[] getParitalMatches(String s) {
		int m = s.length();
		int[] p = new int[m];
		int begin = 1, matched = 0;
		
		while(begin+matched < m) {	
			if(s.charAt(begin+matched) == s.charAt(matched)) {
				//일치하는 경우 현재 위치에 일치하는 길이 저장
				matched++;
				p[begin+matched-1] = matched;
			} else {
				if(matched == 0) {
					begin++;
				} else {
	 				//다음 시작점은 matched-1만큼 문자열 중 접미사가 시작되는 부분
					begin += matched-p[matched-1];
	 				//다음 시작점에서 일치하는 갯수는 matched-1만큼의 문자열에서 접미사의 길이
					matched = p[matched-1];
				}
			}
		}
		
		return p;
	}
}
