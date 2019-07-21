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
public class Main {
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			for(int tc = 0; tc < c; tc++) {
				int n = Integer.parseInt(bf.readLine());
				int[] m = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				int[] e = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				List<Integer[]> eList = new ArrayList<>();
				for(int i = 0; i < n; i++) {
					eList.add(new Integer[] {i,e[i]});
				}
				eList.sort(new Comparator<Integer[]>() {
					@Override
					public int compare(Integer[] o1, Integer[] o2) {
						// TODO Auto-generated method stub
						return o2[1] - o1[1];
					}
				});

				int eatBegin = 0;
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
