package acmicpc._1963;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		precal();
//		System.out.println(Arrays.toString(sosu));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		visited=new boolean[10001];
		int tc=Integer.parseInt(br.readLine());
		
		for(int t = 0; t< tc; t++) {
			int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			int ans=bfs(info[0],info[1]);
			System.out.println(ans==987654321?"impossible":ans);
		}
	}
	
	private static int bfs(int a, int b) {
		Arrays.fill(visited, false);
		Queue<Integer[]> q = new LinkedList<>();
		q.add(new Integer[]{a,0});
		visited[a]=true;
		while(!q.isEmpty()) {
			Integer[] now = q.poll();
//			System.out.println(Arrays.toString(now));
			if(now[0]==b) {
				return now[1];
			}
			
			int cnt=now[1];
			int n = now[0];
			for(int i = 1; i <= 9; i++) {
				int tmp=n%1000+i*1000;
				if(tmp==n) {
					continue;
				}
				if(sosu[tmp] && !visited[tmp]) {
					visited[tmp]=true;
					q.add(new Integer[] {tmp,cnt+1});
				}
			}
			
			for(int i = 0; i <= 9; i++) {
				int tmp=n%100+i*100+(n/1000)*1000;
				if(tmp==n) {
					continue;
				}
				if(sosu[tmp] && !visited[tmp]) {
					q.add(new Integer[] {tmp,cnt+1});
				}
			}
			
			for(int i = 0; i <= 9; i++) {
				int tmp=n%10+i*10+(n/100)*100;
				if(tmp==n) {
					continue;
				}
				if(sosu[tmp] && !visited[tmp]) {
					q.add(new Integer[] {tmp,cnt+1});
				}
			}
			
			for(int i = 0; i <= 9; i++) {
				int tmp=n-n%10+i;
				if(tmp==n) {
					continue;
				}
				if(sosu[tmp] && !visited[tmp]) {
					q.add(new Integer[] {tmp,cnt+1});
				}
			}
		}
		return 987654321;
	}
	
	private static int ans=987654321;
	private static boolean[] visited;
	private static boolean[] sosu=new boolean[10000];
	private static void precal() {
		Arrays.fill(sosu, true);
		sosu[1]=false;
		for(int i = 2; i <= (int)Math.sqrt(10000); i++) {
			int tmp = 2*i;
			while(tmp<10000) {
				sosu[tmp]=false;
				tmp+=i;
			}
		}
	}

}
