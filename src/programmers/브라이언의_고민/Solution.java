package programmers.브라이언의_고민;

import java.util.*;

class Solution {
	static int[][] idxArr;
	static boolean[] used;
	public String solution(String sentence) {
		//소문자가 처음 나온 위치;
		Queue<Character> charQueue = new LinkedList<>();
		//소문자 등장갯수
		int[] cnt = new int[26];
		//규칙 사용여부
		used = new boolean[26];
		//소문자의 시작, 끝위치
		idxArr = new int[26][2];
		for(int i = 0; i < 26; i++) {
			Arrays.fill(idxArr[i], -1);
		}
		
		//소문자 갯수 카운트, 소문자 시작위치 확인
		for(int i = 0; i < sentence.length(); i++) {
			char ch = sentence.charAt(i);
			if(ch >= 'a' && ch <= 'z') {
				if(idxArr[ch-'a'][0] == -1) {
					//문자열 앞부터 시작하는 소문자 순서대로 넣음
					charQueue.add(ch);
					idxArr[ch-'a'][0] = i;
				}
				idxArr[ch-'a'][1] = i;
				cnt[ch-'a']++;
			} else if(ch == ' ') {
				return "invalid";
			}
		}
		
		String answer = "";
		int lastIdx = -1;

		while(!charQueue.isEmpty()) {
			char ch = charQueue.poll();
			if(used[ch-'a']) {
				continue;
			}
			
			String ret;
			//2개가 아닌경우 규칙1, 2개인경우 규칙2
			if(cnt[ch-'a'] != 2) {
				//role 1
				//규칙1인경우 시작-1~끝+1까지 확인
				ret = parseRule1String(sentence,ch,lastIdx);
				lastIdx = idxArr[ch-'a'][1]+1;
			} else {
				//role 2
				//규칙2인경우 시작+1~끝-1까지 확인
				ret = parseRule2String(sentence,ch,lastIdx);
				lastIdx = idxArr[ch-'a'][1];
			}
//			System.out.println(idxArr[ch-'a'][0]+","+lastIdx+">>"+ret);
			
			if(ret == null) {
				return "invalid";
			}
			answer += ret+" ";
		}
		
//		System.out.println(lastIdx+">>"+sentence.length());
		if(lastIdx != sentence.length()-1) {
			String sub = sentence.substring(lastIdx+1);
			for(char tmp : sub.toCharArray()) {
				if(!isUpperCase(tmp)) {
					return "invalid";
				}
				answer += tmp;
			}
		}

		if(answer.charAt(answer.length()-1) == ' ') {
			answer = answer.substring(0, answer.length()-1);
		}
		return answer;
	}

	private String parseRule1String(String sentence, char ch, int beforeEndIdx) {
		int s = idxArr[ch-'a'][0]-1;
		int e = idxArr[ch-'a'][1]+1;

		//위치를 넘어가는 경우 실패
		if(!canParse(sentence, s, e, beforeEndIdx)) {
			return null;
		}

		StringBuilder ret = new StringBuilder(getBeforeRuleString(sentence, s, beforeEndIdx, true));

//		System.out.println("parseRule1String sub:"+sentence.substring(s, e+1));

		boolean isSmallChecked = true;
		for(int i = s; i <= e; i++) {
			char c = sentence.charAt(i);
			if(isUpperCase(c)) {
				if(!isSmallChecked) {
					return null;
				}
				ret.append(c);
				isSmallChecked = false;
			} else {
				if(c != ch) {
					return null;
				}
				if(isSmallChecked) {
					return null;
				}
				isSmallChecked = true;
			}
		}

		return ret.toString();
	}

