package algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * @see https://algospot.com/judge/problem/read/KLIS
 * @author jun
 * input
3
9 2
1 9 7 4 2 6 3 11 10
8 4
2 1 4 3 6 5 8 7
8 2
5 6 7 8 1 2 3 4

 * output
4
1 2 3 11
4
1 3 6 8
4
5 6 7 8
 */
public class KLIS {
	static int n;
	static long k;
	static int[] arr;
	static int[] cacheLen = new int[501];
	static long[] cacheCnt = new long[501];
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int c;
		try {
			c = Integer.parseInt(bf.readLine());
			
			for(int tc = 0; tc < c; tc++) {
				String[] tmp = bf.readLine().split(" ");
				n = Integer.parseInt(tmp[0]);
				k = Integer.parseInt(tmp[1]);
				arr = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				Arrays.fill(cacheLen, -1);
				Arrays.fill(cacheCnt, -1);
				
				List<Integer> outList = new ArrayList<>();
				find(-1, k, outList);
				System.out.println(outList.size());
				
				String answer = outList.toString().replace("[", "");
				answer = answer.replace("]", "");
				answer = answer.replace(",", "");
				System.out.println(answer);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static int countLen(int start) {
		if(start != -1 && cacheLen[start] != -1) {
			return cacheLen[start];
		}
		
		int ret = 1;
		for(int next = start+1; next < n; next++) {
			if(start == -1 || arr[start] < arr[next]) {
				ret = Math.max(ret, countLen(next) + 1);
			}
		}
		
		if(start != -1) {
			cacheLen[start] = ret;
		}
		return ret;
	}
	
	static long count(int start) {
		if(countLen(start) == 1) {
			return 1;
		}
		
		if(start != -1 && cacheCnt[start] != -1) {
			return cacheCnt[start];
		}
		
		long ret = 0;
		for(int next = start+1; next < n; next++) {
			if((start == -1 || arr[start] < arr[next]) && 
					(countLen(start) == countLen(next) + 1)) {
				//check to overflow
				ret = Math.min(Integer.MAX_VALUE, ret + count(next));
			}
		}
		
		if(start != -1) {
			cacheCnt[start] = ret;
		}
		return ret;
	}
	
	static void find(int start, long skip, List<Integer> list) {		
		if(start != -1) {
			list.add(arr[start]);
		}
		
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int next = start+1; next < n; next++) {
			if((start == -1 || arr[start] < arr[next]) && 
					(countLen(start) == countLen(next) + 1)) {
				map.put(arr[next], next);
			}
		}
		
		Integer[] itemArr = map.keySet().toArray(new Integer[map.keySet().size()]);
		Arrays.sort(itemArr);
		
		for(int item : itemArr) {
			long cnt = count(map.get(item));
			if(cnt < skip) {
				skip -= cnt;
			} else {
				find(map.get(item), skip, list);
				break;
			}
		}
	}
}
