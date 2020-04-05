package algospot.HABIT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int tc = Integer.parseInt(br.readLine());
			
			for(int t = 0; t < tc; t++) {
				int k = Integer.parseInt(br.readLine());
				String s = br.readLine();
				System.out.println(getMaxPartial(k, s));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//k개 이상 중복되는 부분 문자열의 최대길이 반환
	private static int getMaxPartial(int k, String s) {
		int n = s.length();
		Integer[] suffix = getSuffixArray(s);
		
		int ret = 0;
		for(int i = 0; i+k <= n; i++) {
			//사전순으로 정렬된 접미사배열에서 k개의 접미사중 중복되는 접두사의 최대길이 계산
			ret = Math.max(ret, getCommonPrefix(s, suffix[i], suffix[i+k-1]));
		}
		
		return ret;
	}

	//문자열 s의 부분문자열의 최대 중복길이 반환
	private static int getCommonPrefix(String s, int i, int j) {
		int k = 0;
		while(i < s.length() && j < s.length() && s.charAt(i) == s.charAt(j)) {
			i++; j++; k++;
		}
		return k;
	}

	//사전순으로 정리된 접미사의 시작위치 배열 반환
	private static Integer[] getSuffixArray(String s) {
		int n = s.length();
		//문자열 그룹 초기화
		int[] group = new int[n+1];
		group[n] = -1;
		for(int i = 0; i < n; i++) {
			group[i] = s.charAt(i);
		}
		
		//사전별로 정리된 접미사의 문자열내 인덱스 값을 가진 배열
		Integer[] perm = new Integer[n];
		for(int i = 0; i< n; i++) {
			perm[i] = i;
		}
		
		int t = 1;
		while(t < n) {
			SuffixComparator comp = new SuffixComparator(t, group);
			//그룹값에 따라 문자열 순서 정렬
			Arrays.sort(perm, comp);
			
			t *= 2;
			if(t >= n) {
				break;
			}
			
			int[] newGroup = new int[n+1];
			newGroup[n] = -1;
			newGroup[perm[0]] = 0;
			//2t글자를 기준으로 한 그룹 정보 만듦
			for(int i = 1; i < n; i++) {
				if(comp.compare(perm[i-1], perm[i]) < 0) {
					newGroup[perm[i]] = newGroup[perm[i-1]] + 1; 
				} else {
					newGroup[perm[i]] = newGroup[perm[i-1]];
				}
			}
			group = newGroup;
		}

		return perm;
	}
	
	private static class SuffixComparator implements Comparator<Integer> {
		private int t;
		private int[] group;
		
		SuffixComparator(int t, int[] group) {
			this.t = t;
			this.group = group;
		}

		//문자열 비교
		@Override
		public int compare(Integer o1, Integer o2) {
			//현재 글자의 그룹값이 다르면 비교
			if(group[o1] != group[o2]) return group[o1]-group[o2];
			//현재 글자의 그룹값이 같을때 다음 t번째 글자의 그룹값 비교
			return group[o1+t]-group[o2+t];
		}
	}
}
