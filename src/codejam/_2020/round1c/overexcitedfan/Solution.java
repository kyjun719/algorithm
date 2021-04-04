package codejam._2020.round1c.overexcitedfan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static class Point {
		int x,y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int hashCode() {
			int ret = 31;
			ret = ret*17+x;
			ret = ret*17+y;
			return ret;
		}
		@Override
		public boolean equals(Object o) {
			if(o instanceof Point) {
				return ((Point) o).x == this.x &&
						((Point) o).y == this.y;
			}
			return false;
		}
	}
	
	static int toPoint(int pos) {
		return pos+2000;
	}
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 1; t <= tc; t++) {
				String[] info = br.readLine().split(" ");
				int x = Integer.parseInt(info[0]);
				int y = Integer.parseInt(info[1]);
				int cnt = info[2].length();
				
				int[][] cntArr = new int[4001][4001];
				boolean[][] visited = new boolean[4001][4001];
				visited[toPoint(x)][toPoint(y)] = true;
				
				Queue<Point> q = new LinkedList<>();
				int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
				q.add(new Point(0,0));
				visited[toPoint(0)][toPoint(0)] = true;
				
				int moveCnt = cnt+1;
				if(x == 0 && y == 0) {
					moveCnt = 0;
				}
				
				for(int i = 0; i < cnt; i++) {
					char moveDir = info[2].charAt(i);
					switch(moveDir) {
						case 'S':
							y--;
							break;
						case 'N':
							y++;
							break;
						case 'E':
							x++;
							break;
						case 'W':
							x--;
							break;
					}
					cntArr[toPoint(x)][toPoint(y)] = i+1;
					visited[toPoint(x)][toPoint(y)] = true;
					if(x == 0 && y == 0) {
						moveCnt = i+1;
					}
				}
				
				while(!q.isEmpty()) {
					Point p = q.poll();
					//System.out.println(p.x+","+p.y);
					
					int nx = p.x;
					int ny = p.y;
					for(int i = 0; i < 4; i++) {
						Point next = new Point(nx+dir[i][0], ny+dir[i][1]);
						if(cntArr[toPoint(next.x)][toPoint(next.y)] > 0) {
							int pu = cntArr[toPoint(next.x)][toPoint(next.y)];
							int now = cntArr[toPoint(nx)][toPoint(ny)]*-1;
							if(pu >= now+1) {
								moveCnt = Math.min(moveCnt, pu);
							}
							//System.out.println("visited!!::"+moveCnt);
							break;
						} else {
							if(!visited[toPoint(next.x)][toPoint(next.y)]) {
								visited[toPoint(next.x)][toPoint(next.y)] = true;
								
								int tmpCnt = cntArr[toPoint(nx)][toPoint(ny)];
								cntArr[toPoint(next.x)][toPoint(next.y)] = --tmpCnt;
								if(Math.abs(tmpCnt) <= cnt) {
									q.add(next);
								}
								//System.out.println("next::"+next.x+","+next.y+">>"+tmpCnt);
							}
						}
					}
					//System.out.println("===============");
					/*
					if(moveCnt <= cnt) {
						break;
					}
					*/
				}
				
				if(moveCnt == cnt+1) {
					System.out.println("Case #"+t+": IMPOSSIBLE");
				} else {
					System.out.println("Case #"+t+": "+moveCnt);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
