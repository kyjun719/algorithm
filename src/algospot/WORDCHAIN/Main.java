package algospot.WORDCHAIN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

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
				
				//그래프 생성
				makeGraph();
				System.out.println(solve());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//간선에 해당하는 단어 목록들
	static Queue<String>[][] graph;
	//그래프의 인접 배열 표현 
	static int[][] adj;
	//정점에서 나가는 간선수, 정점으로 들어오는 간선수 저장 
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
			//간선 추가
			adj[a][b]++;
			//간선에 해당하는 단어 추가
			graph[a][b].add(word);
			//나가는 간선수 추가
			outdegree[a]++;
			//들어오는 간선수 추가
			indegree[b]++;
		}
	}
	
	private static String solve() {
		//모든 정점이 짝수점인지 확인
		if(!checkEuler()) {
			return "IMPOSSIBLE";
		}
		
		//오일러 서킷 생성
		ArrayList<Integer> circuit = getEulerTrailOrCircuit();
		Collections.reverse(circuit);
		//오일러 서킷은 각 정점들의 리스트로, 사이즈는 무조건 입력받은 단어갯수+1개여야 함
		if(circuit.size() != words.size()+1) {
			return "IMPOSSIBLE";
		}
		
		//단어 정렬후 반환
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
			//해당 정점에서 나가는 간선수와 들어오는 간선수 차이 계산
			int del = indegree[i]-outdegree[i];
			//방향그래프 이므로 간선수의 차이가 하나 이상 나면 안됨
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
		//모든 정점이 들어오는 간선수 나가는 간선수 인 경우 오일러 서킷
		//한 정점은 나가는 간선, 다른 정점은 들어오는 간선이 많은 경우 오일러 트레일
		return (plus==0 && minus==0) || (plus==1 && minus==1);
	}
	
	private static ArrayList<Integer> getEulerTrailOrCircuit() {
		ArrayList<Integer> circuit = new ArrayList<>();
		//오일러 트레일 먼저 계산해봄
		for(int i = 0; i < 26; i++) {
			//정점이 나가는 간선이 하나 더 많은 경우 해당 서킷은 오일러 트레일
			//해당 정점을 시작으로 오일러 트레일 계산
			if(outdegree[i] == indegree[i]+1) {
				getEulerCircuit(i, circuit);
				return circuit;
			}
		}
		
		for(int i = 0; i < 26; i++) {
			//한 정점에서 나가는 간선이 있는 경우 해당 정점을 시작으로 오일러 서킷 계산
			if(outdegree[i] > 0) {
				getEulerCircuit(i, circuit);
				return circuit;
			}
		}
		
		return circuit;
	}

	//오일러 서킷 계산
	private static void getEulerCircuit(int here, ArrayList<Integer> circuit) {
		for(int there = 0; there < adj.length; there++) {
			//해당 정점의 나가는 간선을 모두 순회할 떄 까지 반복
			while(adj[here][there] > 0) {
				adj[here][there]--;
				getEulerCircuit(there, circuit);
			}
		}
		circuit.add(here);
	}
}
