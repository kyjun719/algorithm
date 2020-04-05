package algospot.JAEHASAFE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				int n = Integer.parseInt(br.readLine());
				List<String> strList = new ArrayList<>();
				for(int i = 0; i < n; i++) {
					strList.add(br.readLine());
				}
				strList.add(br.readLine());
				System.out.println(solve(strList));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static int solve(List<String> strList) {
		int ret = 0;
		for(int i = 1; i < strList.size(); i++) {
			if(i%2==0) {
				//반시계
				ret += shift(strList.get(i-1), strList.get(i));
			} else {
				//시계
				ret += shift(strList.get(i), strList.get(i-1));
			}
		}
		return ret;
	}

	//문자열 a를 이동시켜 b로 만들때 반시계방향으로 움직여야 되는 횟수 반환
	private static int shift(String a, String b) {
		a = a+a;
		int n = a.length();
		int m = b.length();
		int[] p = getPatialMatches(b);
		int begin = 0, matched = 0;
		
		while(begin < n) {
			if(matched < m && a.charAt(begin+matched) == b.charAt(matched)) {
				matched++;
				//완전 일치시 현재 글자의 시작위치 반환
				if(matched == m) {
					return begin;
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

	//문장에서 접두사와 접미사가 같은 길이 배열 반환(부분 일치 테이블)
	private static int[] getPatialMatches(String b) {
		int n = b.length();
		int[] p = new int[n];
		int begin = 1, matches = 0;
		while(begin+matches < n) {
			if(b.charAt(begin+matches) == b.charAt(matches)) {
				matches++;
				p[begin+matches-1] = matches;
			} else {
				if(matches == 0) {
					begin++;
				} else {
					begin += matches - p[matches-1];
					matches = p[matches-1];
				}
			}
		}
		return p;
	}
}
