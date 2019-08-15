package algospot.ROOTS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/ROOTS
 * @author jun
 * input
2
3
1.00 -6.00 11.00 -6.00
2
1.00 -12.50 31.50

 * output
1.000000000000 2.000000000000 3.000000000000 
3.500000000000 9.000000000000 
 */
public class Main {
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			
			for(int tc = 0; tc < c; tc++) {
				int n = Integer.parseInt(bf.readLine());
				double[] num = Arrays.stream(bf.readLine().split(" "))
								.mapToDouble(Double::parseDouble)
								.toArray();
				double[] answer = solve(n, num);
				String strAnswer = "";
				for(int i = 0; i < answer.length; i++) {
					strAnswer += String.format("%.12f", answer[i]) + " ";
				}
				System.out.println(strAnswer.substring(0, strAnswer.length() - 1));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static double[] solve(int n, double[] num) {
		//식이 2차일 경우 근의 공식으로 해결
		if(n == 2) {
			double tmp = Math.sqrt(num[1]*num[1]-4*num[0]*num[2]);
			return new double[] {(-num[1]-tmp)/2/num[0],(-num[1]+tmp)/2/num[0]}; 
		}
		
		double[] answer = new double[n];
		//극점을 찾기위해 미분값의 해 검색
		double[] difArr = solve(n-1, getDiff(num));
		//극점간의 구간에서 함수가 증가인지 감소인지
		boolean[] isPlus = getPlus(difArr, getDiff(num));
		
		for(int i = 0; i <= difArr.length; i++) {
			if(i == 0) {
				answer[i] = getAnswer(-10, difArr[i], num, isPlus[i]);
			} else if(i == difArr.length) {
				answer[i] = getAnswer(difArr[i-1], 10, num, isPlus[i]);
			} else {
				answer[i] = getAnswer(difArr[i-1], difArr[i], num, isPlus[i]);
			}
		}
		
		Arrays.sort(answer);
		return answer;
	}

	//미분된 방정식 계수와 극점값으로 기울기가 양수인지 음수인지 반환
	private static boolean[] getPlus(double[] difArr, double[] diff) {
		boolean[] ret = new boolean[difArr.length+1];
		for(int i = 0; i < ret.length; i++) {
			if(i == 0) {
				double tmp = (-10 + difArr[i])/2;
				ret[i] = isHigh(tmp, diff);
			} else if(i == ret.length - 1) {
				double tmp = (10 + difArr[i-1])/2;
				ret[i] = isHigh(tmp, diff);
			} else {
				double tmp = (difArr[i-1] + difArr[i])/2;
				ret[i] = isHigh(tmp, diff);
			}
		}
		return ret;
	}
	
	//x범위의 중간값으로 방정식의 미분값이 양수이면 해당 구간은 증가, 음수이면 감소
	private static boolean isHigh(double mid, double[] num) {
		double ret = num[num.length-1];
		double nextMid = mid;
		for(int i = num.length-2; i >= 0; i--) {
			ret += num[i] * nextMid;
			nextMid *= mid;
		}
		return ret >= 0;
	}

	private static double getAnswer(double start, double end, double[] num, boolean isPlus) {
		//[start, end]구간에서 해의 이분검색
		double low = start;
		double high = end;
		for(int i = 0; i < 100; i++) {
			double mid = (low + high) / 2;
			//값이 0보다 클때
			if(isHigh(mid, num)) {
				if(isPlus) {
					//증가 구간인 경우
					high = mid;
				} else {
					//감소 구간인 경우
					low = mid;
				}
			} else {
				if(isPlus) {
					//증가 구간인 경우
					low = mid;
				} else {
					//감소 구간인 경우
					high = mid;
				}
			}
		}
		return high;
	}

	//계수들의 미분값 반환
	private static double[] getDiff(double[] num) {
		int nextN = num.length - 1;
		double[] ret = new double[nextN];
		for(int i = 0; i < num.length - 1; i++) {
			ret[i] = nextN*num[i];
			nextN--;
		}
		
		return ret;
	}
}
