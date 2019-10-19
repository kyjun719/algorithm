package acmicpc._16235;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @see https://www.acmicpc.net/problem/16235
 * @author jun
 *
 */
public class Main {
	static Deque<Integer>[][] tree;
	static int[][] add;
	static int[][] food;
	static int n;
	
	static class Point {
		int x,y;
		Point(int y, int x){
			this.y = y;
			this.x = x;
		}
		
		@Override
		public String toString() {
			return "("+y+","+x+")";
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof Point) {
				Point obj = (Point) o;
				return (obj.x == this.x) && (obj.y == this.y);
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			int ret = 17;
			ret = ret*31+x;
			ret = ret*31+y;
			return ret;
		}
	}
	
	static int[][] dir = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			n = info[0];
			int m = info[1];
			int k = info[2];
			
			tree = new ArrayDeque[n][n];
			add = new int[n][n];
			food = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					tree[i][j] = new ArrayDeque<Integer>();
				}
				
				add[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				
				Arrays.fill(food[i], 5);
			}
			
			HashSet<Point> treePointList = new HashSet<>();
			for(int i = 0; i < m; i++) {
				int[] tmp = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				tree[tmp[0]-1][tmp[1]-1].add(tmp[2]);
				treePointList.add(new Point(tmp[0]-1, tmp[1]-1));
			}
			
			while(k > 0 && treePointList.size() > 0) {
				//showTree();
				k--;
				
				HashSet<Point> nextPointList = new HashSet<>();
				for(Point ptr : treePointList) {
					//spring
					int y = ptr.y;
					int x = ptr.x;
					Deque<Integer> list = tree[y][x];
					Deque<Integer> nextList = new ArrayDeque<>();

					while(!list.isEmpty()) {
						int tree = list.peek();
						if(food[y][x] >= tree) {
							list.poll();
							food[y][x] -= tree;
							nextList.add(tree+1);
						} else {
							break;
						}
					}
					
					if(nextList.size() != 0) {
						nextPointList.add(ptr);
						tree[y][x] = nextList;
					} else {
						tree[y][x] = new ArrayDeque<>();
					}
					
					//summer
					while(!list.isEmpty()) {
						int tree = list.poll();
						food[y][x] += tree/2;
					}
				}
				treePointList = nextPointList;
				//showTree();
				
				//autumn
				int[][] newTreeCnt = new int[n][n];
				for(Point ptr : treePointList) {
					int y = ptr.y;
					int x = ptr.x;
					Deque<Integer> list = tree[y][x];
					Iterator<Integer> iter = list.iterator();
					while(iter.hasNext()) {
						int tree = iter.next();
						
						if(tree%5 == 0) {
							for(int i = 0; i < dir.length; i++) {
								int nextY = y+dir[i][0];
								int nextX = x+dir[i][1];
								if(isInBound(nextY, nextX)) {
									newTreeCnt[nextY][nextX]++;
								}
							}
						}
					}
				}
				
				//winter
				for(int y = 0; y < n; y++) {
					for(int x = 0; x < n; x++) {
						food[y][x] += add[y][x];
						if(newTreeCnt[y][x] != 0) {
							treePointList.add(new Point(y,x));
							int tmp = newTreeCnt[y][x];
							while(tmp > 0) {
								tmp--;
								tree[y][x].push(1);
							}
						}
					}
				}
			}
			
			//showTree();
			System.out.println(countTree(treePointList));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static int countTree(HashSet<Point> treePointList) {
		int cnt = 0;
		//System.out.println(treePointList);
		for(Point ptr : treePointList) {
			cnt += tree[ptr.y][ptr.x].size();
		}
		return cnt;
	}

	private static void showTree() {
		// TODO Auto-generated method stub
		for(int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(tree[i]));
		}
		System.out.println("============================");
	}

	private static boolean isInBound(int y, int x) {
		return (y >= 0) && (y < n) && (x >= 0) && (x < n);
	}
}
