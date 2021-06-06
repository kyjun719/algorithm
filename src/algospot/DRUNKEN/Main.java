package algospot.DRUNKEN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static int INF = 987654321;
	private static int[][] adj = new int[501][501];
	private static int[][] ans = new int[501][501];
	private static int v;

	private static class Vertex {
		int idx;
		int value;

		public Vertex(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}

		@Override
		public String toString() {
			return "["+idx+"::"+value+"]";
		}
	}
	private static List<Vertex> vertexList = new ArrayList<>();

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			v = tmp[0];
			int e = tmp[1];

			int[] vertex = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for(int i = 0; i < vertex.length; i++) {
				vertexList.add(new Vertex(i+1, vertex[i]));
			}

			for(int i = 0; i < adj.length; i++) {
				Arrays.fill(adj[i], INF);
				Arrays.fill(ans[i], INF);
			}

			for(int i = 0; i < e; i++) {
				int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				adj[info[0]][info[1]] = info[2];
				adj[info[1]][info[0]] = info[2];

				ans[info[0]][info[1]] = info[2];
				ans[info[1]][info[0]] = info[2];
			}

			for(int i = 0; i < v; i++) {
				ans[i][i] = 0;
			}

			solve();

			int n = Integer.parseInt(br.readLine());
			StringBuilder stringBuilder = new StringBuilder();
			for(int i = 0; i < n; i++) {
				int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				stringBuilder.append(ans[info[0]][info[1]]);
				stringBuilder.append("\n");
			}

			System.out.print(stringBuilder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void solve() {
		//sort vertex
		vertexList.sort(new Comparator<Vertex>() {
			@Override
			public int compare(Vertex o1, Vertex o2) {
				return o1.value - o2.value;
			}
		});

		for(int k = 0; k < v; k++) {
			//k번째 예상시간이 짧은 정점을 지나는 경로의 최단거리 계산
			Vertex vertex = vertexList.get(k);
			int w = vertex.idx;
			int val = vertex.value;

			for(int i = 1; i <= v; i++) {
				for(int j = 1; j <= v; j++) {
					//최단 간선의 거리 계산
					adj[i][j] = Math.min(adj[i][j], adj[i][w]+adj[w][j]);
					//w정점의 예상시간까지 포함한 값 계산
					ans[i][j] = Math.min(ans[i][j], adj[i][w]+val+adj[w][j]);
				}
			}
		}
	}
}
