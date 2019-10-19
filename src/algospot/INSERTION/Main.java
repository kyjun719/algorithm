package algospot.INSERTION;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see https://algospot.com/judge/problem/read/INSERTION
 * @author jun
 * input
2
5
0 1 1 2 3
4
0 1 2 3

 * output
5 1 4 3 2
4 3 2 1
 */
public class Main {	
	static class Node {
		int key, size, priority;
		Node left,right;
		public Node(int key) {
			this.key = key;
			priority = (int) (Math.random()*Integer.MAX_VALUE);
		}
		
		int getSize() {
			size = 1;
			if(left != null) size += left.getSize();
			if(right != null) size += right.getSize();
			return size;
		}
		
		@Override
		public String toString() {
			return "("+key+")>L["+left+"],R["+right+"]";
		}
	}
	
	static class NodePair {
		Node left, right;
		public NodePair(Node right, Node left) {
			this.left = left;
			this.right = right;
		}
		
		@Override
		public String toString() {
			return "L["+left+"],R["+right+"]";
		}
	}
	
	static NodePair split(Node root, int key) {
		if(root == null) {
			return new NodePair(null, null);
		}
		
		//루트가 key 미만이면 오른쪽 서브트리를 쪼갬
		if(root.key < key) {
			NodePair rs = split(root.right, key);
			root.right = rs.right;
			return new NodePair(root, rs.left);
		}
		
		//루트가 key 이상이면 왼쪽 서브트리를 쪼갬
		NodePair ls = split(root.left, key);
		root.left = ls.left;
		return new NodePair(ls.right, root);
	}
	
	static Node insert(Node root, Node node) {
		if(root == null) {
			return node;
		}
		
		if(root.priority < node.priority) {
			NodePair splited = split(root, node.key);
			node.left = splited.right;
			node.right = splited.left;
			return node;
		} else if(node.key < root.key) {
			root.left = insert(root.left, node);
		} else {
			root.right = insert(root.right, node);
		}
		
		return root;
	}
	
	static Node merge(Node a, Node b) {
		if(a == null) {
			return b;
		}
		if(b == null) {
			return a;
		}
		if(a.priority < b.priority) {
			b.left = merge(a, b.left);
			return b;
		}
		a.right = merge(a.right, b);
		return a;
	}
	
	static Node erase(Node root, int key) {
		if(root == null) {
			return root;
		}
		
		if(root.key == key) {
			Node ret = merge(root.left, root.right);
			root = null;
			return ret;
		}
		
		if(key < root.key) {
			root.left = erase(root.left, key);
		} else {
			root.right = erase(root.right, key);
		}
		return root;
	}
	
	static Node kth(Node root, int k) {
		int leftSize = 0;
		if(root.left != null) {
			leftSize = root.left.getSize();
		}
		
		if(k <= leftSize) {
			return kth(root.left, k);
		}
		if(k==leftSize+1) {
			return root;
		}
		return kth(root, k-leftSize-1);
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				//long start = Calendar.getInstance().getTimeInMillis();
				solve2(br);
				//System.out.println("time::"+(Calendar.getInstance().getTimeInMillis()-start));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//timeout
	static void solve(BufferedReader br) throws Exception {
		int n = Integer.parseInt(br.readLine());
		Node candidate = null;
		for(int i = 1; i <= n; i++) {
			candidate = insert(candidate, new Node(i));
		}
		int[] shifted = Arrays.stream(br.readLine().split(" "))
								.mapToInt(Integer::parseInt)
								.toArray();
		int[] arr = new int[n];
		for(int i = n-1; i >= 0; i--) {
			int larger = shifted[i];
			Node key = kth(candidate, i+1-larger);
			arr[i] = key.key;
			candidate = erase(candidate, key.key);
		}
		System.out.print(arr[0]);
		for(int i = 1; i < n; i++) {
			System.out.print(" "+arr[i]);
		}
		System.out.println();
	}
	
	static void solve2(BufferedReader br) throws Exception {
		int n = Integer.parseInt(br.readLine());
		List<Integer> keyList = new ArrayList<>();
		for(int i = 1; i <= n; i++) {
			keyList.add(i);
		}
		int[] arr = new int[n];
		int[] shifted = Arrays.stream(br.readLine().split(" "))
								.mapToInt(Integer::parseInt)
								.toArray();
		for(int i = n-1; i >= 0; i--) {
			int larger = shifted[i];
			int key = keyList.get(i-larger);
			arr[i] = key;
			try {
				keyList.remove((Object) key);	
			} catch(Exception e) {
				System.out.println(key+">>"+keyList);
				e.printStackTrace();
			}
		}
		System.out.print(arr[0]);
		for(int i = 1; i < n; i++) {
			System.out.print(" "+arr[i]);
		}
		System.out.println();
	}
}
