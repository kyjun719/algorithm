package algospot.RESTORE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @see https://algospot.com/judge/problem/read/RESTORE
 * @author jun
 * input
3
3
geo
oji
jing
2
world
hello
3
abrac
cadabra
dabr

 * output
geojing
helloworld
cadabrac
 */
public class Main {
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
				
				for(int i = 0; i <= k; i++) {
					for(int j = 0; j <= k; j++) {
						overlap[i][j] = getOverlap(words.get(i), words.get(j));
					}
				}
				
				maxOverlap(k, 0);

				System.out.println(getWord(k, 0));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int getOverlap(String a, String b) {
		for(int len = Math.min(a.length(), b.length()); len > 0; len--) {
			if(a.substring(a.length() - len).equals(b.substring(0, len))) {
				return len;
			}
		}
		return 0;
	}
	
	static int maxOverlap(int now, int used) {
		if(used == (1<<k) -1) {
			return 0;
		}
		
		if(cache[now][used] != -1) {
			return cache[now][used];
		}
		
		int ret = 0;
		for(int next = 0; next < k; next++) {
			if((used & (1<<next)) == 0) {
				ret = Math.max(ret, maxOverlap(next, used | 1<<next) + overlap[now][next]);
			}
		}
		
		cache[now][used] = ret;
		return ret;
	}
	
	static String getWord(int last, int used) {
		if(used == ((1<<k) - 1)) {
			return "";
		}
		
		for(int next = 0; next < k; next++) {
			if((used & (1<<next)) == 0) {
				int usedLength = maxOverlap(next, used | 1<<next) + overlap[last][next];
				if(usedLength == maxOverlap(last,used)) {
					String tmp = words.get(next).substring(overlap[last][next]);
					return tmp
							+ getWord(next, used | (1<<next));
				}
			}
		}
		
		return "#";
	}
}
