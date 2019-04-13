package algospot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/TRIPATHCNT
 * @author jun
 * input
2
4
1
1 1 
1 1 1 
1 1 1 1 
4
9
5 7
1 3 2
3 5 5 6
 * output
8
3
 */
public class TRIPATHCNT {
	static int n;
	static int[][] arr = new int[101][101];
	static int[][] cache = new int[101][101];
	static int[][] countCache = new int[101][101];
	
	public static void main(String args[]) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		try {
			int c = Integer.parseInt(bf.readLine());
			for(int tc = 0; tc < c; tc++) {
				n = Integer.parseInt(bf.readLine());
				
				for(int i = 1; i <= n; i++) {
					Arrays.fill(cache[i], -1);
					Arrays.fill(countCache[i], -1);
					String[] tmp = bf.readLine().split(" ");
					for(int j = 1; j <= i; j++) {
						arr[i][j] = Integer.parseInt(tmp[j-1]);
					}
				}
				System.out.println(count(1, 1));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int count(int y, int x) {
		if(y == n) {
			return 1;
		}
		
		if(countCache[y][x] != -1) {
			return countCache[y][x];
		}
		
		int ret = 0;
		if(path(y+1, x) >= path(y+1, x+1)) {
			ret += count(y+1, x);
		}
		if(path(y+1, x) <= path(y+1, x+1)) {
			ret += count(y+1, x+1);
		}
		
		countCache[y][x] = ret;
		return ret;
	}
	
	static int path(int y, int x) {
		if(y == n) {
			return arr[y][x];
		}
		
		if(cache[y][x] != -1) {
			return cache[y][x];
		}
		
		int ret = Math.max(path(y+1, x), path(y+1, x+1)) + arr[y][x];
		cache[y][x] = ret;
		return ret;
	}
}