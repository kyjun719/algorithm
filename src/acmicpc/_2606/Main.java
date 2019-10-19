package acmicpc._2606;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/2606
 * @author jun
 *
 */
public class Main {
	static int n,m;
	
	private static class Node{
		ArrayList<Node> linked;
		int num;
		Node(int num) {
			this.num = num;
			linked = new ArrayList<>();
		}
		
		@Override
		public String toString() {
			return ""+num;
		}
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());
			ArrayList<Node> nodeList = new ArrayList<>();
			for(int i = 0; i < n; i++) {
				nodeList.add(new Node(i+1));
			}
			
			for(int i = 0; i < m; i++) {
				int[] tmp = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				nodeList.get(tmp[0]-1).linked.add(nodeList.get(tmp[1]-1));
				nodeList.get(tmp[1]-1).linked.add(nodeList.get(tmp[0]-1));
			}
			solve(nodeList);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void solve(ArrayList<Node> nodeList) {
		Queue<Node> search = new LinkedList<>();
		boolean[] checked = new boolean[n+1];
		search.add(nodeList.get(0));
		int cnt = -1;
		
		while(!search.isEmpty()) {
			Node node = search.poll();
			if(!checked[node.num]) {
				checked[node.num] = true;
				cnt++;
				search.addAll(node.linked);
			}
		}
		System.out.println(cnt);
	}
}
