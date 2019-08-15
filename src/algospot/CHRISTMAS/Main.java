package algospot.CHRISTMAS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/CHRISTMAS
 * @author jun
 * input
1
6 4
1 2 3 4 5 6

 * output
3 1
 */
public class Main {
	static int n,k;
	static int[] d;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int tc = Integer.parseInt(br.readLine());
			
			for(int t = 0; t< tc; t++) {
				int[] info = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				n = info[0];
				k = info[1];
				d = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				precal();
				
				System.out.println(countOrder() + " " + maxOrder());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//psum(H~T) = 0 -> psum(H-1) = psum(T) -> psum값이 동일한 부분확인
	private static int countOrder() {
		int MOD = 20091101;
		long[] count = new long[k];
		for(int i = 0; i <= n; i++) {
			count[psum[i]]++;
		}
		
		long ret = 0;
		for(int i = 0; i < k; i++) {
			if(count[i] >= 2) {
				ret = (ret+((count[i]*(count[i]-1))/2))%MOD;
			}
		}
		
		return (int)ret;
	}
	
	private static int maxOrder() {
		int[] ret = new int[n+1];
		int[] prevLoc = new int[k];
		Arrays.fill(prevLoc, -1);
		
		for(int i = 0; i <= n; i++) {
			//i번째 상자를 사지 않은경우 최대값은 ret[i-1]
			if(i > 0) {
				ret[i] = ret[i-1];
			} else {
				ret[i] = 0;
			}
			
			//i번째 상자를 산 경우 최대값은 psum[i]와 같은 psum값을 가진 상자의 최대값+1
			if(prevLoc[psum[i]] != -1) {
				ret[i] = Math.max(ret[i], ret[prevLoc[psum[i]]]+1);
			}
			prevLoc[psum[i]] = i;
		}
		return ret[n];
	}

	static int[] psum;
	private static void precal() {
		psum = new int[n+1];
		psum[0] = 0;
		for(int i = 1; i <= n; i++) {
			psum[i] = (psum[i-1]+d[i-1])%k;
		}
	}
}
