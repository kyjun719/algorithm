package algospot.LAN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static double[][] adj;
	private static int n;

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t< tc; t++) {
				int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				n = tmp[0];
				int m = tmp[1];

				adj = new double[n][n];
				for(int i = 0; i < n; i++) {
					Arrays.fill(adj[i], -1);
				}

				int[] tmpx = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				int[] tmpy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				for(int s = 0; s < n; s++) {
					for(int e = s+1; e < n; e++) {
						double len = calcLen(tmpx[s], tmpy[s], tmpx[e], tmpy[e]);
						adj[s][e] = len;
						adj[e][s] = len;
					}
				}

				for(int i = 0; i < m; i++) {
					int[] mInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
					adj[mInfo[0]][mInfo[1]] = 0;
					adj[mInfo[1]][mInfo[0]] = 0;
				}

//				System.out.println(kruskal());
				System.out.println(prim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static double kruskal() {
		double ret = 0;

		List<Edge> edges = new ArrayList<>();
		for(int s = 0; s < n; s++) {
			for(int e = 0; e < n; e++) {
				if(adj[s][e] >= 0) {
					Edge edge = new Edge();
					edge.s = s;
					edge.e = e;
					edge.w = adj[s][e];
					edges.add(edge);
				}
			}
		}

		edges.sort(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return Double.compare(o1.w, o2.w);
			}
		});

		DisjointSet set = new DisjointSet(n);
		for(Edge edge : edges) {
			if(set.find(edge.s) == set.find(edge.e)) {
				continue;
			}

			set.merge(edge.s, edge.e);
			ret += edge.w;
		}
		return ret;
	}

	private static double prim() {
		double ret = 0;

		boolean[] added = new boolean[n];
		double[] minWeight = new double[n];
		int[] parent = new int[n];
		Arrays.fill(minWeight, 987654321);
		Arrays.fill(parent, -1);

		minWeight[0] = 0;
		parent[0] = 0;
		for(int i = 0; i < n; i++) {
			int u = -1;
			for(int v = 0; v < n; v++) {
				if(!added[v] && (u==-1 || minWeight[u]>minWeight[v])) {
					u = v;
				}
			}

			if(u == -1) {
				break;
			}

			ret += minWeight[u];
			added[u] = true;
			for(int e = 0 ; e < n; e++) {
				if(adj[u][e] != -1) {
					if(!added[e] && minWeight[e] > adj[u][e]) {
						parent[e] = u;
						minWeight[e] = adj[u][e];
					}
				}
			}
		}

		return ret;
	}

	private static double calcLen(int sx, int sy, int ex, int ey) {
		double x = Math.abs(sx-ex);
		double y = Math.abs(sy-ey);
		return Math.sqrt(x*x + y*y);
	}

	static class Edge {
		int s, e;
		double w;
	}

	static class DisjointSet {
		//i번째의 부모노드 인덱스 배열
		int[] parent;
		//i번째의 노드가 루트인경우 해당 트리의 높이
		int[] rank;

		DisjointSet(int n) {
			parent = new int[n];
			rank = new int[n];
			Arrays.fill(rank, 1);
			for(int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		//u가 속한 트리의 루트번호 반환
		public int find(int u) {
			if(u == parent[u]) {
				return u;
			}
			//parent[u]를 u가 속하는 트리의 루트로 변경
			parent[u] = find(parent[u]);
			return parent[u];
		}

		//u가 속한 트리와 v가 속한 트리를 합침
		public void merge(int u, int v) {
			u = find(u);
			v = find(v);
			//u와 v가 같은 트리에 있는 경우 걸러냄
			if(u == v) {
				return;
			}
			//u의 랭크가 v의 랭크보다 큰 경우 두 수를 바꿈
			if(rank[u] > rank[v]) {
				int tmp = u;
				u = v;
				v = tmp;
			}
			//rank[v]가 항상 rank[u]이상이므로 u를 v의 자식으로 넣음
			parent[u] = v;
			//두 트리의 높이가 같은경우에만 결과 트리의 높이를 1 늘림
			if(rank[u] == rank[v]) {
				rank[v]++;
			}
		}
	}
}
