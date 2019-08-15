package algospot.DARPA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/DARPA
 * @author jun
 * input
3
2 4 
80 100 120 140 
4 4 
80 100 120 140.00
4 7 
0 70 90 120 200 210 220

 * output
60.00
20.00
50.00
 */
public class Main {
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			for(int tc = 0; tc < c; tc++) {
				int[] tmp = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				int n = tmp[0];
				int m = tmp[1];
				double[] location = Arrays.stream(bf.readLine().split(" "))
						.mapToDouble(Double::parseDouble)
						.toArray();
				
				System.out.println(String.format("%.2f", getMaxRange(n, location)));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static double getMaxRange(int n, double[] location) {
		double low = 0;
		double high = 241;
		
		//241/(2^23) < 0.0001
		for(int i = 0; i < 25; i++) {
			double mid = (low + high) / 2;
			//n대를 mid 이상의 거리로 설치할 수 있는 경우
			if(canSearch(n, location, mid)) {
				low = mid;
			} else {
				high = mid;
			}
		}
		
		return low;
	}
	
	//dist 거리 이상으로 카메라를 n대 이상 설치할 수 있는지 여부 반환
	static boolean canSearch(int n, double[] location, double dist) {
		int cnt = 0;
		double limit = -1;
		for(int i = 0; i < location.length; i++) {
			//카메라 위치가 설치 가능위치와 크거나 같은 경우 설치 후 다음 설치 위치로 바꿈
			if(limit <= location[i]) {
				cnt++;
				limit = location[i] + dist;
			}
		}
		
		return cnt >= n;
	}
}
