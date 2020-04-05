package algospot.CANADATRIP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	//l : 시작점으로부터의 거리, m : 표지판 출력 시작 위치, g : 표지판간 거리
	static int[] l, m, g;
	//n : 도시 수, k : 출력할 표지판 수
	static int n, k;
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			for(int tc = 0; tc < c; tc++) {
				int[] tmp = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt).toArray();
				n = tmp[0];
				k = tmp[1];
				
				l = new int[n];
				m = new int[n];
				g = new int[n];
				for(int i = 0; i < n; i++) {
					int[] infoTmp = Arrays.stream(bf.readLine().split(" "))
							.mapToInt(Integer::parseInt).toArray();
					l[i] = infoTmp[0];
					m[i] = infoTmp[1];
					g[i] = infoTmp[2];
				}
				
				System.out.println(getDist());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static int getDist() {
		//표지판이 나올수 있는 최대 갯수는 8030000개
		int low = -1;
		int high = 8030001;
		for(int i = 0; i < 25; i++) {
			int dist = (low + high)/2;
			//dist에서 볼수있는 표지판이 k개 이상 인 경우 범위를 줄임 
			if(canShow(dist)) {
				high = dist;
			} else {
				low = dist;
			}
		}
		
		return high;
	}

	//모든 도시들을 돌면서 현재 거리에서 몇개의 표지판이 남아있는지 확인후 반환
	private static boolean canShow(int dist) {
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			//현재 위치에서 해당 도시의 표지판을 볼 수 있는경우
			if(dist >= l[i] - m[i]) {
				cnt += (Math.min(dist, l[i]) - (l[i] - m[i]))/g[i] + 1;
			}
		}
		
		return cnt >= k;
	}
}
