package algospot.QUANTIZE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/QUANTIZE
 * @author jun
 * input
2
10 3
3 3 3 1 2 3 2 2 2 1
9 3
1 744 755 4 897 902 890 6 777

 * output
0
651
 */
public class Main {
	private static int[] sum = new int[101];
	private static int[] sqSum = new int[101];
	private static int n;
	private static int[][] cache = new int[101][11];
	private static int INF = 987654321;
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int c = Integer.parseInt(br.readLine());
			for(int i = 0; i < c; i++) {
				String[] tmp = br.readLine().split(" ");
				n = Integer.parseInt(tmp[0]);
				int parts = Integer.parseInt(tmp[1]);
				String[] arr = br.readLine().split(" ");
				int[] A = new int[n];
				for(int j = 0; j < n; j++) {
					A[j] = Integer.parseInt(arr[j]);
					Arrays.fill(cache[j], -1);
				}
				
				Arrays.sort(A);
				presum(A);
				System.out.println(quantize(0, parts));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//에러 계산시 필요한 값들 미리 계산
	public static void presum(int[] A) {
		sum[0] = A[0];
		sqSum[0] = A[0] * A[0];
		for(int i = 1; i < A.length; i++ ) {
			sum[i] = sum[i-1] + A[i];
			sqSum[i] = sqSum[i-1] + A[i] * A[i];
		}
	}
	
	//sum(a[i]-m)^2=sum(a[i]^2)-2m*sum(a[i])+(b+a-1)*m^2
	public static int error(int a, int b) {
		int SqSum = sqSum[b] - (a == 0? 0 : sqSum[a-1]);
		int Sum = sum[b] - (a == 0? 0 : sum[a-1]);
		int m = Math.round((float)Sum/(b-a+1));
		int ret = SqSum - 2*m*Sum + m*m*(b-a+1);
		return ret;
	}
	
	public static int quantize(int from, int parts) {
		//다 묶은 경우
		if(from == n) {
			return 0;
		}
		
		//묶음을 다 쓴 경우
		if(parts == 0) {
			return INF;
		}
			
		if(cache[from][parts] != -1) {
			return cache[from][parts];
		}
		
		//에러 최소값 탐색
		int ret = INF;
		for(int pt = 1; from+pt <= n; pt++) {
			int val = error(from, from+pt-1) + quantize(from+pt, parts-1);
			ret = Math.min(ret, val);
		}
		
		cache[from][parts] = ret;
		return ret;
	}
}
