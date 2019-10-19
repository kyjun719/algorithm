package algospot.NH;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @see https://algospot.com/judge/problem/read/NH
 * @author jun
 *input
3
2 2
rm
dd
4 4
a
b
c
d
100 4
aa
ba
ab
cd

 * output
674
4095
5293
 */
public class Main {
	static int ALPHABETS = 26;
	static int toNumber(char ch) {
		return ch - 'a';
	}
	static class TrieNode {
		int no;
		TrieNode[] next = new TrieNode[ALPHABETS];
		TrieNode[] children = new TrieNode[ALPHABETS];
		int terminal = -1;
		TrieNode fail;
		List<Integer> output = new ArrayList<>();
		void initNext() {
			for(int i = 0; i < ALPHABETS; i++) {
				next[i] = new TrieNode();
			}
		}
		void insert(String word, int ptr, int id) {
			if(word.length() == ptr) {
				terminal = id;
			} else {
				int next = toNumber(word.charAt(ptr));
				if(children[next] == null) {
					children[next] = new TrieNode();
				}
				children[next].insert(word, ++ptr, id);
			}
		}
	}
	
	static int n,m;
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
				cache = new int[101][1001];
				for(int i = 0; i < 101; i++) {
					Arrays.fill(cache[i], -1);
				}
				
				TrieNode root = new TrieNode();
				root.initNext();
				for(int i = 0; i < m; i++) {
					root.insert(br.readLine(), 0, i);
				}
				computeFailFunc(root);
				computeTransition(root, 0);
				int ret = count(n,root);
				System.out.println(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void computeFailFunc(TrieNode root) {
		Queue<TrieNode> q = new LinkedList<>();
		root.fail = root;
		q.add(root);
		while(!q.isEmpty()) {
			TrieNode here = q.poll();
			for(int node = 0; node < ALPHABETS; node++) {
				TrieNode child = here.children[node];
				if(child == null) {
					continue;
				}
				if(here == root) {
					child.fail = root;
				} else {
					TrieNode t = here.fail;
					while(t != root && t.children[node] == null) {
						t = t.fail;
					}
					if(t.children[node] != null) {
						t = t.children[node];
					}
					child.fail = t;
				}
				child.output = child.fail.output;
				if(child.terminal != -1) {
					child.output.add(child.terminal);
				}
				q.add(child);
			}
		}
	}
	
	static void computeTransition(TrieNode here, int nodeCounter) {
		here.no = nodeCounter++;
		for(int node = 0; node < ALPHABETS; node++) {
			TrieNode next = here;
			while(next != next.fail && next.children[node] == null) {
				next = next.fail;
			}
			if(next.children[node] != null) {
				next = next.children[node];
			}
			here.next[node] = next;
			if(here.children[node] != null) {
				computeTransition(here.children[node], nodeCounter);
			}
		}
	}
	
	static int MOD = 10007;
	static int[][] cache;
	static int count(int length, TrieNode state) {
		if(!state.output.isEmpty()) {
			return 0;
		}
		if(length == 0) {
			return 1;
		}
		int ret = cache[length][state.no];
		if(ret != -1) {
			return ret;
		}
		ret = 0;
		for(int node = 0; node < ALPHABETS; node++) {
			ret += count(length-1, state.next[node]);
			ret %= MOD;
		}
		cache[length][state.no] = ret;
		return ret;
	}
}
