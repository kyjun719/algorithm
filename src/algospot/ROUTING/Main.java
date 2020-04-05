package algospot.ROUTING;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	//정점간 간선 정보
	static class Edge {
		int dest;
		double weight;
		public Edge(int dest, double weight) {
			this.dest = dest;
			this.weight = weight;
		}
	}
	//정점과 정점까지의 거리 정보
	static class Vertex {
		int here;
		double cost;
		public Vertex(int here, double cost) {
			this.here = here;
			this.cost = cost;
		}
	}
	//그래프의 인접 리스트
	static ArrayList<Edge>[] adj;
	//전체 정점 수
	static int n;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				String[] tmp = br.readLine().split(" ");
				n = Integer.parseInt(tmp[0]);
				int m = Integer.parseInt(tmp[1]);
				
				//그래프의 인접리스트 초기화
				adj = new ArrayList[n];
				for(int i = 0; i < n; i++) {
					adj[i] = new ArrayList<>();
				}
				
				for(int i = 0; i < m; i++) {
					tmp = br.readLine().split(" ");
					//간선 가중치는 계산의 편의성을 위해 로그를 취함, 결과값을 보일때는 pow로 변환해줘야함
					adj[Integer.parseInt(tmp[0])].add(new Edge(Integer.parseInt(tmp[1]), Math.log10(Double.parseDouble(tmp[2]))));
				}
				//0번부터 다익스트라 결과값 계산
				double[] dist = dijkstra(0);
				System.out.println(Math.pow(10, dist[n-1]));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static double INF = 987654321;
	private static double[] dijkstra(int src) {
		//최단거리 배열 초기화
		double[] dist = new double[n];
		Arrays.fill(dist, INF);
		
		//우선순위큐 초기화
		PriorityQueue<Vertex> pq = new PriorityQueue<>(new Comparator<Vertex>() {
			@Override
			public int compare(Vertex o1, Vertex o2) {
				if(o1.cost == o2.cost) {
					return 0;
				}
				return o1.cost>o2.cost?1:-1;
			}
		});
		dist[src] = 0;
		pq.add(new Vertex(0, 0));
		
		while(!pq.isEmpty()) {
			Vertex now = pq.poll();
			int here = now.here;
			double cost = now.cost;
			//큐에서 꺼낸 정점의 최단거리가 최신이 아닌경우 넘어감
			if(dist[here] < cost) {
				continue;
			}
			//인접한 정점 검색
			for(Edge e : adj[here]) {
				int there = e.dest;
				double nextDist = cost+e.weight;
				//최단거리 정보 업데이트
				if(dist[there] > nextDist) {
					pq.add(new Vertex(there, nextDist));
					dist[there] = nextDist;
				}
			}
		}
		return dist;
	}
}
