package algospot.NTHLON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	//간선정보
	static class Edge {
		int dest;
		int weight;
		public Edge(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
	}

	//정점정보
	static class Vertex {
		int here;
		int cost;
		public Vertex(int here, int cost) {
			this.here = here;
			this.cost = cost;
		}
	}
	
	//간선 번호는 팀간 차이이므로 -199~199이므로 200을 더하여 1~399로 사용함
	static int vertex(int idx) {
		return idx+200;
	}
	//처음 시작하는 정점의 인덱스
	static int START = 401;
	static int INF = 987654321;
	//그래프의 인접 리스트
	static ArrayList<Edge>[] adj;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				//간선정보 초기화
				adj = new ArrayList[410];
				for(int i = 0; i < 410; i++) {
					adj[i] = new ArrayList<>();
				}
				
				int m = Integer.parseInt(br.readLine());
				for(int i = 0; i < m; i++) {
					int[] tmp = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
					int a = tmp[0];
					int b = tmp[1];
					//값의 차이를 뺀값을 정점으로함
					int del = a-b;
					//시작 정점과 팀간 시간차를 인덱스로 하는 정점과 연결, 간선 가중치는 a팀의 경기시간으로 설정
					adj[START].add(new Edge(vertex(del), a));
					//그래프의 정점 범위는 -200~200내에 있음
					//모든 정점들과 해당 시간의 경기를 간선으로 하는 정점들을 연결함
					for(int delta = -200; delta <= 200; delta++) {
						//delta정점과 입력받은 간선을 지나갈 때 나타나는 다음간선 계산
						int next = delta+del;
						//다음 간선의 절대값이 200보다 큰경우 다음 간선 계산
						if(Math.abs(next) > 200) {
							continue;
						}
						//delta 정점과 입력받은 간선을 지날때 나타나는 정점과 연결, 간선 가중치는 a팀의 경기시간으로 설정
						adj[vertex(delta)].add(new Edge(vertex(next), a));
					}
				}
				//다익스트라 알고리즘 계산
				int[] dist = dijkstra(START);
				//시간의 총합이 0인 정점의 최단경로값 출력, INF인 경우 불가능 출력
				System.out.println(dist[vertex(0)] == INF?"IMPOSSIBLE":dist[vertex(0)]);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static int[] dijkstra(int src) {
		int[] dist = new int[410];
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
				int nextDist = cost + e.weight;
				if(dist[there] > nextDist) {
					dist[there] = nextDist;
					pq.add(new Vertex(there, nextDist));
				}
			}
		}
		
		return dist;
	}
}
