package algospot.PASS486;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/PASS486
 * @author jun
 * input
3
2 2 10
12 486 486
200 1000000 2000000

 * output
4
1
19
 */
public class Main {
	//just calculate
	static int[] num = new int[10000001];
	private static void preCal() {
		Arrays.fill(num, 1);
		
		for(int i = 2; i < 10000001; i++) {
			int tmp = i;
			while(tmp < 10000001) {
				num[tmp]++;
				tmp += i;
			}
		}
	}
	
	//use Eratosthenes' sieve
	//in submit, runtime error occur
	/*
	static int MAX_N = 10000001;
	static int[] minFactor = new int[MAX_N];
	static int[] minFactorPower = new int[MAX_N];
	static int[] factor = new int[MAX_N];
	private static void preCal2() {
		for(int i = 2; i < MAX_N; i++) {
			minFactor[i] = i;
		}
		
		int n = (int)Math.sqrt(MAX_N);
		for(int i = 2; i <= n; i++) {
			if(minFactor[i] == i) {
				for(int j = i*i; j < MAX_N; j+= i) {
					if(minFactor[j] == j) {
						minFactor[j] = i;
					}
				}
			}
		}
		
		factor[1] = 1;
		for(int i = 2; i < MAX_N; i++) {
			if(minFactor[i] == i) {
				minFactorPower[i] = 1;
				factor[i] = 2;
			} else {
				int p = minFactor[i];
				int m = (int) i/p;
				if(p != minFactor[m]) {
					minFactorPower[i] = 1;
				} else {
					minFactorPower[i] = minFactorPower[m]+1;
				}
				int a = minFactorPower[i];
				factor[i] = (int)(factor[m]/a)*(a+1);
			}
		}
	}
	*/
	
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			preCal();
			//preCal2();
			
			int tc = Integer.parseInt(bf.readLine());
			
			for(int t = 0; t < tc; t++) {
				int[] info = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				int n = info[0];
				int cnt = 0;
				for(int i = info[1]; i <= info[2]; i++) {
					if(num[i] == n) {
					//if(factor[i] == n) {
						cnt++;
					}
				}
				System.out.println(cnt);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
