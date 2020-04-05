package algospot.EDITORWARS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static class BipartiteUnionFind {
		//parent : i의 최상위 부모노드, 루트인경우 자기자신
		//rank : i가 루트인 경우 i를 루트로 하는 트리의 랭크
		//size : i를 루트로 하는 경우 해당 트리의 크기
		//enemy : i가 루트인 경우 해당 집합과 적대적인 집합의 크기, 없을경우 -1
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
		
		//u가 속한 트리의 루트번호 반환
		int find(int u) {
			if(parent[u] == u) {
				return u;
			}
			return parent[u] = find(parent[u]);
		}

		//u가 속한 트리와 v가 속한 트리를 합침
		int merge(int u, int v) {
			//u나 v가 공집합인 경우 둘중 하나 반환
			if(u == -1 || v == -1) {
				return Math.max(u, v);
			}
			
			u = find(u); v = find(v);
			//이미 같은 트리에 속한 경우
			if(u == v) {
				return v;
			}
			
			//u의 랭크가 v의 랭크보다 큰 경우 두 수를 바꿈
			if(rank[u] > rank[v]) {
				int tmp = u;
				u = v;
				v = tmp;
			}
			
			//둘의 랭크가 같은경우 결과 트리의 랭크를 1 늘림
			if(rank[u] == rank[v]) {
				rank[v]++;
			}
			parent[u] = v;
			size[v] += size[u];
			return v;
		}
		
		//u와 v가 동지관계
		boolean ack(int u, int v) {
			u = find(u); v = find(v);
			//u의 루트와 v의 루트가 서로 적이면 모순
			if(enemy[u] == v) {
				return false;
			}
			//동지끼리 합침
			int a = merge(u, v);
			//동지의 적끼리 합침
			int b = merge(enemy[u], enemy[v]);
			//루트간 적대관계 설정
			enemy[a] = b;
			//두집합이 적대하는 집합이 없는 경우 b는 -1일 수도 있음
			if(b != -1) {
				enemy[b] = a;
			}
			return true;
		}
		
		//u와 v가 적대관계
		boolean dis(int u, int v) {
			u = find(u); v = find(v);
			//u의 루트와 v의 루트가 같으면 모순
			if(u == v) {
				return false;
			}
			//u와 v의 적과 합침 
			int a = merge(u, enemy[v]);
			//v와 u의 적과 합침
			int b = merge(v, enemy[u]);
			//적대관계 설정
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
					boolean ret;
					//메시지에 따른 처리 분기
					if(rep[0].equals("ACK")) {
						ret = buf.ack(Integer.parseInt(rep[1]), Integer.parseInt(rep[2]));
					} else {
						ret = buf.dis(Integer.parseInt(rep[1]), Integer.parseInt(rep[2]));
					}
					//결과가 모순일 경우 종료
					if(!ret) {
						System.out.println("CONTRADICTION AT " + i);
						isErr = true;
						break;
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
			//i번째가 루트인 경우
			if(buf.parent[i] == i) {
				int enemy = buf.enemy[i];
				//적의 루트가 지금 번호보다 다음인 경우 종료
				if(enemy > i) continue;
				int a = buf.size[i];
				//적대적인 루트가 없는경우 크기는 0
				int b = enemy == -1?0:buf.size[enemy];
				//모임의 최대크기를 찾는것 이므로 최대값을 더함
				ret += Math.max(a, b);
			}
		}
		
		return ret;
	}
}
