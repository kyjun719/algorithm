package algospot.GALLERY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	//갤러리의 수와 연결하는 복도의 수
	static int g,h;
	//그래프의 인접 리스트
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
				//그래프 생성
				makeGraph(graph);
				//그래프의 최소 지배노드 집합 계산
				System.out.println(solve());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//그래프 생성
	private static void makeGraph(ArrayList<Integer[]> graph) {
		adj = new ArrayList[g];
		for(int i = 0; i < g; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < graph.size(); i++) {
			Integer[] tmp = graph.get(i);
			//루트없는트리 이므로 양방향으로 간선 추가
			adj[tmp[0]].add(tmp[1]);
			adj[tmp[1]].add(tmp[0]);
		}
	}
	//정점 방문 여부
	static boolean[] visited;
	//정점 상태값들
	static int WATCHED=0, UNWATCHED=1, INSTALLED=2;
	//설치된 카메라 수, 지배노드의 갯수와 동일
	static int installed;
	private static int solve() {
		installed = 0;
		visited = new boolean[g];
		for(int i = 0; i < g; i++) {
			//해당 정점을 방문하지 않고, 해당 정점의 깊이우선탐색 결과가 지배중이지 않은경우, 해당 노드에 카메라 설치
			if(!visited[i] && dfs(i) == UNWATCHED) {
				installed++;
			}
		}
		return installed;
	}

	private static int dfs(int here) {
		visited[here] = true;
		//해당 정점과 연결된 정점들 상태값 카운트 배열
		int[] children = new int[3];
		for(int there : adj[here]) {
			//해당 정점들의 자손중 아직 방문하지 않은 경우
			if(!visited[there]) {
				//해당 정점을 루트로 깊이 우선 탐색을 하여 해당 정점의 상태값 계산 
				children[dfs(there)]++;
			}
		}
		//해당 정점의 자손중 지배중이지 않은 정점이 있는 경우 현재 노드에 카메라 설치
		if(children[UNWATCHED] > 0) {
			installed++;
			return INSTALLED;
		}
		//해당 정점의 자손중 카메라가 설치되어 있는경우 현재 노드는 피지배중
		if(children[INSTALLED] > 0) {
			return WATCHED;
		}
		//이외의 경우 지배당하지 않음을 반환
		return UNWATCHED;
	}
}
