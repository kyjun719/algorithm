package codejam._2020.qualification.vestigium;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				int n = Integer.parseInt(br.readLine());
				int[][] rowCnt = new int[n][101];
				int[][] colCnt = new int[n][101];
				int diagonal = 0;
				
				for(int i = 0; i < n; i++) {
					int[] arr = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
					diagonal += arr[i];
					
					for(int j = 0; j < n; j++) {
						rowCnt[i][arr[j]]++;
						colCnt[j][arr[j]]++;
					}
				}
				
				int r = 0;
				int c = 0;
				for(int i = 0; i < n; i++) {
					for(int j = 0; j < 101; j++) {
						if(rowCnt[i][j] > 1) {
							r++;
							break;
						}
					}
					for(int j = 0; j < 101; j++) {
					    if(colCnt[i][j] > 1) {
							c++;
							break;
						}
					}
				}
				System.out.println("Case #"+(t+1)+": "+diagonal+" "+r+" "+c);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

