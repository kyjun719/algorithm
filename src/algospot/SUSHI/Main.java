package algospot.SUSHI;

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
public class Main {
	//100의 배수이므로 입력값/100
	static int[] price = new int[20];
	//초밥의 선호도
	static int[] need = new int[20];
	//초밥 갯수
	static int n;
	//예산/100
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
	
	//초밥 가격은 1~200으로 먹기 이전의 최대 만족도는 (현재예산-가격)%201번째에 저장됨
	static int[] cache = new int[201];
	static int getNeed() {
		cache[0] = 0;
		int ret = 0;
		//1부터 최대 예산까지 대한 최대 만족도 계산
		for(int budget = 1; budget <= m; budget++) {
			int cand = 0;
			for(int i = 0; i < n; i++) {
				//초밥 가격이 예산안일때 먹었을때와 먹지 않았을때의 최대 선호도 구함 
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
