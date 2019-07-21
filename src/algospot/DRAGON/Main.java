package algospot.DRAGON;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @see https://algospot.com/judge/problem/read/DRAGON
 * @author jun
 * input
4
0 1 2
1 1 5
2 6 5
42 764853475 30

 * output
FX 
FX+YF 
+FX-Y 
FX-YF-FX+YF+FX-YF-FX+YF-FX-YF- 
 */
public class Main {
	static int[] dragonLen = new int [501];
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		dragonLen[0] = 1;
		for(int i = 1; i < 501; i++) {
			dragonLen[i] = Math.min(1000000100, dragonLen[i-1]*2 + 2);
		}
		
		try {
			int c = Integer.parseInt(bf.readLine());
			
			for(int tc = 0; tc < c; tc++) {
				String[] tmp = bf.readLine().split(" ");
				int n = Integer.parseInt(tmp[0]);
				int p = Integer.parseInt(tmp[1]);
				int l = Integer.parseInt(tmp[2]);
				for(int i = 0; i < l; i++) {
					System.out.print(curve("FX", n, p+i-1));
				}
				System.out.println("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static char curve(String seed, int generation, int skip) {
		//System.out.println(seed + "," + generation + "," + skip);
		if(generation == 0) {
			return seed.charAt(skip);
		}
		
		for(char tmp : seed.toCharArray()) {
			if(tmp == 'X' || tmp == 'Y') {
				if(skip >= dragonLen[generation]) {
					skip -= dragonLen[generation];
				} else if(tmp == 'X') {
					return curve("X+YF", generation-1, skip);
				} else {
					return curve("FX-Y", generation-1, skip);
				}
			} else if(skip > 0) {
				skip--;
			} else {
				return tmp;
			}
		}
		return '#';
	}
}
