package algospot.RESTORE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
	//overlap[i][j] : i번째 단어의 뒷부분과 j번째 단어의 앞부분의 겹치는 길이
	static int[][] overlap = new int[16][16];
	static int[][] cache = new int[16][1<<16];
	static int k;
	static List<String> words;
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		try {
			int c = Integer.parseInt(bf.readLine());
			
			for(int tc = 0; tc < c; tc++) {
				words = new ArrayList<>();
				k = Integer.parseInt(bf.readLine());
				for(int i = 0; i < k; i++) {
					words.add(bf.readLine());
					Arrays.fill(overlap[i], -1);
					Arrays.fill(cache[i], -1);
				}
				Arrays.fill(cache[k], -1);
				Arrays.fill(overlap[k], -1);
				
				//입력 단어 리스트중 단어간 중복 제거
				while(true) {
					boolean isRemoved = false;
					for(int i = 0; i < k; i++) {
						for(int j = 0; j < k; j++) {
							if(i != j && words.get(i).contains(words.get(j))) {
								String tmp = words.get(k-1);
								words.remove(j);
								words.add(j, tmp);
								k--;
								isRemoved = true;
							}
						}
					}
					if(!isRemoved) {
						break;
					}
				}

				words.add(k, "");
				//단어간 중복 길이 검색
				for(int i = 0; i <= k; i++) {
					for(int j = 0; j <= k; j++) {
						overlap[i][j] = getOverlap(words.get(i), words.get(j));
					}
				}
				
				//겹치는 부분의 최대값 검색
				maxOverlap(k, 0);

				System.out.println(getWord(k, 0));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//단어간 중복 길이 검색, a의 뒷부분과 b의 앞부분 검색
	static int getOverlap(String a, String b) {
		for(int len = Math.min(a.length(), b.length()); len > 0; len--) {
			if(a.substring(a.length() - len).equals(b.substring(0, len))) {
				return len;
			}
		}
		return 0;
	}
	
	//겹치는 문자열 길이의 최대값 검색
	static int maxOverlap(int now, int used) {
		//모든 단어 검색
		if(used == (1<<k)-1) {
			return 0;
		}
		
		if(cache[now][used] != -1) {
			return cache[now][used];
		}
		
		int ret = 0;
		for(int next = 0; next < k; next++) {
			//해당 단어 미사용시 겹쳐지는 부분이 최대인값 검색
			if((used & (1<<next)) == 0) {
				ret = Math.max(ret, maxOverlap(next, used | 1<<next) + overlap[now][next]);
			}
		}
		
		cache[now][used] = ret;
		return ret;
	}
	
	//
	static String getWord(int last, int used) {
		//모든 단어 검색시
		if(used == ((1<<k)-1)) {
			return "";
		}
		
		for(int next = 0; next < k; next++) {
			//해당 단어 미사용시
			if((used & (1<<next)) == 0) {
				int usedLength = maxOverlap(next, used | 1<<next) + overlap[last][next];
				//현재 단어의 최대 겹치는 길이가 최대값일때 다음 단어의 겹치는 부분이 정답의 일부임
				if(usedLength == maxOverlap(last,used)) {
					String tmp = words.get(next).substring(overlap[last][next]);
					return tmp + getWord(next, used | (1<<next));
				}
			}
		}
		
		return "#";
	}
}
