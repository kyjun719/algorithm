package algospot.ARCTIC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * @see https://algospot.com/judge/problem/read/ARCTIC
 * @author jun
 * input
2
5
0 0
1 0
1 1
1 2
0 2
6
1.0 1.0
30.91 8
4.0 7.64
21.12 6.0
11.39 3.0
5.31 11.0

 * output
1.00
10.18
 */
public class Main {
	static class Point {
		double x;
		double y;
		
		Point() {
			this.x = -1;
			this.y = -1;
		}
		
		Point(double[] arr) {
			this.x = arr[0];
			this.y = arr[1];
		}
		
		@Override
		public String toString() {
			return "("+x+","+y+")";
		}
	}
	
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			
			for(int tc = 0; tc < c; tc++) {
				int m = Integer.parseInt(bf.readLine());
				double[][] distArr = new double[m][m];
				List<Point> pointList = new ArrayList<>();

				for(int i = 0; i < m; i++) {
					Point tmpPoint = new Point(Arrays.stream(bf.readLine().split(" "))
							.mapToDouble(Double::parseDouble)
							.toArray());
					
					for(int j = 0; j < i; j++) {
						double dist = getDistance(pointList.get(j), tmpPoint);
						distArr[j][i] = dist;
						distArr[i][j] = dist;
					}
					
					pointList.add(tmpPoint);
				}
				
				System.out.println(String.format("%.2f", getMinPower(distArr)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static double getMinPower(double[][] distArr) {
		double low = 0;
		double high = Math.sqrt(1000*1000+1000*1000);
		
		//Math.sqrt(1000*1000+1000*1000)/(2^25) < 0.001
		for(int i = 0; i < 25; i++) {
			double mid = (low + high) / 2;
			if(enableCall(distArr, mid)) {
				high = mid;
			} else {
				low = mid;
			}
		}
		
		return low;
	}
	
	static boolean enableCall(double[][] distArr, double range) {
		boolean[] connected = new boolean[distArr.length];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		connected[0] = true;
		int cnt = 1;
		while(!queue.isEmpty()) {
			int idx = queue.poll();
			for(int i = 0; i < distArr.length; i++) {
				if(i == idx) {
					continue;
				}
				if(connected[i] == false && distArr[idx][i] <= range) {
					queue.add(i);
					connected[i] = true;
					cnt++;
				}
			}
		}
		
		return cnt == distArr.length;
	}
	
	static double getDistance(Point p1, Point p2) {
		double x = p1.x - p2.x;
		double y = p1.y - p2.y;
		return Math.sqrt(x*x + y*y);
	}
}
