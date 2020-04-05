package algospot.INSERTION;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {	
	static class Node {
		//노드에 저장된 원소
		int key;
		//이 노드를 루트로 하는 서브트리의 크기 
		int size=1;
		//노드의 우선순위
		int priority;
		//왼쪽, 오른쪽 두 자식의 객체
		Node left,right;
		//생성자
		public Node(int key) {
			this.key = key;
			//우선순위 랜덤으로 생성
			priority = (int) (Math.random()*Integer.MAX_VALUE);
		}
		
		//왼쪽 서브노드 설정
		public void setLeft(Node left) {
			this.left = left;
			calcSize();
		}
		
		//오른쪽 서브노드 설정
		public void setRight(Node right) {
			this.right = right;
			calcSize();
		}
		
		void calcSize() {
			this.size = 1;
			if(left != null) this.size += left.size;
			if(right != null) this.size += right.size;
		}
	}
	
	//왼쪽과 오른쪽 서브트리 클래스
	static class NodePair {
		Node small, large;
		public NodePair(Node small, Node large) {
			this.small = small;
			this.large = large;
		}
	}

	//루트를 포함하는 트리를 키값을 기준으로 왼쪽과 오른쪽 서브트리로 나눔
	static NodePair split(Node root, int key) {
		if(root == null) {
			return new NodePair(null, null);
		}
		
		//key가 루트의 값보다 크면 오른쪽 서브트리를 쪼갬
		if(root.key < key) {
			NodePair rs = split(root.right, key);
			root.setRight(rs.small);
			
			//key가 루트보다 크므로 루트를 포함하는 서브트리에는 key보다 작은값만 있음
			//루트의 오른쪽 서브트리에서 큰값은 NodePair의 큰값 부분
			return new NodePair(root, rs.large);
		}
		
		//루트가 key 이상이면 왼쪽 서브트리를 쪼갬
		NodePair ls = split(root.left, key);
		root.setLeft(ls.large);
		
		//key가 루트보다 작으므로 루트를 포함하는 서브트리에는 key보다 큰값만 있음
		//루트의 왼쪽 서브트리에서 작은값은 NodePair의 작은값 부분
		return new NodePair(ls.small, root);
	}

	//root에 node를 삽입한 결과의 루트를 반환함
	static Node insert(Node root, Node node) {
		if(root == null) {
			return node;
		}
		
		//새 노드의 우선순위가 높은경우
		if(root.priority < node.priority) {
			//새 노드의 값을 기준으로 왼쪽과 오른쪽의 서브트리 생성
			NodePair splited = split(root, node.key);
			node.setLeft(splited.small);
			node.setRight(splited.large);
			return node;
		} else if(node.key < root.key) {
			//새 노드의 값이 루트보다 작은경우
			root.setLeft(insert(root.left, node));
		} else {
			//새 노드의 값이 루트보다 큰경우
			root.setRight(insert(root.right, node));
		}
		
		return root;
	}
	
	static Node merge(Node a, Node b) {
		//a 가 null일 경우 b반환
		if(a == null) {
			return b;
		}
		//b 가 null일 경우 b반환
		if(b == null) {
			return a;
		}
		//b의 우선순위값이 더 큰 경우
		if(a.priority < b.priority) {
			b.setLeft(merge(a, b.left));
			return b;
		}
		//a의 우선순위값이 더 큰 경우
		a.setRight(merge(a.right, b));
		return a;
	}

	static Node erase(Node root, int key) {
		if(root == null) {
			return root;
		}
		
		//해당 키를 가지고 있는 노드를 찾은 경우
		if(root.key == key) {
			//해당 노드의 서브트리를 합친 후 반환
			Node ret = merge(root.left, root.right);
			return ret;
		}
		
		if(key < root.key) {
			//키값이 루트의 키값보다 작은 경우 왼쪽 서브트리에서 삭제
			root.setLeft(erase(root.left, key));
		} else {
			//키값이 루트의 키값보다 큰 경우 오른쪽 서브트리에서 삭제
			root.setRight(erase(root.right, key));
		}
		return root;
	}
	
	static Node kth(Node root, int k) {
		int leftSize = 0;
		//왼쪽 서브트리 계산
		if(root.left != null) {
			leftSize = root.left.size;
		}
		
		if(k <= leftSize) {
			//k번째 노드가 왼쪽 서브트리에 있음
			return kth(root.left, k);
		}
		if(k==leftSize+1) {
			//k번째 노드가 루트
			return root;
		}
		//k번째 노드가 오른쪽 서브트리에 있음
		return kth(root.right, k-leftSize-1);
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				//solve(br);
				solve2(br);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//트립으로 구현
	static void solve(BufferedReader br) throws Exception {
		int n = Integer.parseInt(br.readLine());
		Node candidate = null;
		//1~n까지 키값을 가지는 트리 생성
		for(int i = 1; i <= n; i++) {
			candidate = insert(candidate, new Node(i));
		}
		//이동 횟수 배열 입력 받음
		int[] shifted = Arrays.stream(br.readLine().split(" "))
								.mapToInt(Integer::parseInt)
								.toArray();
		//결과배열
		int[] arr = new int[n];
		for(int i = n-1; i >= 0; i--) {
			int larger = shifted[i];
			//후보수중 larger만큼 큰수가 있다 = 전체크기+1-larger번째 수
			Node key = kth(candidate, i+1-larger);
			arr[i] = key.key;
			//해당 수 삭제
			candidate = erase(candidate, key.key);
		}
		System.out.print(arr[0]);
		for(int i = 1; i < n; i++) {
			System.out.print(" "+arr[i]);
		}
		System.out.println();
	}
	
	//리스트로 구현
	static void solve2(BufferedReader br) throws Exception {
		int n = Integer.parseInt(br.readLine());
		List<Integer> keyList = new ArrayList<>();
		//1~n까지 배열 입력
		for(int i = 1; i <= n; i++) {
			keyList.add(i);
		}
		int[] arr = new int[n];
		int[] shifted = Arrays.stream(br.readLine().split(" "))
								.mapToInt(Integer::parseInt)
								.toArray();
		for(int i = n-1; i >= 0; i--) {
			int larger = shifted[i];
			//후보수중 larger만큼 큰수가 있다 = 전체크기-larger 인덱스 수(인덱스는 0부터 시작, 수는 1부터 시작)
			int key = keyList.get(i-larger);
			arr[i] = key;
			keyList.remove(i-larger);
		}
		
		System.out.print(arr[0]);
		for(int i = 1; i < n; i++) {
			System.out.print(" "+arr[i]);
		}
		System.out.println();
	}
}
