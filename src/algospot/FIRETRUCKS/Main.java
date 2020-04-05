package algospot.FIRETRUCKS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	//간선 정보
	static class Edge {
		//목적지 간선 인덱스
		int dest;
		//간선 가중치
		int weight;
		public Edge(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
	}
	
	//정점정보
	static class Vertex {
		//현재 정점 인덱스
		int here;
		//현재 정점까지의 최단거리
		int cost;
		public Vertex(int here, int cost) {
			this.here = here;
			this.cost = cost;
		}
	}
	//총 정점 수
	static int v;
	//그래프의 인접 리스트
	static ArrayList<Edge>[] adj;
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				String[] tmp = br.readLine().split(" ");
				v = Integer.parseInt(tmp[0]);
				int e = Integer.parseInt(tmp[1]);
				int n = Integer.parseInt(tmp[2]);
				int m = Integer.parseInt(tmp[3]);
				//그래프의 인접 리스트 초기화
				adj = new ArrayList[v+1];
				for(int i = 0; i < v+1; i++) {
					adj[i] = new ArrayList<>();
				}
				
				for(int i = 0; i < e; i++) {
					//양방향간선 정보 저장
					tmp = br.readLine().split(" ");
					int a = Integer.parseInt(tmp[0]);
					int b = Integer.parseInt(tmp[1]);
					int val = Integer.parseInt(tmp[2]);
					adj[a].add(new Edge(b, val));
					adj[b].add(new Edge(a, val));
				}
				String[] n_arr = br.readLine().split(" ");
				String[] m_arr = br.readLine().split(" ");
				//소방서 정점과 가상의 정점인 0번과 연결, 가중치는 0
				for(String start : m_arr) {
					adj[0].add(new Edge(Integer.parseInt(start), 0));
				}
				//다익스트라 알고리즘 계산
				int[] dist = dijkstra(0);
				//System.out.println(Arrays.toString(dist));

				int ret = 0;
				for(int i = 0; i < n; i++) {
					ret += dist[Integer.parseInt(n_arr[i])];
				}
				System.out.println(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	static int INF = 987654321;
	//다익스트라 알고리즘
	private static int[] dijkstra(int src) {
		int[] dist = new int[v+1];
		Arrays.fill(dist, INF);
		PriorityQueue<Vertex> pq = new PriorityQueue<>(new Comparator<Vertex>() {
			@Override
			public int compare(Vertex o1, Vertex o2) {
				return o1.cost-o2.cost;
			}
		});
		
		pq.add(new Vertex(src, 0));
		dist[src] = 0;
		
		while(!pq.isEmpty()) {
			Vertex now = pq.poll();
			int here = now.here;
			int cost = now.cost;
			if(dist[here] < cost) {
				continue;
			}
			
			for(Edge e : adj[here]) {
				int there = e.dest;
				int nextDist = cost+e.weight;
				if(dist[there] > nextDist) {
					dist[there] = nextDist;
					pq.add(new Vertex(there, nextDist));
				}
			}
		}
		
		return dist;
	}
}
