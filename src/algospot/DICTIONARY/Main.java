package algospot.DICTIONARY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @see https://algospot.com/judge/problem/read/DICTIONARY
 * @author jun
 *
 * input
3
3
ba
aa
ab
5
gg
kia
lotte
lg
hanhwa
6
dictionary
english
is
ordered
ordinary
this

* output
INVALID HYPOTHESIS
ogklhabcdefijmnpqrstuvwxyz
abcdefghijklmnopqrstuvwxyz
 */
public class Main {
	static ArrayList<String> words;
	static ArrayList<Integer>[] adj;
	static boolean[] visited;
	static int n;
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
				
				getGraph();
				String ret = getOrder();
				System.out.println(ret.isEmpty()?"INVALID HYPOTHESIS":ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static void getGraph() {
		adj = new ArrayList[26];
		for(int i = 0; i < 26; i++) {
			adj[i] = new ArrayList<>();
		}

		for(int i = 1; i < n; i++) {
			String word1 = words.get(i-1);
			String word2 = words.get(i);
			for(int k = 0; k < Math.min(word1.length(), word2.length()); k++) {
				if(word1.charAt(k) != word2.charAt(k)) {
					int a = word1.charAt(k)-'a';
					int b = word2.charAt(k)-'a';
					adj[a].add(b);
					break;
				}
			}
		}
	}
	
	private static String getOrder() {
		int n = adj.length;
		visited = new boolean[n];
		order = new ArrayList<>();
		for(int i = n-1; i >= 0; i--) {
			if(!visited[i]) {
				dfs(i);
			}
		}
		
		Collections.reverse(order);
		for(int i = 0; i < order.size(); i++) {
			for(int j = i+1; j < order.size(); j++) {
				if(adj[order.get(j)].contains(order.get(i))) {
					return "";
				}
			}
		}
		
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
