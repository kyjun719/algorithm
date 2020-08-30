package acmicpc._1697;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static ArrayList<Integer>[] list;
	
	public static void main(String[] args) throws Exception{
		//solveWithBFS();
		
		solveWithDSF();
	}

	private static void solveWithBFS() throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int vex=Integer.parseInt(st.nextToken());
		list=new ArrayList[n+1];
		arr=new int[n+1];
		for(int i=1; i<=n; i++) {
			list[i]=new ArrayList<Integer>();
		}
		for(int i=0; i<vex; i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int a=Integer.parseInt(st.nextToken());
			list[s].add(a);
		}

		for(int i=1; i<=n; i++) {
			bfs(n, i);
			/*
			boolean[] visited=new boolean[n+1];
			dfs(i,visited);
			 */
		}
		
		int max = 0;
		for (int i = 1; i <= n; i++) {
	      max = Math.max(max, arr[i]);
	    }

		StringBuilder sb = new StringBuilder();
	    for (int i = 1; i <= n; i++) {
	      if (max == arr[i]) {
	        sb.append(i + " ");
	      }
	    }
		System.out.println(sb.toString());
	}

	private static void bfs(int n, int s) {
		boolean[] visited=new boolean[n+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		visited[s] = true;
		while(!q.isEmpty()) {
			int now = q.poll();
			for(int next : list[now]) {
				if(!visited[next]) {
					visited[next] = true;
					arr[next]++;
					q.add(next);
				}
			}
		}
	}
	
    //TODO output error occur
    // based on https://www.acmicpc.net/source/19278788
	private static ArrayList<Integer>[] adj, inv;
	private static boolean[] visited;
	private static int sccCounter;
	private static void solveWithDSF() throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int vex=Integer.parseInt(st.nextToken());
		adj = new ArrayList[n];
		inv = new ArrayList[n];
		for(int i=0; i<n; i++) {
			adj[i]=new ArrayList<Integer>();
			inv[i]=new ArrayList<Integer>();
		}
		
		for(int i = 0; i < vex; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			adj[b-1].add(a-1);
			inv[a-1].add(b-1);
		}
		
		Stack<Integer> visitedOrder = new Stack<>();
		visited = new boolean[n];
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				visitedOrder.addAll(dfs(i));
			}
		}
		
		//check inv
		visited = new boolean[n];
		int[] sccSize = new int[n];
		int[] scc = new int[n];
		sccCounter = 0;
		while(!visitedOrder.isEmpty()) {
			int now = visitedOrder.pop();
			if(!visited[now]) {
				int size = dfs_inv(now, scc, 0);
				sccSize[sccCounter++] = size;
			}
		}
		
		Set<Integer>[] sccAdj = new HashSet[sccCounter];
		Set<Integer>[] sccInv = new HashSet[sccCounter];
		Set<Integer>[] d = new HashSet[sccCounter];
		for(int i = 0; i < sccCounter; i++) {
			sccAdj[i] = new HashSet<>();
			sccInv[i] = new HashSet<>();
			d[i] = new HashSet<>();
		}
		
		for(int i = 0; i < n; i++) {
			for(int next : adj[i]) {
				sccAdj[scc[i]].add(scc[next]);
				sccInv[scc[next]].add(scc[i]);
			}
		}
		
		int[] sccOut = new int[sccCounter];
		Queue<Integer> q = new LinkedList<>();
		for(int i = 0; i < sccCounter; i++) {
			sccOut[i] = sccAdj[i].size(); 
			if(sccOut[i] == 0) {
				q.add(i);
			}
		}
	
		while(!q.isEmpty()) {
			int now = q.poll();
			d[now].add(now);
			for(int next : sccInv[now]) {
				d[next].addAll(d[now]);
				sccOut[next]--;
				if(sccOut[next] == 0) {
					q.add(next);
				}
			}
		}

		int[] dSum = new int[sccCounter];
		for(int i = 0; i < sccCounter; i++) {
			for(int j : d[i]) {
				dSum[i] += sccSize[j];
			}
		}
		
		int max = 0;
		for(int i = 0; i < sccCounter; i++) {
			max = Math.max(max, dSum[i]);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int  i = 0; i < n; i++) {
			if(dSum[scc[i]] == max) {
				sb.append(i+1);
				sb.append(" ");
			}
		}
		System.out.println(sb.toString());
	}

	private static Stack<Integer> dfs(int s) {
		Stack<Integer> order = new Stack<>();
		visited[s]=true;
		for(int a:adj[s]) {
			if(!visited[a]) {
				order.addAll(dfs(a));
			}
		}
		order.add(s);
		return order;
	}
	
	private static int dfs_inv(int now, int[] scc, int size) {
		visited[now] = true;
		scc[now] = sccCounter;
		size += 1;
		for(int next : inv[now]) {
			if(!visited[next]) {
				dfs_inv(next,scc,size);
			}
		}
		return size;
	}
}
