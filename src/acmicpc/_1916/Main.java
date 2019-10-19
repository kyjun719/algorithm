package acmicpc._1916;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	private static int n,m;
	private static int[] dist;
	private static ArrayList<Node>[] arr;
	static class Node {
		int end,val;
		Node(int end, int val) {
			this.end = end;
			this.val = val;
		}
	}
	static int MAX = 987654321;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());
			dist = new int[n];
			Arrays.fill(dist, MAX);
			arr = new ArrayList[n];
			for(int i = 0; i < n; i++) {
				arr[i] = new ArrayList<Node>();
			}
			for(int i = 0; i < m; i++) {
				int[] tmp = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				arr[tmp[0]-1].add(new Node(tmp[1]-1,tmp[2]));
			}
			int[] ptr = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			int start = ptr[0]-1;
			int end = ptr[1]-1;
			System.out.println(calc(start, end));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int calc(int start, int end) {
		boolean[] visited = new boolean[n];
		dist[start] = 0;
		
		while(true) {
			int idx = -1;
			int minDist = MAX;
			for(int i = 0; i < n; i++) {
				if(!visited[i] && (minDist > dist[i])) {
					idx = i;
					minDist = dist[i];
				}
			}
			if(idx == end) {
				break;
			}
			
			visited[idx] = true;
			for(int i = 0; i < arr[idx].size(); i++) {
				int dst = arr[idx].get(i).end;
				int val = arr[idx].get(i).val;
				if(!visited[dst] && (dist[dst] > dist[idx]+val)) {
					dist[dst] = dist[idx]+val;
				}
			}
		}
		return dist[end];
	}
}
