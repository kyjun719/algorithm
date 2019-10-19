package acmicpc._1753;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static int v, e, k;
	static int[] path;
	static int MAX = 987654321;
	static ArrayList<Node>[] vArray;

	static class Node {
		int end, val;

		Node(int start, int end, int val) {
			this.end = end;
			this.val = val;
		}

		public String toString() {
			return end + "::" + val;
		}

		public boolean equals(Object o) {
			if (o instanceof Node) {
				return ((Node) o).end == this.end;
			}
			return false;
		}

		public int hashCode() {
			int ret = 17;
			ret = ret * 31 + end;
			return ret;
		}
	}

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			v = info[0];
			e = info[1];
			k = Integer.parseInt(br.readLine());
			vArray = new ArrayList[v];
			path = new int[v];
			for (int i = 0; i < v; i++) {
				vArray[i] = new ArrayList<Node>();
			}
			Arrays.fill(path, MAX);

			for (int i = 0; i < e; i++) {
				int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				Node node = new Node(tmp[0] - 1, tmp[1] - 1, tmp[2]);
				if (vArray[tmp[0] - 1].contains(node)) {
					Node befNode = vArray[tmp[0] - 1].get(vArray[tmp[0] - 1].indexOf(node));
					if (befNode.val > node.val) {
						vArray[tmp[0] - 1].remove(befNode);
						vArray[tmp[0] - 1].add(node);
					}
				} else {
					vArray[tmp[0] - 1].add(node);
				}
			}

			for (int i = 0; i < v; i++) {
				vArray[i].sort(new Comparator<Node>() {
					@Override
					public int compare(Node o1, Node o2) {
						return o1.val - o2.val;
					}
				});
			}

			calc();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < v; i++) {
				buffer.append(path[i] == MAX ? "INF" : path[i]);
				buffer.append("\n");
			}
			System.out.print(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void calc() {
		int start = k - 1;
		boolean[] visited = new boolean[v];
		path[start] = 0;

		while (true) {
			int idx = -1;
			int minDist = MAX;
			for (int i = 0; i < v; i++) {
				if (!visited[i] && (minDist > path[i])) {
					idx = i;
					minDist = path[i];
				}
			}
			if (idx == -1) {
				break;
			}

			visited[idx] = true;
			for (int i = 0; i < vArray[idx].size(); i++) {
				int next = vArray[idx].get(i).end;
				int val = vArray[idx].get(i).val;
				if (!visited[next] && (path[next] > path[idx] + val)) {
					path[next] = path[idx] + val;
				}
			}
		}
	}
}
