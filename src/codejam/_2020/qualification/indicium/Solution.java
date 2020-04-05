package codejam._2020.qualification.indicium;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 1; t <= tc; t++) {
				int[] tmp = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				n = tmp[0];
				k = tmp[1];
				arr = new int[n][n];
				solve(new int[n][n], 0);
				
				if(arr[0][0] == 0) {
					System.out.println("Case #"+t+": IMPOSSIBLE");
				} else {
					System.out.println("Case #"+t+": POSSIBLE");
					StringBuilder sb = new StringBuilder();
					for(int i = 0; i < n; i++) {
						sb.append(Arrays.toString(arr[i]).replace("[", "").replace("]", "").replace(",", "")+"\n");
					}
					System.out.print(sb.toString());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	static int[][] arr;
	static int n, k;
	private static void solve(int[][] val, int pos) {
		if(arr[0][0] != 0) {
			return;
		}
		
		if(pos == (n*n)) {
			checkArr(val);
			return;
		}
		int y = pos/n;
		int x = pos%n;
		
		for(int num = 0; num < n; num++) {
			boolean isUsed = false;
			for(int j = 0; j < n; j++) {
				if((val[y][j] == (num+1)) || (val[j][x] == (num+1))) {
					isUsed = true;
					break;
				}
			}
			if(isUsed) {
				continue;
			}
			val[y][x] = (num+1);
			solve(val, pos+1);
			val[y][x] = 0;
		}
	}
	
	private static void checkArr(int[][] val) {
		//showMetrix(val);
		
		int ret = 0;
		for(int i = 0; i < n; i++) {
			ret += val[i][i];
		}
		if(ret == k) {
			for(int i = 0; i < n; i++) {
				arr[i] = Arrays.copyOf(val[i], n);
			}
		}
	}
	
	private static void showMetrix(int[][] arr) {
		System.out.println("==========================");
		for(int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		System.out.println("==========================");
	}
}
