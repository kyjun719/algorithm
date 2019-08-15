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
	//2~10000001까지 약수 갯수 계산
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
	
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			preCal();
			
			int tc = Integer.parseInt(bf.readLine());
			
			for(int t = 0; t < tc; t++) {
				int[] info = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				int n = info[0];
				int cnt = 0;
				//범위내에서 약수갯수 확인
				for(int i = info[1]; i <= info[2]; i++) {
					if(num[i] == n) {
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
