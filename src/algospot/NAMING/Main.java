package algospot.NAMING;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 시간초과
 * @see https://algospot.com/judge/problem/read/NAMING
 * @author jun
 * input
ababcabababa
bcabab

 * output
2 4 9 18
 */
public class Main {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			List<Integer> sizeList = solve(br.readLine()+br.readLine());
			Integer[] arr = sizeList.toArray(new Integer[sizeList.size()]);
			Arrays.sort(arr);
			System.out.println(Arrays.toString(arr)
					.replace("[", "")
					.replace("]", "")
					.replace(",", ""));
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	private static List<Integer> solve(String s) {
		//문자열의 각 위치에서 접두사와 접미사 계산
		int[] p = getParitalMatches(s);
		List<Integer> ret = new ArrayList<>();
		
		//문자열 전체는 접두사도 되고 접미사도 됨
		int k = s.length();
		//길이 k보다 짧은 접미사 길이 = S[..k-1]의 접미사 길이 = p[k-1]
		while(k > 0) {
			ret.add(k);
			k = p[k-1];
		}
		
		return ret;
	}

	//각 위치에서의 접두사와 접미사의 최대길이 반환
	private static int[] getParitalMatches(String s) {
		int m = s.length();
		int[] p = new int[m];
		int begin = 1, matched = 0;
		
		while(begin+matched < m) {
			if(s.charAt(begin+matched) == s.charAt(matched)) {
				matched++;
				p[begin+matched-1] = matched;
			} else {
				if(matched == 0) {
					begin++;
				} else {
					begin += matched-p[matched-1];
					matched = p[matched-1];
				}
			}
		}
		
		return p;
	}
}
