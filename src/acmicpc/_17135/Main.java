package acmicpc._17135;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class Main {
	static int n,m,d;
	static int[][] arr;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			n = info[0];
			m = info[1];
			d = info[2];
			
			arr = new int[n+1][m];
			int cnt = 0;
			for(int i = 0; i < n; i++) {
				arr[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				for(int j = 0; j < m; j++) {
					if(arr[i][j] == 1) {
						cnt++;
					}
				}
			}
			boolean[] archer = new boolean[m];
			System.out.println(solve(0,0,archer,cnt));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve(int idx, int selected, boolean[] archer, int cnt) {
		if(selected == 3) {
			return killEmeny(archer, cnt);
		}
		
		if(idx == m) {
			return 0;
		}
		
		int ret = 0;
		for(int i = idx; i < m; i++) {
			archer[i] = true;
			ret = Math.max(ret, solve(i+1,selected+1,archer,cnt));
			archer[i] = false;
		}
		return ret;
	}
	
	private static class Point {
		int y,x;
		int cnt;
		Point(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof Point) {
				Point obj = (Point) o;
				return obj.y == y && obj.x == x;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			int ret = 17;
			ret = ret*31 + y;
			ret = ret*31 + x;
			return ret;
		}
		
		@Override
		public String toString() {
			return "("+y+","+x+")>>"+cnt;
		}
	}

	private static int killEmeny(boolean[] archer, int cnt) {
		int kill = 0;
		int left = cnt;
		int[][] copyArr = new int[n+1][m];
		for(int i = 0; i < n; i++) {
			copyArr[i] = Arrays.copyOf(arr[i], m);
		}
		
		ArrayList<Integer> archerPoint = new ArrayList<>();
		for(int i = 0; i < m; i++) {
			if(archer[i]) {
				copyArr[n][i] = 2;
				archerPoint.add(i);
			}
		}
		
		for(int i = 0; i < n; i++) {
			ArrayList<Point> pointList = new ArrayList<>();
			for(Integer col : archerPoint) {
				Point enemy = getEnemyPoint(copyArr,n,col,0);
				if(enemy == null) {
					continue;
				}
				
				pointList.add(enemy);
			}
			
			for(Point enemy : pointList) {
				if(copyArr[enemy.y][enemy.x] == 1) {
					kill++;
					left--;
					copyArr[enemy.y][enemy.x] = 0;
				}
			}
			
			left -= Arrays.stream(copyArr[n-1]).sum();
			
			if(left == 0) {
				break;
			}
			
			for(int j = n-1; j > 0; j--) {
				copyArr[j] = Arrays.copyOf(copyArr[j-1],m);
			}
			Arrays.fill(copyArr[0], 0);
		}
		return kill;
	}

	private static void showBoard(int[][] copyArr) {
		for(int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(copyArr[i]));
		}
		System.out.println("=======================");
	}

	private static Point getEnemyPoint(int[][] copyArr, int y, int x, int cnt) {
		if(cnt > d) {
			return null;
		}
		
		if(copyArr[y][x] == 1) {
			return new Point(y,x,cnt);
		}
		
		Point ptr = null;
		Point tmp = null;
		ArrayList<Point> ret = new ArrayList<>();
		
		if(isInBound(y,x+1)) {
			tmp = getEnemyPoint(copyArr,y,x+1,cnt+1);
			if(tmp != null) {
				ret.add(tmp);
			}
		}
		
		if(isInBound(y-1,x)) {
			tmp = getEnemyPoint(copyArr,y-1,x,cnt+1);
			if(tmp != null) {
				ret.add(tmp);
			}
		}
		
		if(isInBound(y,x-1)) {
			tmp = getEnemyPoint(copyArr,y,x-1,cnt+1);
			if(tmp != null) {
				ret.add(tmp);
			}
		}
		
		if(!ret.isEmpty()) {
			Collections.sort(ret, new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					// TODO Auto-generated method stub
					if(o1.cnt == o2.cnt) {
						return o1.x-o2.x;
					}
					return o1.cnt-o2.cnt;
				}
			});
			ptr = ret.get(0);
		}

		return ptr;
	}

	private static boolean isInBound(int y, int x) {
		return (y>=0)&&(y<n)&&(x>=0)&&(x<m);
	}
}
