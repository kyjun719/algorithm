package acmicpc._15685;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @see https://www.acmicpc.net/problem/15685
 * @author jun
 *
 */
public class Main {
	static class Point {
		int x,y;
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
			ret = 31*ret + x;
			ret = 31*ret + y;
			return ret;
		}
	}
	
	private static int[][] direction = {{0,1},{-1,0},{0,-1},{1,0}};
	public static ArrayList<Point> calDragon(int y, int x, int dir, int g) {
		ArrayList<Point> dragon = new ArrayList<>();
		dragon.add(new Point(y,x));
		dragon.add(new Point(y+direction[dir][0],x+direction[dir][1]));
		
		while(g > 0) {
			g--;
			ArrayList<Point> nextDragon = new ArrayList<>(dragon);
			Point ptr = dragon.get(nextDragon.size()-1);
			for(int i = dragon.size()-1; i > 0; i--) {
				Point start = dragon.get(i);
				Point end = dragon.get(i-1);
				int delY = start.y-end.y;
				int delX = start.x-end.x;
				Point nextPtr = new Point(ptr.y-delX,ptr.x+delY);
				nextDragon.add(nextPtr);
				ptr = nextPtr;
			}
			dragon = nextDragon;
		}
		
		ArrayList<Point> ret = new ArrayList<>();
		for(Point ptr : dragon) {
			if((ptr.y >= 0) && (ptr.x >= 0)) {
				ret.add(ptr);
			}
		}

		return ret;
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(br.readLine());
			Set<Point> pointSet = new HashSet<>();
			for(int i = 0; i < n; i++) {
				int[] info = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				pointSet.addAll(calDragon(info[1], info[0], info[2], info[3]));
			}
			
			System.out.println(countPoint(pointSet));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static int countPoint(Set<Point> pointSet) {
		// TODO Auto-generated method stub
		int ret = 0;
		
		for(Point ptr : pointSet) {
			int cnt = 0;
			if(pointSet.contains(new Point(ptr.y+1, ptr.x))) {
				cnt++;
			}
			if(pointSet.contains(new Point(ptr.y+1, ptr.x+1))) {
				cnt++;
			}
			if(pointSet.contains(new Point(ptr.y, ptr.x+1))) {
				cnt++;
			}
			if(cnt == 3) {
				ret++;
			}
		}
		
		return ret;
	}
}
