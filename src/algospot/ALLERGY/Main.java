package algospot.ALLERGY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
				
				//이름들을 인덱스로 변환
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
						//해당 음식을 먹을 수 있는 사람의 인덱스 추가
						eatList.get(i).add(nameIdx);
						//해당 사람의 먹을 수 있는 음식 인덱스 추가
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
		//먹을수 있는 음식이 제일 적은 사람 탐색
		int nextIdx = getMinimun(eatenArr);
		
		if(nextIdx == -1) {
			//음식을 다 찾은경우 결과 반환
			answer = Math.min(answer, foodCnt);
			return;
		}
		
		List<Integer> eatableList = canEatList.get(nextIdx);
		for(int i = 0; i < eatableList.size(); i++) {
			int food = eatableList.get(i);
			//해당 음식을 먹을수 있는 사람들 음식수 증가
			List<Integer> eatablePeople = eatList.get(food);
			for(int j : eatablePeople) {
				eatenArr[j]++;
			}
			search(eatenArr, foodCnt+1);
			//해당 음식을 먹을수 있는 사람들 음식수 감소
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
