package algospot.NERD2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @see https://algospot.com/judge/problem/read/NERD2
 * @author jun
 * input
2
4
72 50
57 67
74 55
64 60
5
1 5
2 4
3 3
4 2
5 1

 * output
8
15
 */
public class Main {
	private static TreeMap<Integer, Integer> pointMap;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				int n = Integer.parseInt(br.readLine());
				pointMap = new TreeMap<>();
				int ret = 0;
				for(int i = 0; i < n; i++) {
					int[] info = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
					ret += register(info[0], info[1]);
				}
				System.out.println(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int register(int p, int q) {
		if(isDominated(p, q)) {
			return pointMap.size();
		}		
		
		removeDominated(p, q);
		
		pointMap.put(p, q);
		return pointMap.size();
	}

	//(p,q)가 지배당하는지 확인
	private static boolean isDominated(int p, int q) {
		//map에 키가 p보다 큰값중 가장 작은값의 키값쌍을 찾음
		Entry<Integer, Integer> leftEntry = pointMap.ceilingEntry(p);
		if(leftEntry == null) {
			return false;
		}
		
		//키값쌍의 값이 q보다 큰경우 지배당함, (p,q)는 맵에서 제외
		return leftEntry.getValue() > q;
	}
	
	private static void removeDominated(int p, int q) {
		//키값이 p보다 작은 키중 가장 큰값 검색
		Integer key = pointMap.floorKey(p);
		while(key != null) {
			//(p,q)에 지배당하지 않는다면 바로 종료
			if(pointMap.get(key) > q) {
				return;
			}
			//(p,q)에 지배당하면 삭제
			pointMap.remove(key);
			//키값이 삭제한 키보다 작은 키중 가장 큰값 검색
			key = pointMap.floorKey(key);
		}
	}
}
