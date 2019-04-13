package algospot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/NUMBERGAME
 * @author jun
 * input 
3
5
-1000 -1000 -3 -1000 -1000 
6
100 -1000 -1000 100 -1000 -1000 
10
7 -5 8 5 1 -4 -8 6 7 9 

 * output
-1000
1100
7
 */
public class NUMBERGAME {
	static int[] arr;
	static int[][] cache = new int[50][50];
	static int MIN = -987654321;
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			for(int tc = 0; tc < c; tc++) {
				int n = Integer.parseInt(bf.readLine());
				for(int i = 0; i < n; i++) {
					Arrays.fill(cache[i], MIN);
				}
				arr = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				System.out.println(game(0, arr.length-1));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int game(int left, int right) {
		if(left > right) {
			 return 0;
		}
		
		if(cache[left][right] != MIN) {
			 return cache[left][right];
		}
		
		int ret = MIN;
		ret = Math.max(arr[left] - game(left+1, right), arr[right] - game(left, right - 1));
		
		if(right - left + 1 >= 2) {
			ret = Math.max(ret, -game(left, right-2));
			ret = Math.max(ret, -game(left+2, right));
		}
		cache[left][right] = ret;
		return ret;
	}
}
