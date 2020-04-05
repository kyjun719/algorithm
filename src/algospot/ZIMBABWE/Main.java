package algospot.ZIMBABWE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	static int n, m;
	static int[] nowPrice;
	static int[] number;
	static int[][][] cache = new int[2][20][1<<14];
	static int MOD = 1000000007;
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int c;
		try {
			c = Integer.parseInt(bf.readLine());
			for(int tc = 0; tc < c; tc++) {
				String[] tmp = bf.readLine().split(" ");
				//현재가격의 정보 저장
				nowPrice = tmp[0].chars().map(item -> item-48).toArray();
				number = nowPrice.clone();
				Arrays.sort(number);
				m = Integer.parseInt(tmp[1]);
				for(int i = 0; i < m; i++) {
					Arrays.fill(cache[0][i], -1);
					Arrays.fill(cache[1][i], -1);
				}
				
				n = nowPrice.length;
				System.out.println(zimbabwe(0, 0, 0, 0));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static int zimbabwe(int index, int used, int mod, int less) {
		//자릿수를 다 사용하였을 때 사탕갯수로 나눈 나머지가 0이고 현재 가격보다 낮은 경우 확인
		if(index == n) {
			return mod == 0 && less == 1? 1 : 0;
		}
		
		if(cache[less][mod][used] != -1) {
			return cache[less][mod][used];
		}
		
		int ret = 0;
		for(int next = 0; next < n; next++) {
			if((used & (1<<next)) == 0) {
				//현재 숫자가 과거의 숫자보다 작지 않고 다음 숫자의 크기가 현재 숫자보다 커지는경
				if(less == 0 && nowPrice[index] < number[next]) {
					continue;
				}
				
				//해당 자릿수의 숫자를 중복해서 사용하는 경우
				if(next > 0 && number[next-1] == number[next] && (used & (1<<(next-1))) == 0) {
					continue;
				}
				
				//사용한 자릿수 계산
				int nextUsed = used | 1<<next;
				//사탕으로 나눈 나머지값 계산
				int nextMod = (mod * 10 + number[next]) % m;
				//다음 계산할 가격이 현재 가격보다 큰지 여부 계산
				int nextLess = less | (nowPrice[index] > number[next] == true? 1:0);
				//다음 가능한 숫자의 갯수 계산
				ret += zimbabwe(index + 1, nextUsed, nextMod, nextLess);
				ret %= MOD;
			}
		}
		
		cache[less][mod][used] = ret;
		return ret;
	}
}
