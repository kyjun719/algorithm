package algospot.KLIS;

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
public class Main {
	//전체 배열 길이
	static int n;
	//최대 증가 부분 수열의 인덱스
	static long k;
	//전체 배열
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
	
	//start에서 시작하는 배열의 최대길이 반환
	static int countLen(int start) {
		if(start != -1 && cacheLen[start] != -1) {
			return cacheLen[start];
		}
		
		int ret = 1;
		for(int next = start+1; next < n; next++) {
			//처음 시작 이거나 다음 배열값이 현재 배열값보다 큰 경우  
			if(start == -1 || arr[start] < arr[next]) {
				ret = Math.max(ret, countLen(next) + 1);
			}
		}
		
		if(start != -1) {
			cacheLen[start] = ret;
		}
		return ret;
	}
	
	//start에서 시작하는 최대 길이의 배열 갯수 반환
	static long count(int start) {
		if(countLen(start) == 1) {
			return 1;
		}
		
		if(start != -1 && cacheCnt[start] != -1) {
			return cacheCnt[start];
		}
		
		long ret = 0;
		for(int next = start+1; next < n; next++) {
			//처음시작 또는 다음 배열값이 현재값보다 크고
			//현재 배열길이가 다음 배열값의 길이 +1(배열길이 값이 증가)한 경우
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
	
	//배열의 시작 인덱스, 넘어가야할 갯수로 list에 출력값 반환
	static void find(int start, long skip, List<Integer> list) {
		//초기 시작이 아닌경우 리스트에 값 저장
		if(start != -1) {
			list.add(arr[start]);
		}
		
		//key : 다음 배열의 값, value : 다음 배열의 인덱스
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int next = start+1; next < n; next++) {
			//처음시작 또는 다음 배열값이 현재값보다 크고
			//현재 배열길이가 다음 배열값의 길이 +1(배열길이 값이 증가)한 경우
			if((start == -1 || arr[start] < arr[next]) && 
					(countLen(start) == countLen(next) + 1)) {
				map.put(arr[next], next);
			}
		}
		
		//다음배열의 값 정렬
		Integer[] itemArr = map.keySet().toArray(new Integer[map.keySet().size()]);
		Arrays.sort(itemArr);
		
		for(int item : itemArr) {
			long cnt = count(map.get(item));
			//최대 배열의 갯수가 넘어가야 할 갯수보다 작은경우
			//넘어가야 할 갯수에서 빼고 다음 값 확인
			if(cnt < skip) {
				skip -= cnt;
			} else {
				find(map.get(item), skip, list);
				break;
			}
		}
	}
}
