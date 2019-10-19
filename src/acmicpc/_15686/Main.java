package acmicpc._15686;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @see https://www.acmicpc.net/problem/15686
 * @author jun
 *
 */
public class Main {
	static class Point {
		int x, y, cnt=0;
		Point(int y, int x) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return "("+x+","+y+")";
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof Point) {
				Point obj = (Point) o;
				return (this.x == obj.x) && (this.y == obj.y);
			}
			
			return false;
		}
		
		@Override
		public int hashCode() {
			int ret = 17;
			ret = ret*33+x;
			ret = ret*33+y;
			return ret;
		}
	}
	
	private static int m;
	private static ArrayList<Point> homeList, chickenList;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			int n = info[0];
			m = info[1];
			
			homeList = new ArrayList<>();
			chickenList = new ArrayList<>();
			for(int i = 0; i < n; i++) {
				int[] tmp = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				for(int j = 0; j < n; j++) {
					if(tmp[j] == 1) {
						homeList.add(new Point(i, j));
					} else if(tmp[j] == 2) {
						chickenList.add(new Point(i, j));
					}
				}
			}
			
			System.out.println(solve(0, 0, new boolean[chickenList.size()]));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static int solve(int idx, int selected, boolean[] chickenSel) {
		if(selected == m) {
			return calMinDist(chickenSel);
		}
		
		if(idx >= chickenSel.length) {
			return 987654321;
		}
		
		int ret = 987654321;
		chickenSel[idx] = true;
		ret = Math.min(ret, solve(idx+1, selected+1, chickenSel));
		chickenSel[idx] = false;
		ret = Math.min(ret, solve(idx+1, selected, chickenSel));
		
		return ret;
	}
	
	private static int calMinDist(boolean[] chickenSel) {
		int[] distArr = new int[homeList.size()];
		Arrays.fill(distArr, 987654321);
		for(int i = 0; i < chickenSel.length; i++) {
			if(!chickenSel[i]) {
				continue;
			}
			
			Point chicken = chickenList.get(i);
			for(int j = 0; j < homeList.size(); j++) {
				Point home = homeList.get(j);
				int tmpDist = Math.abs(home.x-chicken.x)+Math.abs(home.y-chicken.y);
				distArr[j] = Math.min(distArr[j], tmpDist);
			}
		}
		return Arrays.stream(distArr).sum();
	}
}
