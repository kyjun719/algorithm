package algospot.NUMB3RS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	//n:마을수, d:지난일수, p:교도소 마을번호(시작점)
	static int n,d,p;
	//마을 연결 여부
	static int[][] connected = new int[50][50];
	//deg[i]:i번째 마을과 연결된 마을 갯수
	static int[] deg = new int[50];
	static double[][] cache = new double[50][100];
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			
			for(int tc = 0; tc < c; tc++) {
				String[] tmp = bf.readLine().split(" ");
				n = Integer.parseInt(tmp[0]);
				d = Integer.parseInt(tmp[1]);
				p = Integer.parseInt(tmp[2]);
				for(int i = 0; i < n; i++) {
					Arrays.fill(cache[i], -1);
					int cnt = 0;
					String[] line = bf.readLine().split(" ");
					for(int j = 0; j < n; j++) {
						connected[i][j] = Integer.parseInt(line[j]);
						cnt += connected[i][j] == 1? 1:0;
					}
					deg[i] = cnt;
				}
				
				int t = Integer.parseInt(bf.readLine());
				String[] q_arr = bf.readLine().split(" ");
				for(int i = 1; i <= t; i++) {
					int q = Integer.parseInt(q_arr[i-1]);
					System.out.print(search(q, d));
					if(i != t) {
						System.out.print(" ");
					}
				}
				System.out.println();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static double search(int here, int days) {
		//요일이 0일일때 감옥에 위치했으면 1, 아니면 0 반환
		if(days == 0) {
			return here == p? 1:0;
		}
		
		//cache값 반환
		if(cache[here][days] != -1) {
			return cache[here][days];
		}
		
		double ret = 0;
		for(int there = 0; there < n; there++) {
			//연결되어있으면 도착할 확률 계산
			if(connected[here][there] == 1) {
				ret += search(there, days-1)/deg[there];
			}
		}
		cache[here][days] = ret;
		return ret;
	}
}
