package algospot.DICTIONARY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	//입력한 단어 목록
	static ArrayList<String> words;
	//간선의 인접 리스트
	static ArrayList<Integer>[] adj;
	//정점 방문여부
	static boolean[] visited;
	//입력받은 단어 갯수
	static int n;
	//dfs가 끝난 순서를 저장하는 리스트
	static ArrayList<Integer> order;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				n = Integer.parseInt(br.readLine());
				words = new ArrayList<>();
				for(int i = 0; i < n; i++) {
					words.add(br.readLine());
				}
				//글자간 간선 저장
				getGraph();
				//DAG 생성 및 역전
				String ret = getOrder();
				System.out.println(ret.isEmpty()?"INVALID HYPOTHESIS":ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//글자간 간선 생성
	private static void getGraph() {
		adj = new ArrayList[26];
		for(int i = 0; i < 26; i++) {
			adj[i] = new ArrayList<>();
		}

		for(int i = 1; i < n; i++) {
			String word1 = words.get(i-1);
			String word2 = words.get(i);
			for(int k = 0; k < Math.min(word1.length(), word2.length()); k++) {
				//k번째의 글자가 틀린경우 해당 간선 저장
				if(word1.charAt(k) != word2.charAt(k)) {
					//a->b로 가는 간선 생성 
					int a = word1.charAt(k)-'a';
					int b = word2.charAt(k)-'a';
					adj[a].add(b);
					break;
				}
			}
		}
	}
	
	//DAG 생성 및 역전
	private static String getOrder() {
		int n = adj.length;
		visited = new boolean[n];
		order = new ArrayList<>();
		//dfs
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				dfs(i);
			}
		}
		
		//위상정렬을 위해 dfs가 끝난 순서 역전
		Collections.reverse(order);
		//사이클이 없는지 확인
		for(int i = 0; i < order.size(); i++) {
			for(int j = i+1; j < order.size(); j++) {
				if(adj[order.get(j)].contains(order.get(i))) {
					return "";
				}
			}
		}
		
		//숫자를 문자로 바꿈
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < order.size(); i++) {
			sb.append(String.valueOf((char)(order.get(i)+'a')));
		}
		
		return sb.toString();
	}
	
	private static void dfs(int here) {
		visited[here] = true;
		for(int i = 0; i < adj[here].size(); i++) {
			int there = adj[here].get(i);
			if(!visited[there]) {
				dfs(there);
			}
		}
		order.add(here);
	}
}
