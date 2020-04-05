package algospot.CHILDRENDAY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				String[] tmp = br.readLine().split(" ");
				ArrayList<Integer> number = new ArrayList<>();
				for(int i = 0; i < tmp[0].length(); i++) {
					number.add(tmp[0].charAt(i)-'0');
				}
				//간선을 오름차순으로 정렬하면 사전순으로 가장 앞에 있는 경로를 찾을 수 있음
				Collections.sort(number);
				System.out.println(solve(number, Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2])));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String solve(ArrayList<Integer> number, int n, int m) {
		int[] parent = new int[2*n];
		Arrays.fill(parent, -1);
		int[] choice = new int[2*n];
		Arrays.fill(choice, -1);

		//n보다 작은 0번 추가
		Queue<Integer> queue = new LinkedList<>();
		parent[0] = 0;
		queue.add(0);
		
		while(!queue.isEmpty()) {
			Integer here = queue.poll();
			for(Integer num : number) {
				//here노드에서 간선 num을 따라감
				int there = getNextVertexNum(here, num, n);
				if(parent[there] == -1) {
					parent[there] = here;
					choice[there] = num;
					queue.add(there);
				}
			}
		}
		//n보다 큰 m에 도달하지 못한 경우 실패
		if(parent[n+m] == -1) {
			return "IMPOSSIBLE"; 
		}
		
		//n보다 큰 m에서부터 부모로 가는 간선들을 따라가면서 결과값을 계산함
		String ret = "";
		int here = n+m;
		while(parent[here] != here) {
			//n보다 큰 m에서 부터 시작하므로 결과값을 뒤집어야하므로 가장 먼저 오는 숫자가 뒤로 가도록 함
			ret = choice[here]+ret;
			here = parent[here];
		}
		
		return ret;
	}
	
	private static int getNextVertexNum(int here, int num, int n) {
		int there = here*10+num;
		//다음 숫자가 n보다 큰 경우 정점의 인덱스는 n보다 큼 
		if(there >= n) {
			return n+there%n;
		}
		return there%n;
	}
}
