package algospot.JOSEPHUS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int tc = Integer.parseInt(br.readLine());
			
			for(int t = 0; t < tc; t++) {
				int[] info = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				int n = info[0];
				int k = info[1];
				
				//solve1(n, k);
				solve2(n, k);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//리스트를 사용한 문제 해결
	private static void solve1(int n, int k) {
		List<Integer> soldier = new ArrayList<>();
		for(int i = 1; i <= n; i++) {
			soldier.add(i);
		}
		
		int kill = 0;
		while(soldier.size() > 2) {
			soldier.remove(kill);
			n--;
			kill = (kill+k-1)%n;
		}
		System.out.println(soldier.get(0)+" "+soldier.get(1));
	}
	
	//큐를 사용한 문제 해결
	private static void solve2(int n, int k) {
		Queue<Integer> soldier = new LinkedList<>();
		for(int i = 1; i <= n; i++) {
			soldier.add(i);
		}
		
		while(soldier.size() > 2) {
			soldier.poll();
			//현재부터 k-1의 인덱스는 사라지지 않으므로 다시 뒤로 보냄
			for(int i = 0; i < k-1; i++) {
				soldier.add(soldier.poll());
			}
		}
		int a = soldier.poll();
		int b = soldier.poll();
		if(a > b) {
			int tmp = b;
			b = a;
			a = tmp;
		}
		System.out.println(a+" "+b);
	}
}
