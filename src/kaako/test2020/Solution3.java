package kaako.test2020;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
3 3
0 0 0
1 0 0
0 1 1
1 1 1
1 1 0
1 0 1

true

3 2
0 1
1 0
1 1 1
1 1 0
1 0 1
true

3 2
1 0
0 1
0 1 1
1 0 1
1 1 1
 */
public class Solution3 {
	///*
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			int n = info[0];
			int m = info[1];
			int[][] key = new int[m][m];
			int[][] lock = new int[n][n];
			for(int i = 0; i < m; i++) {
				key[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			for(int i = 0; i < n; i++) {
				lock[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			System.out.println(String.valueOf(new Solution3().solution(key, lock)));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//*/
	
	public boolean solution(int[][] key, int[][] lock) {
		boolean answer = false;
		List<Integer[]> newLock = getLockArea(lock);
		for(Integer[] tmp:newLock) {
			System.out.println(Arrays.toString(tmp));
		}
		for(int i = 0; i < 4; i++) {			
			answer = canOpen(key, newLock);
			if(answer) {
				break;
			}
			//get rotated key
			key = rotateKey(key);
		}
		return answer;
	}
	
	private List<Integer[]> getLockArea(int[][] lock) {
		List<Integer[]> point = new ArrayList<>();
		int initx=-1,inity=-1;
		for(int y = 0; y < lock.length; y++) {
			for(int x = 0; x < lock[0].length; x++) {
				if(lock[y][x] == 0) {
					point.add(new Integer[] {y,x});
					if(initx == -1 && inity== -1) {
                    initx = x;
                    inity = y;
                    }
				}
			}
		}
		
		List<Integer[]> newLock = new ArrayList<>();
		for(Integer[] ptr:point) {
			newLock.add(new Integer[] {ptr[0]-inity, ptr[1]-initx});
		}
		// TODO Auto-generated method stub
		return newLock;
	}
	
	private boolean canOpen(int[][] key, List<Integer[]> newLock) {
		// TODO Auto-generated method stub
		for(int y = 0; y < key.length; y++) {
			for(int x = 0; x < key[0].length; x++) {
				if(isMatch(key,y,x, newLock)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isMatch(int[][] key, int y, int x, List<Integer[]> newLock) {
		for(Integer[] lock : newLock) {
			int matchy = y + lock[0];
			int matchx = x + lock[1];
			if(!isInBound(key.length, matchy, matchx)) {
				return false;
			}
			if(key[matchy][matchx] == 0) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isInBound(int n, int y, int x) {
		return (y >= 0) && (y < n) && (x >= 0) && (x < n);
	}
	
	private int[][] rotateKey(int[][] key) {
		int n = key.length;
		int[][] newKey = new int[n][n];
		for(int y=0; y < n; y++) {
			for(int x = 0; x < n; x++) {
				newKey[y][x] = key[n-x-1][y];
			}
		}
		return newKey;
	}
}
