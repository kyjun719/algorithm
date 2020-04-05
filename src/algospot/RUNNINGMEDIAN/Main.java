package algospot.RUNNINGMEDIAN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	static int MOD = 20090711;
	
	//중간값보다 값이 작을것으로 예상되는 숫자들의 힙
	static PriorityQueue<Integer> maxQueue;
	//중간값보다 값이 클것으로 예상되는 숫자들의 힙
	static PriorityQueue<Integer> minQueue;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				int[] info = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				int n = info[0];
				int a = info[1];
				int b = info[2];
				NumGenerator num = new NumGenerator(a,b);
				//내림차순 정렬
				maxQueue = new PriorityQueue<>(n/2, new Comparator<Integer>() {
					@Override
					public int compare(Integer arg0, Integer arg1) {
						return arg1-arg0;
					}
				});
				//오름차순 정렬
				minQueue = new PriorityQueue<>(n/2);
				
				long ret = 0;
				for(int i = 0; i<n; i++) {
					//최대힙의 크기는 최소힙의 크기보다 같거나 하나 더 큼
					if(maxQueue.size() == minQueue.size()) {
						maxQueue.add(num.next());
					} else {
						minQueue.add(num.next());
					}
					
					//최대힙의 최대값과 최소힙의 최소값을 비교하여 작은 수를 최대힙에 넣음
					if(!maxQueue.isEmpty() && !minQueue.isEmpty() &&
							maxQueue.peek() > minQueue.peek()) {
						int val1 = maxQueue.poll();
						int val2 = minQueue.poll();
						maxQueue.add(val2);
						minQueue.add(val1);
					}
					
					ret = (ret + maxQueue.peek())%MOD;
				}
				
				System.out.println(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//숫자 생성기
	static class NumGenerator {
		int a,b;
		int seed = 1983;
		NumGenerator(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		int next() {
			int ret = seed;
			seed = (int) ((seed*(long)a+b)%MOD);
			return ret;
		}
	}
}
