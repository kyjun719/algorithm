package algospot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/QUANTIZE
 * @author jun
 * sample input
2
10 3
3 3 3 1 2 3 2 2 2 1
9 3
1 744 755 4 897 902 890 6 777

  * sample output
0
651
 */
public class QUANTIZE {
	public int[] sum = new int[101];
	public int[] sqSum = new int[101];
	public int n;
	public int[][] cache = new int[101][11];
	public int INF = 987654321;
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int c = Integer.parseInt(br.readLine());
			for(int i = 0; i < c; i++) {
				QUANTIZE quantize = new QUANTIZE();
				String[] tmp = br.readLine().split(" ");
				quantize.n = Integer.parseInt(tmp[0]);
				int parts = Integer.parseInt(tmp[1]);
				String[] arr = br.readLine().split(" ");
				int[] A = new int[quantize.n];
				for(int j = 0; j < quantize.n; j++) {
					A[j] = Integer.parseInt(arr[j]);
					Arrays.fill(quantize.cache[j], -1);
				}
				
				Arrays.sort(A);
				quantize.presum(A);
				System.out.println(quantize.quantize(0, parts));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		//1 4 6 744 755 777 890 897 902
		//4,759,896
		//3,0,2,15,4,18,6,1,6
		//9,0,4,225,16,324,36,1,36
	}
	
	public void presum(int[] A) {
		sum[0] = A[0];
		sqSum[0] = A[0] * A[0];
		for(int i = 1; i < A.length; i++ ) {
			sum[i] = sum[i-1] + A[i];
			sqSum[i] = sqSum[i-1] + A[i] * A[i];
		}
	}
	
	public int error(int a, int b) {
		int SqSum = sqSum[b] - (a == 0? 0 : sqSum[a-1]);
		int Sum = sum[b] - (a == 0? 0 : sum[a-1]);
		int m = Math.round((float)Sum/(b-a+1));
		int ret = SqSum - 2*m*Sum + m*m*(b-a+1);
		return ret;
	}
	
	public int quantize(int from, int parts) {
		if(from == n) {
			return 0;
		}
		
		if(parts == 0) {
			return INF;
		}
			
		if(cache[from][parts] != -1) {
			return cache[from][parts];
		}
		
		int ret = INF;
		for(int pt = 1; from+pt <= n; pt++) {
			int val = error(from, from+pt-1) + quantize(from+pt, parts-1);
			ret = Math.min(ret, val);
		}
		
		cache[from][parts] = ret;
		return ret;
	}
}
