package algospot.PI;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author jun
 * input
5
12341234
11111222
12122222
22222222
12673939

 * output
4
2
5
2
14
 */
public class Main {
	static int MAX = 987654321;
	static int n;
	static int[] arr, val;
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int tc = Integer.parseInt(br.readLine());
			arr = new int[10001];
			val = new int[10001];
			
			for(int t = 0; t < tc; t++) {
				String num = br.readLine();
				num = num.replace(" ", "").replace("\n", "");
				n = num.length();
				Arrays.fill(val, -1);
				
				for(int i = 0; i < n; i++) {
					arr[i] = num.charAt(i)-48;
				}
				
				int ret = solve(arr, 0);
				System.out.println(ret);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static int solve(int[] arr, int idx) {
		if(idx == n) {
			return 0;
		}
		
		if(val[idx] != -1) {
			return val[idx];
		}
		
		int ret = 987654321;
		for(int i = 3; i <= 5; i++) {			
			if(i+idx <= n) {
				int calVal = calculateVal(Arrays.copyOfRange(arr, idx, idx+i))+
						solve(arr, idx+i);
				ret = ret > calVal ? calVal : ret;
			}
		}
		val[idx] = ret;
		return ret;
	}
	
	private static int calculateVal(int[] arr) {
		// TODO Auto-generated method stub
		int size = arr.length;
		
		boolean isSame = true;
		for(int i = 1; i < size; i++) {
			if(arr[i] != arr[i-1]) {
				isSame = false;
				break;
			}
		}
		
		if(isSame) {
			return 1;
		}
		
		boolean isProgress = true;
		boolean isTemp = true;
		for(int i = 1; i < size; i++) {
			if(arr[i] - arr[i-1] != arr[1] - arr[0]) {
				isProgress = false;
			}
			
			if(arr[i] != arr[i%2]) {
				isTemp = false;
			}
		}
		
		if(isProgress) {
			if(Math.abs(arr[1]-arr[0]) == 1) {
				return 2;
			}
		}
		
		if(isTemp) {
			return 4;
		}
		
		if(isProgress) {
			return 5;
		}
		
		return 10;
	}
}
