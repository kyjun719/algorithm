package algospot.PACKING;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see  https://algospot.com/judge/problem/read/PACKING
 * @author jun
 * input
2
6 10
laptop 4 7
camera 2 10
xbox 6 6
grinder 4 7
dumbell 2 5
encyclopedia 10 4
6 17
laptop 4 7
camera 2 10
xbox 6 6
grinder 4 7
dumbell 2 5
encyclopedia 10 4
 * output
24 3
laptop
camera
grinder
30 4
laptop
camera
xbox
grinder
 */
public class Main {
	//전체 물건 갯수
	static int n;
	//캐리어 용량
	static int w;
	//물건 이름 배열
	static String[] name = new String[100];
	//물건 부피 배열
	static int[] weight = new int[100];
	//물건 필요도 배열
	static int[] need = new int[100];
	static int[][] cache = new int[100][1000];
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			
			for(int tc = 0; tc < c; tc++) {
				String[] tmp = bf.readLine().split(" ");
				n = Integer.parseInt(tmp[0]);
				w = Integer.parseInt(tmp[1]);
				
				for(int i = 0; i < n; i++) {
					tmp = bf.readLine().split(" ");
					name[i] = tmp[0];
					weight[i] = Integer.parseInt(tmp[1]);
					need[i] = Integer.parseInt(tmp[2]);
					Arrays.fill(cache[i], -1);
				}
				List<String> nameList = new ArrayList<>();
				reconstruct(w,0,nameList);
				System.out.println(packing(w,0) + " " + nameList.size());
				for(String tmpName : nameList) {
					System.out.println(tmpName);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int packing(int capacity, int item_idx) {
		if(item_idx == n) {
			return 0;
		}
		
		if(cache[item_idx][capacity] != -1) {
			return cache[item_idx][capacity];
		}
		//해당 물건을 넣지 않았을때의 최대 필요도
		int not_in = packing(capacity, item_idx+1);
		//해당 물건을 넣을때의 최대 필요도
		int in = 0; 
		
		if(capacity >= weight[item_idx]) {
			in = packing(capacity - weight[item_idx], item_idx+1)+need[item_idx];
		}
		
		cache[item_idx][capacity] = not_in > in? not_in : in;
		return cache[item_idx][capacity];
	}
	
	//가방에 들어가는 물건들 이름을 nameList로 반환 
	static void reconstruct(int capacity, int item_idx, List<String> nameList) {
		if(item_idx == n) {
			return;
		}
		//해당물건을 안넣었을때 최대 필요도가 변하지 않은 경우
		if(packing(capacity, item_idx) == packing(capacity, item_idx+1)) {
			reconstruct(capacity, item_idx+1, nameList);
		} else {
			//해당물건이 필요한 경우
			nameList.add(name[item_idx]);
			reconstruct(capacity - weight[item_idx], item_idx+1, nameList);
		}
	}
}
