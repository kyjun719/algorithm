package kakao.test2019;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author jun

9
5,3
11,5
13,3
3,5
6,1
1,3
8,6
7,2
2,2

[[7,4,6,9,1,8,5,2,3],[9,6,5,8,1,4,3,2,7]]

 */
public class FindRoad {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int[n][2];
			for(int i = 0; i < n; i++) {
				arr[i] = Arrays.stream(br.readLine().split(","))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			new FindRoad().solution(arr);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private class Node {
		int id,x,y;
		Node left,right;
		Node(int id, int x, int y) {
			this.id = id;
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			//return "("+id+":"+x+","+y+")>>"+left+"||"+right;
			return id+"::{"+left+"},{"+right+"}";
		}
	}
	
	private class NodePair {
		Node left, right;
		
		public NodePair(Node left, Node right) {
			// TODO Auto-generated constructor stub
			this.left = left;
			this.right = right;
		}
	}
	
	private NodePair split(Node root, int x) {
		if(root == null) {
			return new NodePair(null, null);
		}
		
		if(root.x < x) {
			NodePair splited = split(root.right, x);
			root.right = splited.right;
			return new NodePair(splited.left, root);
		}
		NodePair splited = split(root.left, x);
		root.left = splited.left;
		return new NodePair(root, splited.right);
	}
	
	private Node insert(Node root, Node node) {
		if(root == null) {
			return node;
		}
		
		if(root.y < node.y) {
			NodePair splited = split(root, node.x);
			node.left = splited.right;
			node.right = splited.left;
			return node;
		}
		if(root.x < node.x) {
			root.right = insert(root.right, node);
		} else {
			root.left = insert(root.left, node);
		}
		return root;
	}
	
	public int[][] solution(int[][] nodeinfo) {
		Node root = null;
		for(int i = 0; i < nodeinfo.length; i++) {
			if(root == null) {
				root = new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]);
			} else {
				root = insert(root, new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]));
			}
		}
		
		List<Integer> pre = new ArrayList<>(); 
		getPreOrder(root, pre);
		List<Integer> post = new ArrayList<>();
		getPostOrder(root, post);
		int[][] answer = new int[2][nodeinfo.length];
		for(int i = 0; i < pre.size(); i++) {
			answer[0][i] = pre.get(i);
			answer[1][i] = post.get(i);
		}
		return answer;
    }

	private void getPreOrder(Node root, List<Integer> list) {
		// TODO Auto-generated method stub
		if(root == null) {
			return;
		}
		list.add(root.id);
		getPreOrder(root.left, list);
		getPreOrder(root.right, list);
	}
	
	private void getPostOrder(Node root, List<Integer> list) {
		// TODO Auto-generated method stub
		if(root == null) {
			return;
		}
		getPostOrder(root.left, list);
		getPostOrder(root.right, list);
		list.add(root.id);
	}
}
