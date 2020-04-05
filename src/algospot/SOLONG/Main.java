package algospot.SOLONG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Main {
	static int toNumber(char ch) {
		return ch - 'A';
	}
	static class TrieNode {
		TrieNode[] children = new TrieNode[26];
		//이 노드를 루트로 하는 트라이에 가장 먼저 추가된 단어의 번호
		int first = -1;
		//이 노드에서 종료하는 문자열의 번호
		int terminal = -1;
		void insert(String word, int ptr, int id) {
			//먼저 추가된 문자열 번호 먼저 갱신
			if(first == -1) {
				first = id;
			}
			
			if(word.length() == ptr) {
				//해당 문자열이 여기서 종료되는 경우 종료되는 문자열 변경
				terminal = id;
			} else {
				//다음 노드로 이동
				int next = toNumber(word.charAt(ptr));
				if(children[next] == null) {
					children[next] = new TrieNode();
				}
				children[next].insert(word, ++ptr, id);
			}
		}
		
		//word와 대응되는 노드를 반환함, 없으면 null
		TrieNode find(String word, int ptr) {
			//찾았으면 해당 노드 반환
			if(ptr == word.length()) {
				return this;
			}
			
			int next = toNumber(word.charAt(ptr));
			//다음 노드가 없는경우 null 반환
			if(children[next] == null) {
				return null;
			}
			//다음노드 검색
			return children[next].find(word, ++ptr);
		}
		
		//이 노드까지 타이핑 했을때 번호가 id인 단어를 타이핑 하기 위해서 몇번의 키를 눌러야 하는가? 
		int type(String word, int ptr, int id) {
			//해당 단어의 노드를 찾은경우 0 반환
			if(word.length() == ptr) {
				return 0;
			}
			
			//해당 노드를 루트로 하는 단어에 처음으로 등장하는 단어가 id번째 단어이므로 탭키를 누르는 횟수인 1 반환
			if(first == id) {
				return 1;
			}
			//다음노드 검색
			int next = toNumber(word.charAt(ptr));
			return 1+children[next].type(word, ++ptr, id);
		}
	}
	
	static int n,m;
	
	static class Word {
		//입력받은 사전의 단어
		String word;
		//출현빈도수
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
				
				//입력단어를 출현빈도수, 같을경우 사전순으로 정렬
				Collections.sort(wordList, new Comparator<Word>() {
					@Override
					public int compare(Word o1, Word o2) {
						if(o1.freq == o2.freq) {
							//출현 빈도수가 같으면 사전순으로 정렬
							return o1.word.compareTo(o2.word);
						}
						//출현빈도의 내림차순으로 정렬
						return o2.freq - o1.freq;
					}
				});
				
				TrieNode trie = new TrieNode();
				//트라이에 단어 입력
				for(int i = 0; i < wordList.size(); i++) {
					trie.insert(wordList.get(i).word, 0, i);
				}
				//아무 입력이 없을시 자동완성이 되지 않으므로 -1로 설정
				trie.first = -1;
				
				//타이핑 해야할 단어목록 저장
				String[] input = br.readLine().split(" ");
				//첫번째 단어의 타이핑횟수 저장
				int ret = countTyping(trie, input[0]);
				//띄어쓰기 타이핑횟수 더함
				ret += m-1;
				//1~n까지의 단어 타이핑 횟수 더함
				for(int i = 1; i < input.length; i++) {
					ret += countTyping(trie, input[i]);
				}
				System.out.println(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//단어의 타이핑횟수 반환
	private static int countTyping(TrieNode trie, String word) {
		//트라이에서 해당 단어의 노드 검색
		TrieNode node = trie.find(word, 0);
		//노드가 없거나 해당 단어로 종료되는 단어가 없는경우 단어의 길이 반환
		if(node == null || node.terminal == -1) {
			return word.length();
		}
		//트라이에서 해당 단어를 타이핑하는데 걸리는 횟수 반환
		return trie.type(word, 0, node.terminal);
	}
}
