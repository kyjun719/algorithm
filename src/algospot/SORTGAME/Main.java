package algospot.SORTGAME;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @see https://algospot.com/judge/problem/read/SORTGAME
 * @author jun
 *
 * input
3
8
1 2 3 4 8 7 6 5
4
3 4 1 2
3
1 2 3

 * output
1
2
0
 */
public class Main {
	static class ArrayData {
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
	
	static HashMap<ArrayData, Integer> toSort;
	static void precal(int k) {		
		ArrayList<Integer> perm = new ArrayList<>();
		for(int i = 0; i < k; i++) {
			perm.add(i);
		}
		ArrayData start = new ArrayData();
		start.arr = perm;
		Queue<ArrayData> q = new LinkedList<>();
		q.add(start);
		toSort.put(start, 0);
		while(!q.isEmpty()) {
			ArrayData here = q.poll();
			int cost = toSort.get(here);
			for(int i = 0; i < k; i++) {
				for(int j = i+2; j <= k; j++) {
					Collections.reverse(here.arr.subList(i, j));
					if(toSort.get(here) == null) {
						ArrayData tmp = new ArrayData();
						for(int val : here.arr) {
							tmp.arr.add(val);
						}
						toSort.put(tmp, cost+1);
						q.add(tmp);
					}
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
