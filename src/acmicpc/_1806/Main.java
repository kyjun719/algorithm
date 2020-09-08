package acmicpc._1806;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int n = info[0];
			int s = info[1];
			
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			solve2(arr,n,s);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//fail
	private static void solve1(int[] arr, int n, int s) {
		int[] psum=new int[n+1];
		for(int i = 0; i < n; i++) {
			psum[i+1]=psum[i]+arr[i];
		}
		
		int len=987654321;
		for(int i=0; i<n; i++) {
            int min=Math.min(Math.min(len,n),i);
			for(int j=0; j<=min; j++) {
				if(psum[i]-psum[i-j]>=s) {
					len=len>j?j:len;
				}
			}
		}
		System.out.println(len==987654321?0:len);
	}
	
	private static void solve2(int[] arr, int n, int s) {
		int i=0,j=0,sum=0;
		int len=987654321;
		while(true) {
			if(sum>=s) {
				if(len>(j-i)) {
					len=j-i;
				}
				sum-=arr[i++];
				continue;
			}
			
			if(j==n) {
				break;
			}
			sum+=arr[j++];
		}
		System.out.println(len==987654321?0:len);
	}
}
