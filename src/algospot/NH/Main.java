package algospot.NH;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int ALPHABETS = 26;
	static int toNumber(char ch) {
		return ch - 'a';
	}
	static class TrieNode {
		//노드 번호
		int no;
		//다음 글자가 추가되었을때 이동하는 노드들
		TrieNode[] next = new TrieNode[ALPHABETS];
		//자손 노드들
		TrieNode[] children = new TrieNode[ALPHABETS];
		//현위치에서 끝나는 문자열의 번호
		int terminal = -1;
		//실패연결, 이 노드에 대응되는 문자열의 접미사 이면서 트라이에 포함된 최대길이의 문자열
		TrieNode fail;
		//출력 문자열 목록, 이 노드가 방문되었을 떄 등장하는 문자열들의 번호
		List<Integer> output = new ArrayList<>();

		//이 노드를 루트로 하는 트라이에 문자열word의 ptr에 해당하는 문자를 추가함
		void insert(String word, int ptr, int id) {
			if(word.length() == ptr) {
				//종로 노드인 경우
				terminal = id;
			} else {
				//종료 노드가 아닌경우 자손노드로 재귀호출
				int next = toNumber(word.charAt(ptr));
				//해당 자식 노드가 없으면 생성함
				if(children[next] == null) {
					children[next] = new TrieNode();
				}
				children[next].insert(word, ++ptr, id);
			}
		}
	}
	
	static int n,m;
	//노드 번호 카운터
	static int nodeCounter;
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
				//캐시 초기화
				cache = new int[101][1001];
				for(int i = 0; i < cache.length; i++) {
					Arrays.fill(cache[i], -1);
				}
				nodeCounter = 0;
				
				//노드 삽입
				TrieNode root = new TrieNode();
				for(int i = 0; i < m; i++) {
					root.insert(br.readLine(), 0, i);
				}
				//실패연결과 출력 문자열 목록을 계산
				computeFailFunc(root);
				//각 노드에 문자열 추가시 이동하는 노드 설정
				computeTransition(root);
				//문자열 수 계산
				int ret = count(n,root);
				System.out.println(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//트라이가 주어질때 각 노드에 대해 실패연결과 출력 문자열 목록을 계산함
	static void computeFailFunc(TrieNode root) {
		//루트에서부터 시작해서 한단계씩 아래로 내려가며 각 노드의 실패연결을 계산함
		Queue<TrieNode> q = new LinkedList<>();
		//루트의 실패연결은 자기자신
		root.fail = root;
		q.add(root);
		while(!q.isEmpty()) {
			TrieNode here = q.poll();
			//here의 모든 자손에 대해 실패연결을 계산하고 큐에 넣음
			for(int node = 0; node < ALPHABETS; node++) {
				TrieNode child = here.children[node];
				//자손이 없는경우 건너뜀
				if(child == null) {
					continue;
				}
				if(here == root) {
					//부모가 루트일 경우 실패연결은 루트
					child.fail = root;
				} else {
					//아닌경우 보모의 실패연결을 따라가면서 일치하는 연결을 찾음
					TrieNode t = here.fail;
					//t가 루트거나 t의 자손중 매치하지않는 단어를 가진 노드가 나타날때 까지 실패연결 탐색
					while(t != root && t.children[node] == null) {
						t = t.fail;
					}
					//t의 자손중 틀린 글자를 가진 자손이 있는경우 해당 노드가 child의 실패연결
					if(t.children[node] != null) {
						t = t.children[node];
					}
					child.fail = t;
				}
				//출력문자열 목록 : 실패 연결을 따라간 노드에서 복사함
				child.output.addAll(child.fail.output);
				//child위치에서 끝나는 바늘 문자열이 있는경우 해당 노드에 해당되는 단어의 인덱스도 추가함
				if(child.terminal != -1) {
					child.output.add(child.terminal);
				}
				//다음 검색을 위해 큐에 노드 저장
				q.add(child);
			}
		}
	}
	
	//각 노드에 문자열 추가시 이동하는 노드 설정
	static void computeTransition(TrieNode here) {
		//노드에 번호 설정, 루트를 0부터 시작으로 노드에 방문 순서를 매김
		here.no = nodeCounter++;
		//현재 위치에서 모든 글자에 대해 이동하는 노드 설정
		for(int node = 0; node < ALPHABETS; node++) {
			//현재 위치에서 한글자를 추가하였을 때 일치하는 노드검색, 없을경우 루트
			TrieNode next = here;
			while(next != next.fail && next.children[node] == null) {
				next = next.fail;
			}
			//한글자를 추가했을 때 일치하는 노드인 경우
			if(next.children[node] != null) {
				next = next.children[node];
			}
			//현재 노드에서 한글자를 추가하였을 때 다음 노드 설정
			here.next[node] = next;
			//현재 노드의 자식들에 대해 재귀 설정
			if(here.children[node] != null) {
				computeTransition(here.children[node]);
			}
		}
	}
	
	static int MOD = 10007;
	static int[][] cache;
	static int count(int length, TrieNode state) {
		//현재위치에서 패턴을 포함하면 0 반환
		if(!state.output.isEmpty()) {
			return 0;
		}
		//패턴을 포함하지 않고 다 완성한 경우 1 반환
		if(length == 0) {
			return 1;
		}
		//캐시에 값이 저장되어 있는경우 캐시값 반환
		int ret = cache[length][state.no];
		if(ret != -1) {
			return ret;
		}

		ret = 0;
		//모든 글자에 더하여 재귀값 계산
		for(int node = 0; node < ALPHABETS; node++) {
			ret += count(length-1, state.next[node]);
			ret %= MOD;
		}
		//캐시 저장 및 값 반환
		cache[length][state.no] = ret;
		return ret;
	}
}
