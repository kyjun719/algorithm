package algospot.WORDCHAIN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @see https://algospot.com/judge/problem/read/WORDCHAIN
 * @author jun
 * input
3
4
dog
god
dragon
need
3
aa
ab
bb
2
ab
cd

 * output
need dog god dragon
aa ab bb
IMPOSSIBLE
 */
public class Main {
	static int n;
	static ArrayList<String> words;
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
				
				makeGraph();
				System.out.println(solve());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	static Queue<String>[][] graph;
	static int[][] adj;
	static int[] outdegree, indegree;
	private static void makeGraph() {
		adj = new int[26][26];
		outdegree = new int[26];
		indegree = new int[26];
		graph = new LinkedList[26][26];
		for(int i = 0; i < 26; i++) {
			for(int j = 0; j < 26; j++) {
				graph[i][j] = new LinkedList<>();
			}
		}
		
		for(int i = 0; i < n; i++) {
			String word = words.get(i);
			int a = word.charAt(0)-'a';
			int b = word.charAt(word.length()-1)-'a';
			adj[a][b]++;
			graph[a][b].add(word);
			outdegree[a]++;
			indegree[b]++;
		}
	}
	
	private static String solve() {
		if(!checkEuler()) {
			return "IMPOSSIBLE";
		}
		
		ArrayList<Integer> circuit = getEulerTrailOrCircuit();
		Collections.reverse(circuit);
		if(circuit.size() != words.size()+1) {
			return "IMPOSSIBLE";
		}
		
		StringBuffer sb = new StringBuffer();
		for(int i = 1; i < circuit.size(); i++) {
			if(sb.length() != 0) {
				sb.append(" ");
			}
			int a = circuit.get(i-1);
			int b = circuit.get(i);
			sb.append(graph[a][b].poll());
		}
		
		return sb.toString();
	}

	private static boolean checkEuler() {
		int plus = 0, minus = 0;
		for(int i = 0; i < adj.length; i++) {
			int del = indegree[i]-outdegree[i];
			if(del < -1 || del > 1) {
				return false;
			}
			if(del == 1) {
				plus++;
			}
			if(del == -1) {
				minus++;
			}
		}
		
		return (plus==0 && minus==0) || (plus==1 && minus==1);
	}
	
	private static ArrayList<Integer> getEulerTrailOrCircuit() {
		ArrayList<Integer> circuit = new ArrayList<>();
		for(int i = 0; i < 26; i++) {
			//trail
			if(outdegree[i] == indegree[i]+1) {
				getEulerCircuit(i, circuit);
				return circuit;
			}
		}
		
		for(int i = 0; i < 26; i++) {
			//circuit
			if(outdegree[i] > 0) {
				getEulerCircuit(i, circuit);
				return circuit;
			}
		}
		
		return circuit;
	}

	private static void getEulerCircuit(int here, ArrayList<Integer> circuit) {
		for(int there = 0; there < adj.length; there++) {
			while(adj[here][there] > 0) {
				adj[here][there]--;
				getEulerCircuit(there, circuit);
			}
		}
		circuit.add(here);
	}
}
