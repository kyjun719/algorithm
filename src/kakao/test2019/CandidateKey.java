package kakao.test2019;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @see https://programmers.co.kr/learn/courses/30/lessons/42890
 * @author jun

6 4
"100","ryan","music","2"
"200","apeach","math","2"
"300","tube","computer","3"
"400","con","computer","4"
"500","muzi","music","3"
"600","apeach","music","2"

2
 */
public class CandidateKey {
	///*
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] info = br.readLine().split(" ");
			int n = Integer.parseInt(info[0]);
			int m = Integer.parseInt(info[1]);
			String[][] arr = new String[n][m];
			for(int i = 0; i < n; i++) {
				arr[i] = br.readLine().replace("\"", "").split(",");
			}
			System.out.println(new CandidateKey().solution(arr));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//*/
	public int solution(String[][] relation) {
		int row = relation.length;
		int col = relation[0].length;
		List<Integer> answerList = new ArrayList<>();
		for(int i = 0; i < (1 << col); i++) {
			if(isUnique(relation, i)) {
				if(isMinimal(answerList, i)) {
					answerList.add(i);
				}
			}
		}
		return answerList.size();
    }

	private boolean isMinimal(List<Integer> answerList, int key) {
		for(int i = 0; i < answerList.size(); i++) {
			if((answerList.get(i) & key) == answerList.get(i)) {
				return false;
			}
		}
		return true;
	}

	private boolean isUnique(String[][] relation, int key) {
		//System.out.println(Integer.toBinaryString(key));
		Set<String> set = new HashSet<>();
		for(int i = 0; i < relation.length; i++) {
			String tmp = "";
			for(int j = 0; j < relation[i].length; j++) {
				if((key & (1 << j)) > 0) {
					tmp += relation[i][j];
				}
			}
			set.add(tmp);
		}
		//System.out.println(set.toString());
		return set.size() == relation.length;
	}
}
