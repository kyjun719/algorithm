package algospot.GALLERY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/GALLERY
 * @author jun
 * input
3
6 5
0 1
1 2
1 3
2 5
0 4
4 2
0 1
2 3
1000 1
0 1

 * output
3
2
999
 */
public class Main {
	static int g,h;
	static ArrayList<Integer>[] adj;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				int[] info = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				g = info[0];
				h = info[1];
				ArrayList<Integer[]> graph = new ArrayList<>();
				for(int i = 0; i < h; i++) {
					int[] tmp = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
					graph.add(new Integer[] {tmp[0],tmp[1]});
				}
				
				makeGraph(graph);
				System.out.println(solve());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void makeGraph(ArrayList<Integer[]> graph) {
		adj = new ArrayList[g];
		for(int i = 0; i < g; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < graph.size(); i++) {
			Integer[] tmp = graph.get(i);
			adj[tmp[0]].add(tmp[1]);
			adj[tmp[1]].add(tmp[0]);
		}
	}
	
	static boolean[] visited;
	static int WATCHED=0, UNWATCHED=1, INSTALLED=2, installed;
	private static int solve() {
		installed = 0;
		visited = new boolean[g];
		for(int i = 0; i < g; i++) {
			if(!visited[i] && dfs(i) == UNWATCHED) {
				installed++;
			}
		}
		return installed;
	}

	private static int dfs(int here) {
		visited[here] = true;
		int[] children = new int[3];
		for(int there : adj[here]) {
			if(!visited[there]) {
				children[dfs(there)]++;
			}
		}
		if(children[UNWATCHED] > 0) {
			installed++;
			return INSTALLED;
		}
		if(children[INSTALLED] > 0) {
			return WATCHED;
		}
		return UNWATCHED;
	}
}
