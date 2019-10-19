package acmicpc._1260;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/1260
 * @author jun
 *
 */
public class Main {
	static int n,m,v;
	static ArrayList<Node> nodeList;
	
	private static class Node{
		ArrayList<Node> child;
		int n;
		Node(int n) {
			this.n = n;
			child = new ArrayList<>();
		}
		ArrayList<Node> getSortedChildren() {
			child.sort(new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					// TODO Auto-generated method stub
					return o2.n-o1.n;
				}
			});
			return child;
		}
		@Override
		public String toString() {
			//return this.n+">>"+child.toString();
			return ""+n;
		}
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			n = info[0];
			m = info[1];
			v = info[2];
			nodeList = new ArrayList<>();
			for(int i = 0; i < n; i++ ) {
				nodeList.add(new Node(i+1));
			}
			for(int i = 0; i < m; i++) {
				int[] tmp = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				nodeList.get(tmp[0]-1).child.add(nodeList.get(tmp[1]-1));
				nodeList.get(tmp[1]-1).child.add(nodeList.get(tmp[0]-1));
			}
			dfs();
			bfs();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void dfs() {
		// TODO Auto-generated method stub
		Stack<Node> searchStack = new Stack<>();
		searchStack.push(nodeList.get(v-1));
		String ret = "";
		
		boolean[] isSearched = new boolean[n];
		while(!searchStack.isEmpty()) {
			Node node = searchStack.pop();
			if(!isSearched[node.n-1]) {
				isSearched[node.n-1] = true;
				ret += node.n+" ";
				ArrayList<Node> next = node.getSortedChildren();
				for(Node tmp : next) {
					searchStack.push(tmp);
				}
			}
		}
		System.out.println(ret);
	}
	
	private static void bfs() {
		// TODO Auto-generated method stub
		Queue<Node> searchQueue = new LinkedList<>();
		searchQueue.add(nodeList.get(v-1));
		String ret = "";
		
		boolean[] isSearched = new boolean[n];
		while(!searchQueue.isEmpty()) {
			Node node = searchQueue.poll();
			if(!isSearched[node.n-1]) {
				isSearched[node.n-1] = true;
				ret += node.n+" ";
				ArrayList<Node> next = node.getSortedChildren();
				for(int i = next.size()-1; i >=0;i--) {
					searchQueue.add(next.get(i));
				}
			}
		}
		System.out.println(ret);
	}
}
