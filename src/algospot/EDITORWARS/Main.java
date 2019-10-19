package algospot.EDITORWARS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/EDITORWARS
 * @author jun
 * input
4
4 4
ACK 0 1
ACK 1 2
DIS 1 3
ACK 2 0
100 4
ACK 0 1
ACK 1 2
ACK 2 3
ACK 3 4
3 3
ACK 0 1
ACK 1 2
DIS 2 0
8 6
DIS 0 1
ACK 1 2
ACK 1 4
DIS 4 3
DIS 5 6
ACK 5 7

 * output
MAX PARTY SIZE IS 3
MAX PARTY SIZE IS 100
CONTRADICTION AT 3
MAX PARTY SIZE IS 5
 */
public class Main {
	static class BipartiteUnionFind {
		int[] parent, rank, size, enemy;
		BipartiteUnionFind(int n) {
			parent = new int[n];
			rank = new int[n];
			size = new int[n];
			enemy = new int[n];
			Arrays.fill(rank, 0);
			Arrays.fill(enemy, -1);
			Arrays.fill(size, 1);
			for(int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}
		
		int find(int u) {
			if(parent[u] == u) {
				return u;
			}
			return parent[u] = find(parent[u]);
		}
		
		int merge(int u, int v) {
			if(u == -1 || v == -1) {
				return Math.max(u, v);
			}
			
			u = find(u); v = find(v);
			if(u == v) {
				return v;
			}
			if(rank[u] > rank[v]) {
				int tmp = u;
				u = v;
				v = tmp;
			}
			if(rank[u] == rank[v]) {
				rank[v]++;
			}
			parent[u] = v;
			size[v] += size[u];
			return v;
		}
		
		boolean ack(int u, int v) {
			u = find(u); v = find(v);
			if(enemy[u] == v) {
				return false;
			}
			
			int a = merge(u, v);
			int b = merge(enemy[u], enemy[v]);
			enemy[a] = b;
			if(b != -1) {
				enemy[b] = a;
			}
			return true;
		}
		
		boolean dis(int u, int v) {
			u = find(u); v = find(v);
			if(u == v) {
				return false;
			}
			
			int a = merge(u, enemy[v]);
			int b = merge(v, enemy[u]);
			enemy[a] = b;
			enemy[b] = a;
			return true;
		}
	}
	static int n, m;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t<tc; t++) {
				int[] info = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				n = info[0];
				m = info[1];
				BipartiteUnionFind buf = new BipartiteUnionFind(n);
				boolean isErr = false;
				for(int i = 1; i <= m; i++) {
					String[] rep = br.readLine().split(" ");
					if(isErr) continue;
					boolean ret;
					if(rep[0].equals("ACK")) {
						ret = buf.ack(Integer.parseInt(rep[1]), Integer.parseInt(rep[2]));
					} else {
						ret = buf.dis(Integer.parseInt(rep[1]), Integer.parseInt(rep[2]));
					}
					if(!ret) {
						System.out.println("CONTRADICTION AT " + i);
						isErr = true;
					}
				}
				if(!isErr) {
					System.out.println("MAX PARTY SIZE IS " + maxParty(buf));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int maxParty(BipartiteUnionFind buf) {
		int ret = 0;
		for(int i = 0; i < n; i++) {
			if(buf.parent[i] == i) {
				int enemy = buf.enemy[i];
				if(enemy > i) continue;
				int a = buf.size[i];
				int b = enemy == -1?0:buf.size[enemy];
				ret += Math.max(a, b);
			}
		}
		
		return ret;
	}
}
