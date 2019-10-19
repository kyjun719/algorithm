package algospot.SOLONG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @see https://algospot.com/judge/problem/read/SOLONG
 * @author jun
 * input
2
7 8
ALL 4
AND 3
FISH 8
FOR 6
SO 4
THANKS 9
THE 9
SO LONG AND THANKS FOR ALL THE FISH
7 8
ALL 4
AND 5
FISH 3
FOR 6
SO 8
THANKS 1
THE 2
SO LONG AND THANKS FOR ALL THE FISH

 * output
28
29
 */
public class Main {
	static int toNumber(char ch) {
		return ch - 'A';
	}
	static class TrieNode {
		TrieNode[] children = new TrieNode[26];
		int first = -1, terminal = -1;
		void insert(String word, int ptr, int id) {
			if(first == -1) {
				first = id;
			}
			
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
		
		TrieNode find(String word, int ptr) {
			if(ptr == word.length()) {
				return this;
			}
			
			int next = toNumber(word.charAt(ptr));
			if(children[next] == null) {
				return null;
			}
			return children[next].find(word, ++ptr);
		}
		
		int type(String word, int ptr, int id) {
			if(word.length() == ptr) {
				return 0;
			}
			
			if(first == id) {
				return 1;
			}
			int next = toNumber(word.charAt(ptr));
			return 1+children[next].type(word, ++ptr, id);
		}
	}
	
	static int n,m;
	
	static class Word {
		String word;
		int freq;
		Word(String word, int freq) {
			this.word = word;
			this.freq = freq;
		}
		public String toString() {
			return "("+word+","+freq+")";
		}
	}
	
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
				List<Word> wordList = new ArrayList<>();
				for(int i = 0; i < n; i++) {
					String[] tmp = br.readLine().split(" ");
					wordList.add(new Word(tmp[0], Integer.parseInt(tmp[1])));
				}
				
				Collections.sort(wordList, new Comparator<Word>() {

					@Override
					public int compare(Word o1, Word o2) {
						// TODO Auto-generated method stub
						if(o1.freq == o2.freq) {
							return o1.word.compareTo(o2.word);
						}
						return o2.freq - o1.freq;
					}
				});
				
				TrieNode trie = new TrieNode();
				for(int i = 0; i < wordList.size(); i++) {
					trie.insert(wordList.get(i).word, 0, i);
				}
				trie.first = -1;
				
				String[] input = br.readLine().split(" ");
				int ret = countTyping(trie, input[0]);
				ret += m-1;
				for(int i = 1; i < input.length; i++) {
					ret += countTyping(trie, input[i]);
				}
				System.out.println(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static int countTyping(TrieNode trie, String word) {
		// TODO Auto-generated method stub
		TrieNode node = trie.find(word, 0);
		if(node == null || node.terminal == -1) {
			return word.length();
		}
		return trie.type(word, 0, node.terminal);
	}
}
