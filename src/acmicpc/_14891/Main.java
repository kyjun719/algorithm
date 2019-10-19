package acmicpc._14891;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://www.acmicpc.net/problem/14891
 * @author jun
 *
 */
public class Main {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] gear = new String[5];
			for(int i = 1; i <= 4; i++) {
				gear[i] = br.readLine();
			}
			
			int n = Integer.parseInt(br.readLine());
			for(int i = 0; i < n; i++) {
				int[] info = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				int[] turns = new int[5];
				checkTurnedGear(turns, gear, info);
				turnGear(turns, gear);
			}
			int answer = 0;
			for(int i = 1; i <= 4; i++) {
				if(gear[i].charAt(0) == '1') {
					answer += (1<<(i-1));
				}
			}
			System.out.println(answer);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void showGear(String[] gear) {
		for(int i = 1; i <= 4; i++) {
			System.out.println(gear[i]);
		}
		System.out.println("=====================");
	}

	private static void checkTurnedGear(int[] turns, String[] gear, int[] info) {
		turns[info[0]] = info[1];
		boolean rightEnd = false;
		boolean leftEnd = false;
		int leftCnt = info[0];
		int rightCnt = info[0];
		while(!(rightEnd && leftEnd)) {
			if(leftCnt == rightCnt) {
				leftCnt--;
				rightCnt++;
			} else {
				if(!leftEnd) {
					if(gear[leftCnt].charAt(2) == gear[leftCnt+1].charAt(6)) {
						leftEnd = true;
					} else {
						turns[leftCnt] = -1*turns[leftCnt+1];
						leftCnt--;
					}
				}
				
				if(!rightEnd) {
					if(gear[rightCnt-1].charAt(2) == gear[rightCnt].charAt(6)) {
						rightEnd = true;
					} else {
						turns[rightCnt] = -1*turns[rightCnt-1];
						rightCnt++;
					}
				}
			}
			//2,6
			if(leftCnt < 1) {
				leftEnd = true;
			}
			if(rightCnt > 4) {
				rightEnd = true;
			}
		}
	}

	private static void turnGear(int[] turns, String[] gear) {
		// TODO Auto-generated method stub
		int size = gear[1].length();
		for(int i = 1; i <= 4; i++) {
			if(turns[i] == 1) {
				gear[i] = gear[i].charAt(size-1)+gear[i].substring(0,size-1);
				continue;
			}
			if(turns[i] == -1) {
				gear[i] = gear[i].substring(1,size)+gear[i].charAt(0);
				continue;
			}
		}
	}
}
