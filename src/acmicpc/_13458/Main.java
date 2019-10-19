package acmicpc._13458;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(br.readLine());
			int[] arr = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
			int[] cnt = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
			long answer = 0;
			for(int i = 0; i < n; i++) {
				answer++;
				if(arr[i]-cnt[0] > 0) {
					answer += Math.ceil(((double)(arr[i]-cnt[0]))/cnt[1]);
				}
			}
			System.out.println(answer);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
