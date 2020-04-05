package algospot.SNAIL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int n, m;
	static double[][] cache = new double[1001][1001];
	public static void main(String[] args) {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			int c = Integer.parseInt(bf.readLine());
			
			for(int tc = 0; tc < c; tc++) {
				String[] tmp = bf.readLine().split(" ");
				n = Integer.parseInt(tmp[0]);
				m = Integer.parseInt(tmp[1]);
				
				for(int i = 0; i <= n; i++) {
					Arrays.fill(cache[i], -1);
				}
				System.out.println(snail(0, 0));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static double snail(int days, int meter) {
		//끝까지 올라간 경우
		if(meter >= n) {
			return 1;
		}
		
		//해당 날짜가 지났을때 끝까지 올라갔는지 여부 반환
		if(days == m) {
			return meter >= n? 1 : 0;
		}
		
		if(cache[days][meter] != -1) {
			return cache[days][meter];
		}
		
		//비가 올 확률이 75%이므로 0.25*(+1미터)+0.75*(+2미터)로 끝까지 올라갈 확률 계산
		double ret = 0.25*snail(days+1, meter+1) + 0.75*snail(days+1, meter+2);
		cache[days][meter] = ret;
		
		return ret;
	}
}
