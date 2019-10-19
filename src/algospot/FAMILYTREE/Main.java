package algospot.FAMILYTREE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see https://algospot.com/judge/problem/read/FAMILYTREE
 * @author jun
 * input
1
13 5
0 1 1 3 3 0 6 0 8 9 9 8
2 7
10 11
4 11
7 7
10 0

 * output
4
2
6
0
3
 */
public class Main {
	static int serial;
	static int[] serialToNo, noToSerial, depth, locInTrip;
	
	static class RMQ {
		int n;
		int[] rangeMin;
		RMQ(Integer[] arr) {
			n = arr.length;
			rangeMin = new int[n*4];
			init(1,0,n-1,arr);
		}
		
		private int init(int node, int left, int right, Integer[] arr) {
			if(left == right) {
				rangeMin[node] = arr[left];
				return rangeMin[node]; 
			}
			
			int mid = (left+right)/2;
			int leftVal = init(node*2,left,mid,arr);
			int rightVal = init(node*2+1,mid+1,right,arr);
			rangeMin[node] = leftVal > rightVal ? rightVal : leftVal;
			return rangeMin[node]; 
		}
		
		int query(int left, int right) {
			return query(left, right, 0, n-1, 1);
		}

		private int query(int left, int right, int nodeLeft, int nodeRight, int node) {
			if(right < nodeLeft || nodeRight  < left) {
				return Integer.MAX_VALUE;
			}
			
			if(left <= nodeLeft && nodeRight <= right) {
				return rangeMin[node];
			}
			
			int mid = (nodeLeft+nodeRight)/2;
			int leftVal = query(left,right,nodeLeft,mid,node*2);
			int rightVal = query(left,right,mid+1,nodeRight,node*2+1);
			return leftVal > rightVal ? rightVal : leftVal;
		}
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t< tc; t++) {
				int[] info = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				int n = info[0];
				int q = info[1];
				
				List<List<Integer>> familyArr = new ArrayList<>();
				for(int i = 0; i < n; i++) {
					familyArr.add(new ArrayList<>());
				}
				
				int[] fatherArr = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
				for(int i = 0; i < fatherArr.length; i++) {
					familyArr.get(fatherArr[i]).add(i+1);
				}
				
				serial = 0;
				serialToNo = new int[n];
				noToSerial = new int[n];
				depth = new int[n];
				locInTrip = new int[n];
				List<Integer> trip = new ArrayList<>();
				traversal(0,0,familyArr,trip);
				RMQ rmq = new RMQ(trip.toArray(new Integer[trip.size()]));
				
				StringBuffer sb = new StringBuffer();
				for(int i = 0; i < q; i++) {
					int[] arr = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
					int aloc = locInTrip[arr[0]];
					int bloc = locInTrip[arr[1]];
					if(aloc > bloc) {
						int tmp = bloc;
						bloc = aloc;
						aloc = tmp;
					}
					
					int leastAncestorSerial = rmq.query(aloc, bloc);
					int ret = depth[arr[0]]+depth[arr[1]]-2*depth[serialToNo[leastAncestorSerial]];
					sb.append(ret+"\n");
				}
				System.out.println(sb.toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	private static void traversal(int here, int d, 
			List<List<Integer>> familyArr, List<Integer> trip) {
		noToSerial[here] = serial;
		serialToNo[serial] = here;
		serial++;
		depth[here] = d;
		
		locInTrip[here] = trip.size();
		trip.add(noToSerial[here]);
		for(int next : familyArr.get(here)) {
			traversal(next, d+1, familyArr, trip);
			trip.add(noToSerial[here]);
		}
	}

}
