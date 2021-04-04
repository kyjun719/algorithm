package algospot.TIMETRIP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static class Edge {
		int dest;
		int weight;
		public Edge(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
	}
	static int V;
	static int M = 98765432;
	static int INF = 987654321;
	static ArrayList<Edge>[] forwardAdj, backwardAdj;
	static boolean[][] reachable;
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				V = tmp[0];
				int w = tmp[1];
				
				forwardAdj = new ArrayList[V];
				backwardAdj = new ArrayList[V];
				reachable = new boolean[V][V];
				for(int i = 0; i < V; i++) {
					forwardAdj[i] = new ArrayList<>();
					backwardAdj[i] = new ArrayList<>();
				}
				
				for(int i = 0; i < w; i++) {
					tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
					int a = tmp[0];
					int b = tmp[1];
					int val = tmp[2];
					forwardAdj[a].add(new Edge(b, val));
					backwardAdj[a].add(new Edge(b, -val));
					reachable[a][b] = true;
				}
				
				for(int i = 0; i < V; i++) {
					for(int j = 0; j < V; j++) {
						for(int k = 0; k < V; k++) {
							reachable[j][k] |= (reachable[j][i] && reachable[i][k]);
						}
					}
				}

				String forwardResult = parseResult(solve(true), 1);
				String backwardResult = parseResult(solve(false), -1);
				if(forwardResult == null || backwardResult == null) {
					System.out.println("UNREACHABLE");
				} else {
					System.out.println(forwardResult+" "+backwardResult);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String parseResult(int distVal, int val) {
		if(distVal == -INF) {
			return "INFINITY";
		} else if(distVal >= INF-M) {
			return null;
		} else {
			return String.valueOf(distVal*val);
		}
	}
	
	private static int solve(boolean isForward) {
		int[] upper = new int[V];
		Arrays.fill(upper, INF);
		if(isForward) {
			return bellmanFord(upper, forwardAdj, 0)[1];
		}
		return bellmanFord(upper, backwardAdj, 0)[1];
	}

	private static int[] bellmanFord(int[] upper, ArrayList<Edge>[] adj, int src) {
		upper[src] = 0;
		boolean cycle = false;
		
		for(int i = 0; i < V; i++) {
			for(int here = 0; here < V; here++) {
				for(Edge e : adj[here]) {
					int there = e.dest;
					int cost = e.weight;
					if(upper[there] > upper[here]+cost) {
						upper[there] = upper[here]+cost;
						
						//if(i == V-1 && reachable[there][1] && reachable[here][1]) {
						if(reachable[there][1] && reachable[here][1]) {
							cycle = true;
						}
					}
				}
			}
		}
		
		if(cycle) {
			upper[1] = -INF;
		}
		
		
		return upper;
	}
}