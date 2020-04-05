package algospot.NUMBERGAME;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] arr;
	static int[][] cache = new int[50][50];
	static int MIN = -987654321;
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			for(int tc = 0; tc < c; tc++) {
				int n = Integer.parseInt(bf.readLine());
				for(int i = 0; i < n; i++) {
					Arrays.fill(cache[i], MIN);
				}
				arr = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				System.out.println(game(0, arr.length-1));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int game(int left, int right) {
		//숫자를 다 뽑았을 때 0 반환
		if(left > right) {
			 return 0;
		}
		
		if(cache[left][right] != MIN) {
			 return cache[left][right];
		}
		
		//좌우 하나씩 뽑았을 때 최대 점수
		int ret = MIN;
		ret = Math.max(arr[left] - game(left+1, right), arr[right] - game(left, right - 1));
		
		//양쪽 2개를 지울수 있는 경우 지웠을때의 점수
		if(right - left + 1 >= 2) {
			ret = Math.max(ret, -game(left, right-2));
			ret = Math.max(ret, -game(left+2, right));
		}
		cache[left][right] = ret;
		return ret;
	}
}
