package acmicpc._2275;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int n = tmp[0];
		int h = tmp[1];
		ArrayList<Edge>[] adj = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		int root=-1;
		int[] weight = new int[n];
		for(int i = 0; i < n; i++) {
			tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if(tmp[0]==0) {
				root=i;
			} else {
				adj[tmp[0]-1].add(new Edge(i,tmp[1]));
				weight[i]=tmp[1];
			}
		}
		
		int[] height = new int[n];
		Arrays.fill(height, -1);
		Queue<Integer> q = new LinkedList<>();
		q.add(root);
		height[root]=0;
		int ret = 0;
		while(!q.isEmpty()) {
			int now = q.poll();
			for(Edge e : adj[now]) {
				if(height[e.to]==-1) {
					if(height[now]+1 > h) {
						if(weight[now]>weight[e.to]) {
							//cut e
							ret+=e.w;
							height[e.to]=0;
							q.add(e.to);
						} else {
							//cut now
							ret+=weight[now];
							height[now]=0;
							q.add(now);
						}
						
					} else {
						height[e.to]=height[now]+1;
						q.add(e.to);
					}
				}
			}
		}
		System.out.println(Arrays.toString(height));
		System.out.println(Arrays.toString(weight));
		
		System.out.println(ret);
	}
	
	private static class Edge {
		int to,w;
		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}
}
