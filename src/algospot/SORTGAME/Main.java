package algospot.SORTGAME;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	//배열의 상태 데이터
	static class ArrayData {
		//1~8개의 배열의 아이템 크기 순서를 저장한 리스트
		ArrayList<Integer> arr;
		ArrayData() {
			arr = new ArrayList<>(8);
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof ArrayData) {
				ArrayData obj = (ArrayData) o;
				if(obj.arr.size() != this.arr.size()) {
					return false;
				}
				for(int i = 0; i < arr.size(); i++) {
					if(this.arr.get(i) != obj.arr.get(i)) {
						return false;
					}
				}
				return true;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			int ret = 17;
			for(int tmp : arr) {
				ret = ret*31+tmp;
			}
			return ret;
		}
		
		@Override
		public String toString() {
			return arr.toString();
		}
	}
	
	//배열값과 정렬까지 걸리는 최단 경로수 저장 맵
	static HashMap<ArrayData, Integer> toSort;
	//현재 배열 상태에 따른 정렬까지의 최소 뒤집기수 저장 
	static void precal(int k) {		
		//1~k까지 정렬된 배열 생성
		ArrayList<Integer> perm = new ArrayList<>();
		for(int i = 0; i < k; i++) {
			perm.add(i);
		}
		ArrayData start = new ArrayData();
		start.arr = perm;
		//시작은 정렬된 배열부터 시작
		Queue<ArrayData> q = new LinkedList<>();
		q.add(start);
		//정렬된 배열이므로 뒤집기 연산수는 0
		toSort.put(start, 0);
		while(!q.isEmpty()) {
			ArrayData here = q.poll();
			int cost = toSort.get(here);
			for(int i = 0; i < k; i++) {
				for(int j = i+2; j <= k; j++) {
					//subList는 i포함, j 미포함
					//배열의 앞부터 2개 이상씩 반전시킴
					Collections.reverse(here.arr.subList(i, j));
					//해당 배열의 최단경로값이 게산되어있지 않은 경우(해당 배열의 정점이 아직 발견되지 않은 경우) 계산 시작
					if(toSort.get(here) == null) {
						//현재 배열의 상태값과 최단경로수 저장
						ArrayData tmp = new ArrayData();
						tmp.arr = new ArrayList<>(here.arr);
						toSort.put(tmp, cost+1);
						q.add(tmp);
					}
					//반전시킨 배열을 원상복구 시킴
					Collections.reverse(here.arr.subList(i, j));
				}
			}
		}
	}
	
	static int n;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			toSort = new HashMap<>();
			//n=1~8까지의 배열 정점 미리 계산
			for(int i = 1; i <= 8; i++) {
				precal(i);
			}
			
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				n = Integer.parseInt(br.readLine());
				int[] perm = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				System.out.println(solve(perm));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve(int[] perm) {
		//입력받은 배열를 상대적 크기순서의 배열로 새로 만듦
		ArrayData data = new ArrayData();
		for(int i = 0; i < n; i++) {
			int bigCount = 0;
			for(int j = 0; j < n; j++) {
				if(perm[i] > perm[j]) {
					bigCount++;
				}
			}
			data.arr.add(bigCount);
		}
		return toSort.get(data);
	}
}
