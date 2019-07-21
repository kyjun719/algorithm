package algospot.WITHDRAWAL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/WITHDRAWAL
 * @author jun
 * input
3
3 2
1 4 6 10 10 17
4 2
4 8 9 12 3 10 2 5
10 5
70 180 192 192 1 20 10 200 6 102 60 1000 4 9 1 12 8 127 100 700 

 * output
0.5000000000 
0.3333333333 
0.0563991323
 */
public class Main {
	static int[] r, c;
	static int n,k;
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		try {
			int tc = Integer.parseInt(bf.readLine());
			
			for(int t = 0; t < tc; t++) {
				int[] tmp1 = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				n = tmp1[0];
				k = tmp1[1];
				
				r = new int[n];
				c = new int[n];
				
				String[] tmp2 = bf.readLine().split(" ");
				for(int i = 0; i < n; i++) {
					r[i] = Integer.parseInt(tmp2[i*2]);
					c[i] = Integer.parseInt(tmp2[i*2 + 1]);
				}
				
				System.out.println(getRank());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static double getRank() {
		double min = 0;
		double max = 1;
		
		for(int i = 0; i <100; i++) {
			double avg = (min + max) / 2;
			if(isDrawable(avg)) {
				max = avg;
			} else {
				min = avg;
			}
		}
		
		return max;
	}

	//sum(r)/sum(c) <= avg -> 0 <= avg*sum(c) - sum(r)
	private static boolean isDrawable(double avg) {
		double[] cal = new double[n];
		
		for(int i = 0; i < n; i++) {
			cal[i] = avg*c[i] - r[i];
		}
		
		Arrays.sort(cal);
		
		double sum = 0;
		for(int i = n-1; i >= n-k; i--) {
			sum += cal[i];
		}
		
		return sum >= 0;
	}
}
