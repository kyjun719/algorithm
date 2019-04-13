package algospot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see https://algospot.com/judge/problem/read/ALLERGY
 * @author jun
 * input
2
4 6
cl bom dara minzy
2 dara minzy
2 cl minzy
2 cl dara
1 cl
2 bom dara
2 bom minzy
10 7
a b c d e f g h i j
6 a c d h i j
3 a d i
7 a c f g h i j
3 b d g
5 b c f h i
4 b e g j
5 b c g h i 

 * output
2
3
 */
public class ALLERGY {
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			
			for(int tc = 0; tc < c; tc++) {
				String[] tmp = bf.readLine().split(" ");
				int n = Integer.parseInt(tmp[0]);
				int m = Integer.parseInt(tmp[1]);
				List<String> nameList = Arrays.stream(bf.readLine().split(" "))
						.collect(Collectors.toList());
				long[][] cache = new long[2][1<<(m%25)];
				long[] eatArray = new long[m];
				for(int i = 0; i < m; i++) {
					String[] infoTmp = bf.readLine().split(" ");
					int info = 0;
					for(int j = 1; j <= Integer.parseInt(infoTmp[0]); j++) {
						info += (1 << nameList.indexOf(infoTmp[j]));
					}
					eatArray[i] = info;
					cache[i/25][1<<(i%25)] = info;
				}
				
				int cnt = 987654321;
				for(long i = 0; i < (1<<m); i++) {
					if(cnt < Long.toBinaryString(i).replace("0", "").length()) {
						continue;
					}
					
					int rowIdx = (int) (i/(1<<25));
					int colIdx = (int) i%(1<<25);
					if(cache[rowIdx][colIdx] == 0) {
						for(int j = 0; j < Long.toBinaryString(i).length(); j++) {
							if((i & (1<<j)) != 0) {
								cache[rowIdx][colIdx] |= cache[j/25][1<<(j%25)];
							}
						}
					}
					
					if(cache[rowIdx][colIdx] == ((1<<n) -1)) {
						int tmpCnt = Long.toBinaryString(i).replace("0", "").length();
						cnt = Math.min(cnt, tmpCnt);
					}
				}
				System.out.println(cnt);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
