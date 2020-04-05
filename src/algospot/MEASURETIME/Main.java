package algospot.MEASURETIME;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

/**
 * @see https://algospot.com/judge/problem/read/MEASURETIME
 * @author jun
 * input
2
5
5 1 4 3 2
10
7 8 6 6 1 9 4 4 3 10

 * output
7
25
 */
public class Main {	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t<tc; t++) {
				int n = Integer.parseInt(br.readLine());				
				int[] arr = Arrays.stream(br.readLine().split(" "))
								.mapToInt(Integer::parseInt)
								.toArray();
				//long ret = new Solve1().solve(arr);
				//long ret = new Solve2().solve(arr);
				long ret = new Solve3().solve(arr,0,arr.length-1);
				System.out.println(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static class Solve1 {
		//팬윅트리를 사용한 풀이
		class FenwickTree {
			int[] tree;
			FenwickTree(int n) {
				tree = new int[n+1];
			}
			//0~pos의 부분합을 구함
			int sum(int pos) {
				pos++;
				int ret = 0;
				while(pos > 0) {
					ret += tree[pos];
					//다음 구간을 찾기 위해 최종비트를 지움
					pos &= (pos-1);
				}
				
				return ret;
			}
			//A[pos]에 val을 더함
			void add(int pos, int val) {
				pos++;
				while(pos < tree.length) {
					tree[pos] += val;
					//마지막 비트를 자기자신에 더함
					pos += (pos & -pos);
				}
			}
		}
		
		long solve(int[] arr) {
			FenwickTree tree = new FenwickTree(1000000);
			long ret = 0;
			//숫자범위는 0~999999
			for(int num : arr) {
				//num보다 크고 최대값보다 작은 수의 갯수 계산
				ret += tree.sum(999999) - tree.sum(num);
				//해당 범위에 포함하는 숫자 1 증가
				tree.add(num, 1);
			}
			
			return ret;
		}
	}
	
	static class Solve2 {
		//트립을 사용한 문제 풀이
		class Node {
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
		
		//key보다 작은 원소 세기
		int countLessThan(Node root, int key) {
			if(root == null) {
				return 0;
			}
			
			if(root.key >= key) {
				//키값이 루트값보다 작거나 같은 경우 루트의 왼쪽 서브트리 검색
				return countLessThan(root.left, key);
			}
			
			//키값이 루트값보다 큰경우 왼쪽크기 + 1(루트) + 오른쪽 서브트리의 재귀값 반환
			int ls = ((root.left!=null)?root.left.size:0);
			return ls + 1 + countLessThan(root.right, key);
		}
		
		long solve(int[] arr) {
			Node root = null;
			long ret = 0;
			for(int i = 0; i < arr.length; i++) {
				//배열의 수+1보다 큰 수의 갯수 세기
				ret += i-countLessThan(root, arr[i]+1);
				//해당수의 노드 삽입
				root = insert(root, new Node(arr[i]));
			}
			return ret;
		}
	}
	
	static class Solve3 {
		//병합 정렬을 사용한 문제 풀이
		//반전되는 수의 갯수 반환
		long solve(int[] arr, int left, int right) {
			if(left == right) {
				//구간이 1이므로 반전이 일어나지 않음
				return 0;
			}
			/* 1. 분할 */
			int mid = (left+right)/2;
			//왼쪽과 오른쪽을 나누어서 정렬, 이때 일어난 반전되는 수의 합 계산
			long ret = solve(arr,left,mid) + solve(arr,mid+1,right);
			
			/* 2. 정복 */
			int[] tmp = new int[right-left+1];
			//tmpIdx : 병합한 배열의 인덱스
			//leftIdx : 기존 배열의 왼쪽 구간 인덱스(left~mid)
			//rightIdx : 기존 배열의 오른쪽 구간 인덱스(mid+1~right)
			int tmpIdx = 0, leftIdx=left, rightIdx=mid+1;
			//왼쪽 또는 오른쪽을 다 탐색할때 까지, 더 큰수가 왼쪽에, 더 작은수가 오른쪽에 포함되어 있는 경우
			while(leftIdx <= mid || rightIdx <= right) {
				//왼쪽구간 인덱스가 중간값 보다 작고 오른쪽을 다 탐색했거나 왼쪽값이 오른쪽값보다 작은 경우
				if(leftIdx <= mid && (rightIdx > right || arr[leftIdx] <= arr[rightIdx])) {
					//왼쪽의 수가 오른쪽의 수 보다 작은경우
					//병합한 배열에 왼쪽수 추가
					tmp[tmpIdx++] = arr[leftIdx++];
				} else {
					//오른쪽의 수가 왼쪽의 수 보다 작으므로 왼쪽에 남은 수 만큼 반전이 일어남
					ret += mid-leftIdx+1;
					//병합한 배열에 오른쪽수 추가
					tmp[tmpIdx++] = arr[rightIdx++];
				}
			}
			/* 3. 결합 */
			//tmp에 합친 결과를 arr로 복사함
			for(int i = 0; i < tmp.length; i++) {
				arr[left+i] = tmp[i];
			}
			return ret;
		}
	}
}
