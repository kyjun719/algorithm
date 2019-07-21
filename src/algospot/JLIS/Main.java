package algospot.JLIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

public class Main {
	private static int m, n;
	private static int[] a, b;
	private static long[][] val;
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int c;
		try {
			c = Integer.parseInt(reader.readLine());
			for(int i = 0; i < c; i++) {
				/*
				 * n, m = [int(tmp) for tmp in sys.stdin.readline().split(' ')]
        a = [int(tmp) for tmp in sys.stdin.readline().split(' ')]
        b = [int(tmp) for tmp in sys.stdin.readline().split(' ')]
        val = [[-1 for _ in range(m + 1)] for _ in range(n + 1)]
        # for fully search, start pos is -1
        # and start from -1, result = result - 2
        print(jlis(-1, -1) - 2)
				 */
				String tmp = reader.readLine();
				n = Integer.parseInt(tmp.split(" ")[0]);
				m = Integer.parseInt(tmp.split(" ")[1]);
				a = new int[n];
				b = new int[m];
				val = new long[n + 1][m + 1];
				
				String[] a_tmp = reader.readLine().split(" ");
				for(int j = 0; j < a_tmp.length; j++) {
					a[j] = Integer.parseInt(a_tmp[j]);
					Arrays.fill(val[j], -1);
				}
				Arrays.fill(val[n], -1);
				
				String[] b_tmp = reader.readLine().split(" ");
				for(int j = 0; j < b_tmp.length; j++) {
					b[j] = Integer.parseInt(b_tmp[j]);
				}
				
				System.out.println(jlis(-1, -1) - 2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static long jlis(int a_pos, int b_pos) {
		if(val[a_pos + 1][b_pos + 1] != -1) {
			return val[a_pos + 1][b_pos + 1];
		}			
		long a_max = a_pos == -1 ? Long.MIN_VALUE : a[a_pos];
		long b_max = b_pos == -1 ? Long.MIN_VALUE : b[b_pos];

		long max_val = Math.max(a_max, b_max);

		long ret = 2;
		for(int i = a_pos+1; i < n; i++) {
			if(max_val < a[i]) {
				ret = Math.max(ret, jlis(i, b_pos) + 1);
			}
		}
		
		for(int i = b_pos+1; i < m; i++) {
			if(max_val < b[i]) {
				ret = Math.max(ret, jlis(a_pos, i) + 1);
			}
		}
		
		val[a_pos + 1][b_pos + 1] = ret;
		return ret;
	}
}