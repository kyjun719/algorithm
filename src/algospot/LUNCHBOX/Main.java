package algospot.LUNCHBOX;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @see https://algospot.com/judge/problem/read/LUNCHBOX
 * @author jun
 * input
2
3
2 2 2
2 2 2
3
1 2 3
1 2 1

 * output
8
7
 */
/*
 * 1) 데우는 시간 : m1+m2<m3, 먹는 시간 : e1=e2=e3
 * -> m1+m2+m3+e3 ? m1+m3+m2+e2 => 동일
 * 2) 데우는 시간 : m1=m2=m3, 먹는 시간 : e1+e2<e3
 * -> m1+m2+m3+e3 ? m1+m3+m2+e2 =>
 * 2.1) e3 > m2+e2 => m1+m2+m3+e3 > (m1+m3+e3 > m1+m3+m2+e2)
 * 2.2) e3 < m2+e2 => e3 > e2이므로 m1+m2+m3+e3 > m1+m3+m2+e2
 * 3) 데우는 시간 : m1+m2<m3, 먹는 시간 : e1+e2<e3
 *  -> m1+m2+m3+e3 ? m1+m3+m2+e2 =>
 * 3.1) e3 > m2+e2 => m1+m2+m3+e3> (m1+m3+e3 > m1+m3+m2+e2)
 * 3.2) e3 < m2+e2 => e3 > e2이므로 m1+m2+m3+e3 > m1+m3+m2+e2
 * 따라서 먹는 시간이 길수록 먼저 먹어야 이득
 */
public class Main {
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			for(int tc = 0; tc < c; tc++) {
				//도시락 갯수
				int n = Integer.parseInt(bf.readLine());
				//데우는데 걸리는 시간
				int[] m = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				//먹는데 걸리는 시간
				int[] e = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				List<Integer[]> eList = new ArrayList<>();
				for(int i = 0; i < n; i++) {
					eList.add(new Integer[] {i,e[i]});
				}
				//먹는게 오래 걸리는 순서대로 정렬
				eList.sort(new Comparator<Integer[]>() {
					@Override
					public int compare(Integer[] o1, Integer[] o2) {
						// TODO Auto-generated method stub
						return o2[1] - o1[1];
					}
				});

				//먹기 시작한 시간
				int eatBegin = 0;
				//먹는데 오래걸리는 순서대로 데움, 탐욕법
				int ret = 0;
				for(int i = 0; i < n; i++) {
					int idx = eList.get(i)[0];
					eatBegin += m[idx];
					ret = Math.max(ret, eatBegin + eList.get(i)[1]);
				}
				System.out.println(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