	private String parseRule2String(String sentence, char ch, int beforeEndIdx) {
		int s = idxArr[ch-'a'][0]+1;
		int e = idxArr[ch-'a'][1]-1;

		//위치를 넘어가는 경우 실패
		if(!canParse(sentence, s, e, beforeEndIdx)) {
			return null;
		}

		StringBuilder ret = new StringBuilder(getBeforeRuleString(sentence, s, beforeEndIdx, false));

		//한단어에 규칙이 두개가 적용된경우 aXb...bXa형태
		//규칙2안에 규칙1이 포함될 수 있음

//		System.out.println("parseRule2String sub:"+sentence.substring(s, e+1));
		if((s+1 <= e-1) && !isUpperCase(sentence.charAt(s+1)) && !isUpperCase(sentence.charAt(e-1))) {
			String tmp = parseRule1String(sentence, sentence.charAt(s+1), s-1);

			if(tmp == null) {
				return null;
			}

			used[sentence.charAt(s+1)-'a'] = true;
			ret.append(tmp);
		} else {
			for(int i = s; i <= e; i++) {
				if(isUpperCase(sentence.charAt(i))) {
					ret.append(sentence.charAt(i));
				} else {
					return null;
				}
			}
		}

		return ret.toString();
	}

	private boolean canParse(String sentence, int s, int e, int beforeEndIdx) {
		return s >= 0 && s > beforeEndIdx &&
				e < sentence.length() &&
				isUpperCase(sentence.charAt(s)) && isUpperCase(sentence.charAt(e));
	}

	private String getBeforeRuleString(String sentence, int s, int beforeEndIdx, boolean isRule1) {
		int nc = isRule1 ? s-1 : s-2;

		if(nc >= 0 && beforeEndIdx < nc) {
//			System.out.println(s+"::"+beforeEndIdx+"~"+nc+">>"+sentence.substring(beforeEndIdx+1, nc+1));
			StringBuilder sb = new StringBuilder();
			for(int i = beforeEndIdx+1; i <= nc; i++) {
				sb.append(sentence.charAt(i));
			}
			sb.append(' ');
			return sb.toString();
		}

		return "";
	}

	private boolean isUpperCase(char ch) {
		return ch >= 'A' && ch <= 'Z';
	}


	public static void main(String[] args) {
		HashMap<String, List<String>> map = new HashMap<>();
		map.put("HaEaLaLaObWORLDb", Arrays.asList("HELLO WORLD"));
		map.put("SpIpGpOpNpGJqOqA", Arrays.asList("SIGONG JOA", "SIGONG J O A"));
		map.put("AxAxAxAoBoBoB", Arrays.asList("invalid"));
		map.put("aIaAM", Arrays.asList("I AM"));
		map.put("AAAaBaAbBBBBbCcBdBdBdBcCeBfBeGgGGjGjGRvRvRvRvRvR", Arrays.asList("AAA B A BBBB C BBBB C BB GG G G G RRRRRR", "AA ABA BBBB C BBBB C BB GG GGG RRRRRR"));
		map.put("aaA", Arrays.asList("invalid"));
		map.put("Aaa", Arrays.asList("invalid"));
		map.put("HaEaLaLaOWaOaRaLaD", Arrays.asList("invalid"));
		map.put("aHELLOWORLDa", Arrays.asList("HELLOWORLD"));
		map.put("HaEaLaLObWORLDb", Arrays.asList("HELL O WORLD"));
		map.put("aHbEbLbLbOacWdOdRdLdDc", Arrays.asList("HELLO WORLD"));
		map.put("abAba", Arrays.asList("invalid"));
		map.put("HELLO WORLD", Arrays.asList("invalid"));
		map.put("xAaAbAaAx", Arrays.asList("invalid"));
		map.put("AbAaAbAaCa", Arrays.asList("invalid"));
		map.put("AbAaAbAaC", Arrays.asList("invalid"));
		map.put("a", Arrays.asList("invalid"));
		map.put("aHbEbLbLbOacWdOdRdLdDcaHbEbLbLbOacWdOdRdLdDc", Arrays.asList("invalid"));
		map.put("AaAaAcA", Arrays.asList("A A AA"));
		map.put("abHELLObaWORLD", Arrays.asList("invalid"));
		map.put("A B", Arrays.asList("invalid"));
		map.put("AaAaAaAAAAAaAAAa", Arrays.asList("invalid"));
		map.put("AaABbBCcC", Arrays.asList("AA BB CC"));
		map.put("abAba", Arrays.asList("invalid"));
		map.put("AbAaAbAaCa", Arrays.asList("invalid"));
		map.put("AAAAbbbAAA", Arrays.asList("invalid"));

		for(String key : map.keySet()) {
			String ret = new Solution().solution(key);
			System.out.println(key+","+ret+"="+(map.get(key).contains(ret)));
			System.out.println();
		}
	}
}
