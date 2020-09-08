package acmicpc._5014;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int f = info[0];
			int s = info[1];
			int g = info[2];
			int u = info[3];
			int d = info[4];
			
			int[] visited = new int[f+1];
			Arrays.fill(visited, -1);
			Queue<Integer> q = new LinkedList<>();
			q.add(s);
			visited[s] = 0;
			while(!q.isEmpty()) {
				int now = q.poll();
				if(now == g) {
					break;
				}
				
				if(now+u<=f && visited[now+u]==-1) {
					visited[now+u]=visited[now]+1;
					q.add(now+u);
				}
				
				if(now-d>0 && visited[now-d]==-1) {
					visited[now-d]=visited[now]+1;
					q.add(now-d);
				}
			}
			System.out.println(visited[g]==-1?"use the stairs":visited[g]);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
