package algospot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @see https://algospot.com/judge/problem/read/SUSHI
 * @author jun
 * input
2
6 10000
2500 7
3000 9
4000 10
5000 12
10000 20
15000 1
6 543975612
2500 7
3000 9
4000 10
5000 12
10000 20
15000 1

 * output
28
1631925
 */
public class SUSHI {
	static int[] price = new int[20];
	static int[] need = new int[20];
	static int n;
	static int m;
	
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			for(int tc = 0; tc < c; tc++) {
				String[] info = bf.readLine().split(" ");
				n = Integer.parseInt(info[0]);
				m = Integer.parseInt(info[1])/100;
				
				for(int i = 0; i < n; i++) {
					String[] tmp = bf.readLine().split(" ");
					price[i] = Integer.parseInt(tmp[0])/100;
					need[i] = Integer.parseInt(tmp[1]);
				}
				
				System.out.println(getNeed());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int[] cache = new int[201];
	static int getNeed() {
		cache[0] = 0;
		int ret = 0;
		for(int budget = 1; budget <= m; budget++) {
			int cand = 0;
			for(int i = 0; i < n; i++) {
				if(budget >= price[i]) {
					cand = Math.max(cand, cache[(budget-price[i])%201] + need[i]);
				}
			}
			cache[budget%201] = cand;
			ret = Math.max(ret, cand);
		}
		
		return ret;
	}
}
