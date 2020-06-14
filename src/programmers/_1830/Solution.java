package programmers._1830;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
	static boolean[] used;
	static int[][] idxArr;
	public String solution(String sentence) {
		Queue<Character> charQueue = new LinkedList<>();
		int[] cnt = new int[26];
		used = new boolean[26];
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
				cnt[ch-'a']++;
			} else if(ch == ' ') {
				return "invalid";
			}
		}
		
		//소문자 끝위치 확인
		for(int i = sentence.length()-1; i >= 0; i--) {
			char ch = sentence.charAt(i);
			if(ch >= 'a' && ch <= 'z') {
				if(idxArr[ch-'a'][1] == -1) {
					idxArr[ch-'a'][1] = i;
				}
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
			int e;
			//2개 이상인 경우 규칙1, 2개인경우 규칙2
			if(cnt[ch-'a'] != 2) {
				//role 1
				//규칙1인경우 시작-1~끝+1까지 확인
				ret = parseString(sentence,ch,lastIdx,true);
				e = idxArr[ch-'a'][1]+1;
			} else {
				//role 2
				//규칙2인경우 시작+1~끝-1까지 확인
				ret = parseString(sentence,ch,lastIdx,false);
				e = idxArr[ch-'a'][1];
			}
			
			if(ret == null) {
				return "invalid";
			}
			used[ch-'a'] = true;
			answer += ret+" ";
			lastIdx = e;
		}
		
		System.out.println(lastIdx+">>"+sentence.length());
		if(lastIdx != sentence.length()-1) {
			String sub = sentence.substring(lastIdx+1);
			for(char tmp : sub.toCharArray()) {
				if(!isUpperCase(tmp)) {
					return "invalid";
				}
				answer += tmp+" ";
			}
		}
		if(answer.charAt(answer.length()-1) == ' ') {
			answer = answer.substring(0, answer.length()-1);
		}
		return answer;
	}
	
	
	private String parseString(String sentence, char ch, int lastIdx, boolean isRule1) {
		used[ch-'a'] = true;
		int s,e;
		if(isRule1) {
			s = idxArr[ch-'a'][0]-1;
			e = idxArr[ch-'a'][1]+1;
		} else {
			s = idxArr[ch-'a'][0]+1;
			e = idxArr[ch-'a'][1]-1;
		}
		System.out.println(s+","+e+","+lastIdx+","+isRule1);
		//위치를 넘어가는 경우 실패
		if(s < 0 || s <= lastIdx || e > sentence.length() || !isUpperCase(sentence.charAt(s)) || !isUpperCase(sentence.charAt(e))) {
			return null;
		}
		
		String answer = "";
		if(lastIdx != -1 && lastIdx+(isRule1?1:2) < s) {
			String sub = sentence.substring(lastIdx+1, s+(isRule1?1:-1));
			for(char tmp : sub.toCharArray()) {
				answer += tmp+" ";
			}
		}
		
		if(!isRule1) {
			String ret = "";
			boolean isRule1Checked = false;
			for(int i = s; i <= e; i++) {
				if(!isUpperCase(sentence.charAt(i))) {
					if(isRule1Checked) {
						return null;
					}
					
					ret = parseString(sentence, sentence.charAt(i), lastIdx+1, true);
					if(ret == null) {
						return null;
					}
					i = idxArr[sentence.charAt(i)-'a'][1]+1;
					isRule1Checked = true;
				}
			}
			if(ret.isEmpty()) {
				ret = sentence.substring(s,e+1);
			}
			answer += ret;
		} else {
			for(int i = s; i <= e; i++) {
				if(isUpperCase(sentence.charAt(i))) {
					answer += sentence.charAt(i);
				} else {
					if(sentence.charAt(i) == ch) {
						continue;
					}
					return null;
				}
				System.out.println(sentence.charAt(i)+">>"+answer);
			}
		}
		
		return answer;
	}


	private boolean isUpperCase(char ch) {
		return ch >= 'A' && ch <= 'Z';
	}


	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(new Solution().solution(br.readLine()));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
