package algospot.ITES;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @see https://algospot.com/judge/problem/read/ITES
 * @author jun
 * input
3
8791 20
5265 5000
3578452 5000000

 * output
1
4
1049
 */
public class Main {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t<tc; t++) {
				int[] info = Arrays.stream(br.readLine().split(" " ))
						.mapToInt(Integer::parseInt)
						.toArray();
				int k = info[0];
				int n = info[1];
				seed = 1983;
				System.out.println(solve(k, n));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static int solve(int k, int n) {
		//구간의 합이 k인 갯수
		int ret = 0;
		//구간의 합
		int retSum = 0;
		//구간의 수 배열을 넣은 큐
		Queue<Integer> numberQueue = new LinkedList<>();
		for(int head = 0; head < n; head++) {
			//구간의 숫자 추가
			int next = getNext();
			retSum += next;
			numberQueue.add(next);
			
			//구간의 합이 k보다 작아질때 까지 구간의 배열감소
			while(retSum > k) {
				retSum -= numberQueue.poll();
			}
			
			//구간의 합이 k인경우 답 추가
			if(retSum == k) {
				ret++;
			}
		}
		
		return ret;
	}

	static long seed = 1983;
	//다음 수 생성
	private static int getNext() {
		long ret = seed;
		seed = (long) ((seed*(long)214013+(long)2531011)%Math.pow(2, 32));
		return (int)(ret%10000)+1;
	}
}
