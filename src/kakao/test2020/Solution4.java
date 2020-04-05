package kakao.test2020;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
6
frodo,front,frost,frozen,frame,kakao
5
fro??,????o,fr???,fro???,pro?

3, 2, 4, 1, 0
 */
public class Solution4 {
	///*
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(br.readLine());
			String[] word = new String[n];
			word = br.readLine().split(",");
			
			int q = Integer.parseInt(br.readLine());
			String[] query = new String[q];
			query = br.readLine().split(",");
			System.out.println(Arrays.toString(new Solution4().solution(word, query)));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//*/
	public int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];
		for(int q = 0; q<queries.length; q++) {
			int wildLen = getWildLength(queries[q]);
			//System.out.println("query::"+queries[q]+","+query+","+wildLen);
			int cnt = 0;
			for(int i = 0; i < words.length; i++) {
				//if(isMatch(wildLen, query, words[i])) {
				if(isMatch(wildLen, queries[q], words[i])) {
					cnt++;
				}
			}
			answer[q] = cnt;
		}
		return answer;
    }
	
	private boolean isMatch(int wildLen, String query, String string) {
		if(query.length() != string.length()) {
			return false;
		}
		
		if(query.charAt(0) == '?') {
			query = query.substring(wildLen);
			if(!string.substring(string.length()-query.length()).equals(query)) {
				return false;
			}
			
			return wildLen == (string.length()-query.length());
		} else {
			query = query.substring(0, query.length()-wildLen);
			if(!string.substring(0, query.length()).equals(query)) {
				return false;
			}
			//System.out.println(query+","+wildLen+","+string+">>"+(string.length()-query.length()));
			return wildLen == (string.length()-query.length());
		}
	}
	private int getWildLength(String string) {
		int cnt = 0;
		for(int i = 0; i < string.length(); i++) {
			if(string.charAt(i) == '?') {
				cnt++;
			}
		}
		return cnt;
	}
}
