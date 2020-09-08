package acmicpc._1141;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Trie trie = new Trie();
		for(int i = 0; i < n; i++) {
			trie.insert(br.readLine(), 0);
		}
		
		System.out.println(trie.getLeafCount());
	}
	
	private void checkTrie(Trie trie) {
		Queue<Trie> q = new LinkedList<>();
		q.add(trie);
		while(!q.isEmpty()) {
			Trie t = q.poll();
			if(t.word != null) {
				boolean isNext = false;
				for(Trie next : t.ch) {
					if(next != null) {
						isNext=true;
						break;
					}
				}
				System.out.println(t.word+">>"+isNext);
			}
			for(Trie next : t.ch) {
				if(next != null) {
					q.add(next);
				}
			}
		}
	}
	
	private static class Trie {
		private String word;
		private Trie[] ch = new Trie[26];
		
		public void insert(String word, int idx) {
			if(idx==word.length()) {
				this.word=word;
			} else {
				int tmp = word.charAt(idx)-'a';
				if(ch[tmp]==null) {
					ch[tmp]=new Trie();
				}
				ch[tmp].insert(word, idx+1);
			}
		}
		
		public int getLeafCount() {			
			int ret = 0;
			boolean isNext=false;
			for(Trie next : ch) {
				if(next != null) {
					ret+=next.getLeafCount();
					isNext=true;
				}
			}
			
			return isNext?ret:1;
		}
	}
}
