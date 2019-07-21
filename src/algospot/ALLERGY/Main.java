package algospot.ALLERGY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see https://algospot.com/judge/problem/read/ALLERGY
 * @author jun
 * input
2
4 6
cl bom dara minzy
2 dara minzy
2 cl minzy
2 cl dara
1 cl
2 bom dara
2 bom minzy
10 7
a b c d e f g h i j
6 a c d h i j
3 a d i
7 a c f g h i j
3 b d g
5 b c f h i
4 b e g j
5 b c g h i

 * output
2
3
 */
public class Main {
	static int n, m, answer;
	//j번 음식을 먹을수 있는 사람들
	static List<List<Integer>> eatList;
	//j번 사람이 먹을수 있는 음식
	static List<List<Integer>> canEatList;
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			
			for(int tc = 0; tc < c; tc++) {
				answer = 987654321;
				String[] tmp = bf.readLine().split(" ");
				n = Integer.parseInt(tmp[0]);
				m = Integer.parseInt(tmp[1]);
				List<String> nameList = Arrays.stream(bf.readLine().split(" "))
						.collect(Collectors.toList());
				eatList = new ArrayList<>(m);
				canEatList = new ArrayList<>(n);
				for(int i = 0; i < n; i++) {
					canEatList.add(new ArrayList<>());
				}
				for(int i = 0; i < m; i++) {
					eatList.add(new ArrayList<>());
					String[] infoTmp = bf.readLine().split(" ");
					for(int j = 1; j <= Integer.parseInt(infoTmp[0]); j++) {
						int nameIdx = nameList.indexOf(infoTmp[j]);
						eatList.get(i).add(nameIdx);	
						canEatList.get(nameIdx).add(i);
					}					
				}
				search(new int[n], 0);
				System.out.println(answer);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void search(int[] eatenArr, int foodCnt) {
		int nextIdx = getMinimun(eatenArr);
		
		if(nextIdx == -1) {
			//음식을 다 찾은경우 결과 반환
			answer = Math.min(answer, foodCnt);
			return;
		}
		
		List<Integer> eatableList = canEatList.get(nextIdx);
		for(int i = 0; i < eatableList.size(); i++) {
			int food = eatableList.get(i);
			List<Integer> eatablePeople = eatList.get(food);
			for(int j : eatablePeople) {
				eatenArr[j]++;
			}
			search(eatenArr, foodCnt+1);
			for(int j : eatablePeople) {
				eatenArr[j]--;
			}
		}
	}
	
	//먹을수 있는 최소갯수를 가진 사람의 인덱스 반환
	static int getMinimun(int[] eatenArr) {
		int idx = -1;
		int canEatSize = m;
		for(int i = 0; i < n; i++) {
			if(eatenArr[i] == 0) {
				if(canEatList.get(i).size() < canEatSize) {
					canEatSize = canEatList.get(i).size();
					idx = i;
				}
			}
		}
		return idx;
	}
}
