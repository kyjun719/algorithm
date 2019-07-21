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
		if(n == 2) {
			double tmp = Math.sqrt(num[1]*num[1]-4*num[0]*num[2]);
			return new double[] {(-num[1]-tmp)/2/num[0],(-num[1]+tmp)/2/num[0]}; 
		}
		
		double[] answer = new double[n];
		double[] difArr = solve(n-1, getDiff(num));
		boolean[] isPlus = getPlus(difArr, getDiff(num));
		//System.out.println(Arrays.toString(difArr) + "," + Arrays.toString(isPlus));
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

	private static boolean[] getPlus(double[] difArr, double[] diff) {
		// TODO Auto-generated method stub
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

	private static double getAnswer(double start, double end, double[] num, boolean isPlus) {
		// TODO Auto-generated method stub
		//System.out.println(start+","+end+">>"+Arrays.toString(num)+","+isPlus);
		double low = start;
		double high = end;
		for(int i = 0; i < 100; i++) {
			double mid = (low + high) / 2;
			//System.out.println(mid + ">>" + isHigh(mid, num));
			if(isHigh(mid, num)) {
				if(isPlus) {
					high = mid;
				} else {
					low = mid;
				}
			} else {
				if(isPlus) {
					low = mid;
				} else {
					high = mid;
				}
			}
		}
		return high;
	}

	private static boolean isHigh(double mid, double[] num) {
		// TODO Auto-generated method stub
		double ret = num[num.length-1];
		double nextMid = mid;
		for(int i = num.length-2; i >= 0; i--) {
			ret += num[i] * nextMid;
			nextMid *= mid;
		}
		return ret >= 0;
	}

	private static double[] getDiff(double[] num) {
		// TODO Auto-generated method stub
		int nextN = num.length - 1;
		double[] ret = new double[nextN];
		for(int i = 0; i < num.length - 1; i++) {
			ret[i] = nextN*num[i];
			nextN--;
		}
		
		return ret;
	}
}
