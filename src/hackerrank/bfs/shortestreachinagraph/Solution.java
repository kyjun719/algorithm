package hackerrank.bfs.shortestreachinagraph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 1; t <= tc; t++) {
				String[] nm = br.readLine().split(" ");
				int n = Integer.parseInt(nm[0]);
				int m = Integer.parseInt(nm[1]);
				ArrayList<Integer>[] adj = new ArrayList[n];
				for(int i = 0; i < n; i++) {
					adj[i] = new ArrayList<>();
				}
				
				for(int i = 0; i < m; i++) {
					String[] tmp = br.readLine().split(" ");
					int a = Integer.parseInt(tmp[0])-1;
					int b = Integer.parseInt(tmp[1])-1;
					adj[a].add(b);
					adj[b].add(a);
				}
				int s = Integer.parseInt(br.readLine())-1;
				int[] ret = bfs(s, adj);
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < ret.length; i++) {
					if(ret[i] != 0) {
						sb.append(ret[i]);
						if(i < ret.length-1) {
							sb.append(" ");
						}
					}
				}
				
				System.out.println(sb.toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static int[] bfs(int s, ArrayList<Integer>[] adj) {
		int[] dist = new int[adj.length];
		Arrays.fill(dist, -1);
		boolean[] visited = new boolean[adj.length];
		
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		dist[s] = 0;
		visited[s] = true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			for(int next : adj[now]) {
				if(!visited[next]) {
					visited[next] = true;
					dist[next] = dist[now]+6;
					q.add(next);
				}
			}
		}
		// TODO Auto-generated method stub
		return dist;
	}
}
