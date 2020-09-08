package acmicpc._11577;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	//https://m.blog.naver.com/pasdfq/221221437592
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int n = info[0];
			int k = info[1];
			
			int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] b = new int[n];
			b[0]=a[0];
			for(int i = 1; i < n; i++) {
				b[i] = a[i]^a[i-1];
			}
			System.out.println(Arrays.toString(b));
			
			int cnt=0;
			for(int i = 0; i <=n-k; i++) {
				if(b[i]==1) {
					b[i]^=1;
                    if(i+k<n) {
                         b[i+k]^=1;   
                    }
					cnt++;
				}
				System.out.println(Arrays.toString(b));
			}
			for(int i = n-k+1; i<n; i++) {
				if(b[i]==1) {
					cnt=-1;
					break;
				}
			}
			System.out.println(cnt==-1?"Insomnia":cnt);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
