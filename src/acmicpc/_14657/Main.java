package acmicpc._14657;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	/*
	 * fail
	 */
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int n = info[0];
			int t = info[1];
			ArrayList<Edge>[] adj = new ArrayList[n];
			for(int i = 0; i < n; i++) {
				adj[i] = new ArrayList<>();
			}
			for(int i = 0; i < n-1; i++) {
				int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				int a = tmp[0]-1, b = tmp[1]-1;
				adj[a].add(new Edge(b,tmp[2]));
				adj[b].add(new Edge(a,tmp[2]));
			}
			

			solve1(adj,n,t);
			solve2(adj,n,t);			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static class Edge {
		int dest, val;
		public Edge(int dest, int val) {
			this.dest = dest;
			this.val = val;
		}
	}
	
	/**
	 * solve with bfs
	 * @param adj adj list
	 * @param n total node num
	 * @param t used hour per day
	 */
	private static void solve1(ArrayList<Edge>[] adj, int n, int t) {
		int[] depth = new int[n];
		int[] time = new int[n];
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		depth[0]=1;
		int maxDepth=0,minTime=987654321;
		int maxDepthNode=0;
		while(!q.isEmpty()) {
			int now = q.poll();
			for(Edge e : adj[now]) {
				if(depth[e.dest]==0) {
					depth[e.dest]=depth[now]+1;
					time[e.dest]=time[now]+e.val;
					q.add(e.dest);

					if(maxDepth==depth[e.dest]) {
						if(minTime>time[e.dest]) {
							maxDepthNode=e.dest;
							minTime=time[e.dest];
						}
					}
					if(maxDepth<depth[e.dest]) {
						maxDepth=depth[e.dest];
						minTime=time[e.dest];
						maxDepthNode=e.dest;
					}
				}
			}
		}
		
		depth = new int[n];
		time = new int[n];
		q.add(maxDepthNode);
		depth[maxDepthNode]=1;
		maxDepth=0;
		minTime=987654321;
		while(!q.isEmpty()) {
			int now = q.poll();
			for(Edge e : adj[now]) {
				if(depth[e.dest]==0) {
					depth[e.dest]=depth[now]+1;
					time[e.dest]=time[now]+e.val;
					q.add(e.dest);

					if(maxDepth==depth[e.dest]) {
						if(minTime>time[e.dest]) {
							maxDepthNode=e.dest;
							minTime=time[e.dest];
						}
					}
					if(maxDepth<depth[e.dest]) {
						maxDepth=depth[e.dest];
						minTime=time[e.dest];
						maxDepthNode=e.dest;
					}
				}
			}
		}
		System.out.println("solve1::"+(int)Math.ceil((double)minTime/t));
	}
	
	/**
	 * solve with dfs
	 * @param adj adj list
	 * @param n total node num
	 * @param t used hour per day
	 */
	private static void solve2(ArrayList<Edge>[] adj, int n, int t) {
		visited = new boolean[n];
		maxDepth=0;
		minTime=987654321;
		visited[0]=true;
		dfs(adj,0,1,0);
		
		visited = new boolean[n];
		maxDepth=0;
		minTime=987654321;
		visited[maxDepthNode]=true;
		dfs(adj,maxDepthNode,1,0);
		System.out.println("solve2::"+(int)Math.ceil((double)minTime/t));
	}
	
	private static int maxDepth, minTime, maxDepthNode;
	private static boolean[] visited;
	private static void dfs(ArrayList<Edge>[] adj, int now, int depth, int time) {
		if(depth==maxDepth) {
			if(minTime>time) {
				minTime=time;
				maxDepthNode=now;
			}
		}
		if(depth>maxDepth) {
			minTime=time;
			maxDepth=depth;
			maxDepthNode=now;
		}
		for(Edge next : adj[now]) {
			if(!visited[next.dest]) {
				visited[next.dest] = true;
				dfs(adj,next.dest,depth+1,time+next.val);
			}
		}
	}
}
