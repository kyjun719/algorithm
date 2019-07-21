package algospot.MINASTIRITH;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @see https://algospot.com/judge/problem/read/MINASTIRITH
 * @author jun
 * input
3
10
7.02066050 -3.83540431 4.0
-7.23257714 -3.41903904 2.0
0.00000000 -8.00000000 8.0
-8.00000000 -0.00000000 4.8
-6.47213595 4.70228202 3.2
-4.70228202 6.47213595 4.8
7.60845213 -2.47213595 1.6
-2.47213595 -7.60845213 8.8
6.47213595 4.70228202 7.6
-0.00000000 8.00000000 4.8
4
8.00000000 0.00000000 8.00
0.00000000 -8.00000000 8.00
-8.00000000 -0.00000000 8.00
1.25147572 7.90150672 5.40
1
8 0 15.99

 * output
5
4
IMPOSSIBLE
 */
public class Main {
	static int n;
	static List<Double[]> rangeList;
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			for(int tc = 0; tc < c; tc++) {
				n = Integer.parseInt(bf.readLine());
				rangeList = new ArrayList<>();
				
				for(int i = 0; i < n; i++ ) {
					double[] tmp = Arrays.stream(bf.readLine().split(" "))
							.mapToDouble(Double::parseDouble)
							.toArray();
					double y = tmp[0];
					double x = tmp[1];
					double r = tmp[2];
					double loc = ((Math.atan2(y, x)+2*Math.PI)%(2*Math.PI))*180/Math.PI;
					double theta = 2*Math.asin(r/16)*180/Math.PI;
					rangeList.add(new Double[] {loc-theta, loc+theta});
				}
				rangeList.sort(new Comparator<Double[]>() {
					@Override
					public int compare(Double[] arg0, Double[] arg1) {
						// TODO Auto-generated method stub
						return (int) Math.round(arg0[0] - arg1[0]);
					}
				});
				
				int ret = 987654321;
				for(int i = 0; i < n; i++) {
					if(rangeList.get(i)[0] <= 0 || rangeList.get(i)[1] >= 360) {
						double start = rangeList.get(i)[1]%360;
						double end = (rangeList.get(i)[0]+360)%360;
						ret = Math.min(ret, 1+solve(start, end));
					}
				}
				System.out.println(ret != 987654321?ret : "IMPOSSIBLE");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int solve(double start, double end) {
		int idx = 0;
		int used = 0;
		while(start < end) {
			double newStart = -1;
			while(idx < n && rangeList.get(idx)[0] <= start) {
				newStart = Math.max(newStart, rangeList.get(idx)[1]);
				idx++;
			}
			if(newStart < start) {
				return 987654321;
			}
			start = newStart;
			used++;
		}
		return used;
	}
}
