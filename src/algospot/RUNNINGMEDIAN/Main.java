package algospot.RUNNINGMEDIAN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @see https://algospot.com/judge/problem/read/RUNNINGMEDIAN
 * @author jun
 * input
3
10 1 0
10 1 1
10000 1273 4936

 * output
19830 
19850 
2448920
 */
public class Main {
	static int MOD = 20090711;
	
	//0~중간값 내림차순
	static PriorityQueue<Integer> maxQueue;
	//중간값~끝 오름차순
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
				maxQueue = new PriorityQueue<>(n/2, new Comparator<Integer>() {
					@Override
					public int compare(Integer arg0, Integer arg1) {
						return arg1-arg0;
					}
				});
				minQueue = new PriorityQueue<>(n/2);
				
				long ret = 0;
				for(int i = 0; i<n; i++) {
					if(maxQueue.size() == minQueue.size()) {
						maxQueue.add(num.next());
					} else {
						minQueue.add(num.next());
					}
					
					if(!maxQueue.isEmpty() && !minQueue.isEmpty() &&
							maxQueue.peek() > minQueue.peek()) {
						int val1 = maxQueue.poll();
						int val2 = minQueue.poll();
						maxQueue.add(val2);
						minQueue.add(val1);
					}
					//System.out.println(maxQueue);
					//System.out.println(minQueue);
					
					ret = (ret + maxQueue.peek())%MOD;
				}
				
				System.out.println(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
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
