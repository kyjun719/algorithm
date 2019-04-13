package algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see  https://algospot.com/judge/problem/read/ZIMBABWE
 * @author jun
 * input
4
321 3
123 3
422 2
12738173912 7
 
 * output
5
0
2
11033
 */
public class ZIMBABWE {
	static int n, m;
	static int[] nowPrice;
	static int[] number;
	static int[][][] cache = new int[2][20][1<<14];
	static int MOD = 1000000007;
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int c;
		try {
			c = Integer.parseInt(bf.readLine());
			for(int tc = 0; tc < c; tc++) {
				String[] tmp = bf.readLine().split(" ");
				nowPrice = tmp[0].chars().map(item -> item-48).toArray();
				number = nowPrice.clone();
				Arrays.sort(number);
				m = Integer.parseInt(tmp[1]);
				for(int i = 0; i < m; i++) {
					Arrays.fill(cache[0][i], -1);
					Arrays.fill(cache[1][i], -1);
				}
				
				n = nowPrice.length;
				System.out.println(zimbabwe(0, 0, 0, 0));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static int zimbabwe(int index, int used, int mod, int less) {
		if(index == n) {
			return mod == 0 && less == 1? 1 : 0;
		}
		
		if(cache[less][mod][used] != -1) {
			return cache[less][mod][used];
		}
		
		int ret = 0;
		for(int next = 0; next < n; next++) {
			//System.out.println(Integer.toBinaryString(used) + "::" + nowPrice[index] + "," + number[next]);
			if((used & (1<<next)) == 0) {
				if(less == 0 && nowPrice[index] < number[next]) {
					continue;
				}
				
				if(next > 0 && number[next-1] == number[next] && (used & (1<<(next-1))) == 0) {
					continue;
				}
				
				//int index, int used, int mod, int less
				int nextUsed = used | 1<<next;
				int nextMod = (mod * 10 + number[next]) % m;
				int nextLess = less | (nowPrice[index] > number[next] == true? 1:0);
				ret += zimbabwe(index + 1, nextUsed, nextMod, nextLess);
				ret %= MOD;
			}
		}
		
		cache[less][mod][used] = ret;
		return ret;
	}
}
