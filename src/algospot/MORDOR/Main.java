package algospot.MORDOR;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static class RMQ {
		//구간별 최소값
		int[] rangeMin;
		//구간별 최대값, 음수
		int[] rangeMax;
		int n;
		RMQ(int[] arr) {
			n = arr.length;
			rangeMin = new int[n*4];
			rangeMax = new int[n*4];
			//구간별 최소값 계산
			init(arr, 0, n-1, 1, true);
			//구간별 -를 곱한 배열의 최소값 계산
			init(mul(arr, -1), 0, n-1, 1, false);
		}

		private int[] mul(int[] arr, int k) {
			for(int i = 0; i < arr.length; i++) {
				arr[i] *= k;
			}
			return arr;
		}

		private int init(int[] arr, int left, int right, int node, boolean isMin) {
			if(left == right) {
				int ret = arr[left];
				if(isMin) {
					rangeMin[node] = ret;
				} else {
					rangeMax[node] = ret;
				}
				return ret;
			}

			int mid = (left+right)/2;
			int leftVal = init(arr,left,mid,node*2,isMin);
			int rightVal = init(arr,mid+1,right,node*2+1,isMin);
			int ret = leftVal > rightVal ? rightVal:leftVal;
			if(isMin) {
				rangeMin[node] = ret;
			} else {
				rangeMax[node] = ret;
			}

			return ret;
		}

		int query(int left, int right, boolean isMin) {
			return query(left, right,1,0,n-1,isMin);
		}

		private int query(int left, int right, int node, 
				int nodeLeft, int nodeRight, boolean isMin) {
			if(right < nodeLeft || nodeRight < left) {
				return Integer.MAX_VALUE;
			}

			if(left <= nodeLeft && nodeRight <= right) {
				return isMin?rangeMin[node]:rangeMax[node];
			}

			int mid = (nodeLeft+nodeRight)/2;
			int leftVal = query(left,right,node*2,nodeLeft,mid,isMin);
			int rightVal = query(left,right,node*2+1,mid+1,nodeRight,isMin);
			return leftVal > rightVal ? rightVal:leftVal;
		}
	}

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				int[] info = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				int n = info[0];
				int q = info[1];

				RMQ rmq = new RMQ(
						Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray());

				for(int i = 0; i < q; i++) {
					int[] path = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
					//구간값이 작은것을 시작값으로 해야함
					if(path[0] > path[1]) {
						int tmp = path[0];
						path[0] = path[1];
						path[1] = tmp;
					}

					int max = rmq.query(path[0], path[1], false);
					int min = rmq.query(path[0], path[1], true);
					System.out.println(-max-min);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
