package acmicpc._1316;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args )throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int ret = 0;
		for(int i = 0; i < n; i++) {
			String tmp = br.readLine();
			
			boolean isSuccess=true;
			int[] visited = new int[26];
			for(int j = 0; j < tmp.length(); j++) {
				char c = tmp.charAt(j);
				int idx = c-'a';
				if(visited[idx]==0) {
					visited[idx]=1;
				} else if(visited[idx]==1) {
					if(tmp.charAt(j-1) != c) {
						isSuccess = false;
						break;
					}
				}
			}
			if(isSuccess) {
				ret++;
			}
		}
		System.out.println(ret);
		
	}
}
