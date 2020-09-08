package acmicpc._9251;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String a = br.readLine();
			String b = br.readLine();
			
			int an=a.length(), bn=b.length();
			int[][] arr = new int[an+1][bn+1];
			for(int i = 1; i <=an; i++) {
				for(int j = 1; j <= bn; j++) {
					if(a.charAt(i-1)==b.charAt(j-1)) {
						arr[i][j]=arr[i-1][j-1]+1;
					} else {
						arr[i][j]=Math.max(arr[i][j-1], arr[i-1][j]);
					}
				}
			}
			System.out.println(arr[an][bn]);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
