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
				//long ret = solve1(arr);
				//long ret = solve2(arr);
				long ret = solve3(arr,0,arr.length-1);
				System.out.println(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static class FenwickTree {
		int[] tree;
		FenwickTree(int n) {
			tree = new int[n+1];
		}
		
		int sum(int pos) {
			pos++;
			int ret = 0;
			while(pos > 0) {
				ret += tree[pos];
				pos &= (pos-1);
			}
			
			return ret;
		}
		
		void add(int pos, int val) {
			pos++;
			while(pos < tree.length) {
				tree[pos] += val;
				pos += (pos & -pos);
			}
		}
	}
	
	static long solve1(int[] arr) {
		FenwickTree tree = new FenwickTree(1000000);
		long ret = 0;
		for(int num : arr) {
			ret += tree.sum(999999) - tree.sum(num);
			tree.add(num, 1);
		}
		
		return ret;
	}
	
	static class Treap {
		int key;
		int prior = new Random().nextInt();
		Treap left, right;
		int size = 1;
		Treap(int key) {
			this.key = key;
		}
		void setLeft(Treap left) {
			this.left = left;
			calSize();
		}
		void setRight(Treap right) {
			this.right = right;
			calSize();
		}
		private void calSize() {
			size = 1;
			if(left != null) {
				size += left.size;
			}
			if(right != null) {
				size += right.size;
			}
		}
		public String toString() {
			//return "{("+key+","+prior+")>>"+left+"&&"+right+"}";
			return"{("+key+")>>"+left+"&&"+right+"}";
		}
	}
	
	static class TreapPair {
		Treap left, right;
		TreapPair(Treap left, Treap right) {
			this.left = left;
			this.right = right;
		}
	}
	static TreapPair split(Treap root, int key) {
		if(root == null) {
			return new TreapPair(null, null);
		}
		
		if(root.key < key) {
			TreapPair rs = split(root.right, key);
			root.setRight(rs.left);
			return new TreapPair(root, rs.right);
		}
		TreapPair ls = split(root.left, key);
		root.setLeft(ls.right);
		return new TreapPair(ls.left, root);
	}
	static Treap insert(Treap root, Treap node) {
		if(root == null) {
			return node;
		}
		if(root.prior < node.prior) {
			TreapPair split = split(root, node.key);
			node.setLeft(split.left);
			node.setRight(split.right);
			return node;
		} else if(root.key <= node.key) {
			root.setRight(insert(root.right, node));
		} else {
			root.setLeft(insert(root.left, node));
		}
		return root;
	}
	static int countLessThan(Treap root, int key) {
		if(root == null) {
			return 0;
		}
		if(root.key >= key) {
			return countLessThan(root.left, key);
		}
		int ls = root.left == null?0:root.left.size;
		return ls + 1 + countLessThan(root.right, key);
	}
	static long solve2(int[] arr) {
		Treap root = null;
		long ret = 0;
		for(int i = 0; i < arr.length; i++) {
			ret += i-countLessThan(root, arr[i]+1);
			root = insert(root, new Treap(arr[i]));
		}
		return ret;
	}
	
	static long solve3(int[] arr, int left, int right) {
		if(left == right) {
			return 0;
		}
		int mid = (left+right)/2;
		long ret = solve3(arr,left,mid) + solve3(arr,mid+1,right);
		int[] tmp = new int[right-left+1];
		int tmpIdx = 0, leftIdx=left, rightIdx=mid+1;
		while(leftIdx <= mid || rightIdx <= right) {
			if(leftIdx <= mid && (rightIdx > right || arr[leftIdx] <= arr[rightIdx])) {
				tmp[tmpIdx++] = arr[leftIdx++];
			} else {
				ret += mid-leftIdx+1;
				tmp[tmpIdx++] = arr[rightIdx++];
			}
		}
		for(int i = 0; i < tmp.length; i++) {
			arr[left+i] = tmp[i];
		}
		return ret;
	}
}
