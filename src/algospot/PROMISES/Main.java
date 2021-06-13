package algospot.PROMISES;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static int INF = 987654321;
	private static int[][] adj = new int[201][201];
	private static int v;

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			int c = Integer.parseInt(br.readLine());
			for(int tc = 0; tc < c; tc++) {
				int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

				v = tmp[0];
				int m = tmp[1];
				int n = tmp[2];

				for(int i = 0; i < v; i++) {
					Arrays.fill(adj[i], INF);
				}
				for(int i = 0; i < v; i++) {
					adj[i][i]=0;
				}

				for(int i = 0; i < m; i++) {
					int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
					int a = info[0];
					int b = info[1];
					int v = Math.min(info[2], adj[a][b]);

					adj[a][b] = v;
					adj[b][a] = v;
				}

				int ans = 0;
				for(int i = 0; i < n; i++) {
					int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
					if(!isUpdate(info[0], info[1], info[2])) {
						ans++;
					}
				}

				System.out.println(ans);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean isUpdate(int a, int b, int c) {
		//현재 계산된 간선보다 클경우 거짓 반환
		if(adj[a][b] <= c) {
			return false;
		}

		//x->y가 a,b를 이용하려면 x->a->b->y이거나 x->b->a->y이여야 함
		for(int x = 0; x < v; x++) {
			for(int y = 0; y < v; y++) {
				adj[x][y] = Math.min(adj[x][y], Math.min(adj[x][a]+c+adj[b][y], adj[x][b]+c+adj[a][y]));
			}
		}

		return true;
	}
}
