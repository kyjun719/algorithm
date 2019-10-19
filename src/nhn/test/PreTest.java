package nhn.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PreTest {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			long[] info = Arrays.stream(br.readLine().split(" "))
					.mapToLong(Long::parseLong)
					.toArray();
			int n = (int)info[0];
			long w = info[1];
			String[][] board = new String[n][n];
			for(int i = 0; i < n; i++) {
				board[i] = br.readLine().split(" ");
			}
			
			//(0,0)~(0,n-1)~(n-1,n-1)~(n-1,0)~(0,0)
			//(1,1)~(1,n-2)~(n-2,n-2)~(n-2,1)~(1,1)
			//...
			int min = 0, max = n-1;
			boolean isClockWise = w > 0;
			if(w < 0) {
				w *= -1;
			}
			String[][] newBoard = new String[(int)n][(int)n];
			
			if(n%2 == 1) {
				newBoard[n/2][n/2] = board[n/2][n/2];
			}
			
			while(min < max) {
				int num = 4*(max-min);
				int cnt;
				
				if(isClockWise) {
					cnt = (int)(w%num);
				} else {
					cnt = (int)(num-(w%num));
				}
				
				//System.out.println(min+","+max+">>"+cnt);
				for(int i = 0; i < num; i++) {
					int[] newPtr = calClockPoint((cnt+i)%num, min, max);
					int[] beforePtr = calClockPoint(i, min, max);
					//System.out.println(Arrays.toString(newPtr)+"<<"+Arrays.toString(beforePtr)+"::"+i);
					newBoard[newPtr[0]][newPtr[1]] = board[beforePtr[0]][beforePtr[1]];
				}
				min++;
				max--;
				isClockWise = isClockWise?false:true;
			}

			for(int i = 0; i < n; i++) {
				String tmp = Arrays.toString(newBoard[i]);
				tmp = tmp.replace("[", "");
				tmp = tmp.replace("]", "");
				tmp = tmp.replace(",", "");
				System.out.println(tmp);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//(0,0)~(0,n-1)~(n-1,n-1)~(n-1,0)~(0,0)
	private static int[] calClockPoint(long i, int min, int max) {
		int len = max-min;
		int n = (int)(i/len);
		int m = (int)(i%len);
		if(n == 0) {
			return new int[] {min, min+m};
		}
		if(n == 1) {
			return new int[] {min+m, max};
		}
		if(n == 2) {
			return new int[] {max, max-m};
		}
		return new int[] {max-m, min};
	}
}
