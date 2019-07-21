package algospot.CANADATRIP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/CANADATRIP
 * @author jun
 * input
2
3 15
500 100 10
504 16 4
510 60 6
2 1234567
8030000 8030000 1
2 2 1

 * output
480
1234563
 */
public class Main {
	static int[] l, m, g;
	static int n, k;
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			for(int tc = 0; tc < c; tc++) {
				int[] tmp = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt).toArray();
				n = tmp[0];
				k = tmp[1];
				
				l = new int[n];
				m = new int[n];
				g = new int[n];
				for(int i = 0; i < n; i++) {
					int[] infoTmp = Arrays.stream(bf.readLine().split(" "))
							.mapToInt(Integer::parseInt).toArray();
					l[i] = infoTmp[0];
					m[i] = infoTmp[1];
					g[i] = infoTmp[2];
				}
				
				System.out.println(getDist());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static int getDist() {
		int low = -1;
		int high = 8030001;
		for(int i = 0; i < 25; i++) {
			int dist = (low + high)/2;
			if(canShow(dist)) {
				high = dist;
			} else {
				low = dist;
			}
		}
		
		return high;
	}

	private static boolean canShow(int dist) {
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			if(dist >= l[i] - m[i]) {
				cnt += (Math.min(dist, l[i]) - (l[i] - m[i]))/g[i] + 1;
			}
		}
		
		return cnt >= k;
	}
}
