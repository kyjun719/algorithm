package algospot.LUNCHBOX;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
