package acmicpc._9252;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine().trim();
		String b = br.readLine().trim();
		int an = a.length();
		int bn = b.length();
		int[][] arr = new int[an+1][bn+1];

		for(int i = 1; i <= an; i++) {
			for(int j = 1; j <= bn; j++) {
				if(a.charAt(i-1) == b.charAt(j-1)) {
					arr[i][j] = arr[i-1][j-1]+1;
				} else {
					arr[i][j] = Math.max(arr[i][j-1], arr[i-1][j]);
				}
			}
		}
//		for(int i = 0; i <= n; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		
		int cnt=arr[an][bn];		
		String ret = "";
		for(int i = an; i > 0; i--) {
			for(int j = bn; j > 0; j--) {
				if((cnt==arr[i][j]) && (arr[i-1][j-1]==cnt-1) && (arr[i-1][j]==cnt-1) && (arr[i][j-1]==cnt-1)) {
//					System.out.println(i+","+j);
					ret = a.charAt(i-1)+ret;
//					System.out.println(ret);
					cnt--;
					break;
				}
			}
		}
		
		System.out.println(arr[an][bn]);
		System.out.println(ret);
	}
}
