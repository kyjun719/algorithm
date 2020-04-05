package algospot.FAMILYTREE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	//배열에 들어가는 시리얼 정보
	static int serial;
	//시리얼 정보와 노드 번호 배열, 인덱스 : 시리얼 번호, 값 : 노드 번호
	static int[] serialToNo;
	//노드 번호와 시리얼번호 배열, 인덱스 : 노드 번호, 값 : 시리얼 번호
	static int[] noToSerial;
	//노드 번호와 해당 노드의 깊이 배열
	static int[] depth;
	//노드 번호와 순회 배열에서의 해당 노드의 시작 인덱스값
	static int[] locInTrip;
	
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
				//노드의 순회값, 노드 번호가 아닌 노드의 순서값으로 순회순서 저장되어 있음
				List<Integer> trip = new ArrayList<>();
				traversal(0,0,familyArr,trip);
				
				RMQ rmq = new RMQ(trip.toArray(new Integer[trip.size()]));
				
				StringBuffer sb = new StringBuffer();
				for(int i = 0; i < q; i++) {
					int[] arr = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
					//해당 노드의 순회 시작값 계산, 순회 시작값이 작은값부터 범위가 시작 되어야함
					int aloc = locInTrip[arr[0]];
					int bloc = locInTrip[arr[1]];
					if(aloc > bloc) {
						int tmp = bloc;
						bloc = aloc;
						aloc = tmp;
					}
					//rmq에 저장된 값은 순서값의 순회 순서값으로 저장되어 있음
					int leastAncestorSerial = rmq.query(aloc, bloc);
					//자손간 거리 = a노드의 깊이+b노드의 깊이-2*(a와 b노드의 가장 높이있는 부모의 깊이)
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
		//노드번호와 해당노드의 순서값 저장
		noToSerial[here] = serial;
		//해당노드의 순서값과 노드번호 저장
		serialToNo[serial] = here;
		//순서값 증가
		serial++;
		//해당노드의 깊이 저장
		depth[here] = d;
		
		//해당 노드의 순회 시작값은 지금까지 입력된 순회배열의 길이
		locInTrip[here] = trip.size();

		//해당 노드의 순서값을 순회배열에 추가
		trip.add(noToSerial[here]);
		for(int next : familyArr.get(here)) {
			//해당 노드의 자손들 순회
			traversal(next, d+1, familyArr, trip);
			//자손들의 순회가 끝났으므로 자기 자신의 순서값 추가
			trip.add(noToSerial[here]);
		}
	}

}
