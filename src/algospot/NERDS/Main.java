package algospot.NERDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @see https://algospot.com/judge/problem/read/NERDS
 * @author jun
 * input
3
8
1 2 3 
1 3 4
1 4 5
1 2 5
0 4 1
0 5 5
0 3 3
0 4 4
6
1 1 5
1 5 1
1 1 1
0 2 2
0 4 1
0 1 4
6
1 10 10
0 10 10
1 5 15
1 5 5
0 15 15
0 15 5

 * output
THEORY HOLDS
THEORY IS INVALID
THEORY IS INVALID
 */
public class Main {
	static class Vector {
		double x,y;
		
		/* constructor */
		Vector() {}
		
		Vector(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		/* operator */
		Vector plus(Vector b) {
			return new Vector(x+b.x, y+b.y);
		}
		Vector minus(Vector b) {
			return new Vector(x-b.x, y-b.y);
		}
		Vector mul(double r) {
			return new Vector(x*r, y*r);
		}
		Vector div(double r) {
			return new Vector(x/r, y/r);
		}
		double dot(Vector b) {
			return x*b.x+y*b.y;
		}
		double cross(Vector b) {
			return b.y*x-b.x*y;
		}
		double norm() {
			return Math.sqrt(x*x+y*y);
		}
		Vector normalize() {
			return new Vector(x/norm(), y/norm());
		}
		public String toString() {
			return "("+x+","+y+")";
		}
		
		/* method */
		static double ccw(Vector a, Vector b) {
			return a.cross(b);
		}
		static double ccw(Vector p, Vector a, Vector b) {
			return ccw(a.minus(p), b.minus(p));
		}
	}
	
	/*
	 * 임의의 한점 p를 오른쪽으로 끝까지 늘렸을 때
	 * 경계를 짝수번 교차하면 경계 밖, 홀수번 교차하면 경계 안에 있음 
	 */
	static boolean isInside(Vector p, List<Vector> list) {
		int crosses = 0;
		int n = list.size();
		
		for(int i = 0; i < n; i++) {
			int j = (i+1)%n;
			//반직선(list[i], list[j])를 p점의 선이 세로로 가로 지르는가?
			if((list.get(i).y > p.y) != (list.get(j).y > p.y)) {
				//반직선과 p를 포함한 선분의 x교차점
				double crossX = (list.get(j).x-list.get(i).x)*(p.y-list.get(i).y)
						/(list.get(j).y-list.get(i).y)+list.get(i).x;
				if(crossX > p.x) {
					crosses++;
				}
			}
		}
		return crosses % 2 > 0;
	}

	static boolean segmentIntersects(Vector a, Vector b, Vector c, Vector d) {
		//ab를 기준으로 c가 반시계(왼쪽), d가 시계(오른쪽)에 있으면 ab와 cd는 교차함
		double ab = Vector.ccw(a,b,c)*Vector.ccw(a,b,d);
		double cd = Vector.ccw(c,d,a)*Vector.ccw(c,d,b);
		
		//System.out.println("("+a+","+b+")="+ab+",("+c+","+d+")="+cd);
		//(ab), (cd)가 일직선상에 있는 경우, x범위와 y범위가 교차하는지를 반환함
		if((ab == 0) && (cd == 0)) {
			return !disjoint(a.x, b.x, c.x, d.x) && 
					!disjoint(a.y, b.y, c.y, d.y);
		}
		
		return ab <= 0 && cd <= 0;
	}
	
	//각점의 영역이 겹치지 않는지 반환
	static boolean disjoint(double a, double b, double c, double d) {
		if(a > b) {
			double tmp = a;
			a = b;
			b = tmp;
		}
		
		if(c > d) {
			double tmp = c;
			c = d;
			d = tmp;
		}
		
		return b < c || d < a;
	}
	//다각형의 겹침여부 반환
	private static boolean checkPloygonIntersects(
			List<Vector> p, List<Vector> q) {
		int n = p.size();
		int m = q.size();
		
		//한 다각형이 다른 다각형에 포함되지 않는지 확인
		if(isInside(p.get(0), q) || 
				isInside(q.get(0), p)) {
			return true;
		}
		
		//다각형의 모든 선분들에 대해 교차 여부 확인
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(segmentIntersects(p.get(i), p.get((i+1)%n),
						q.get(j), q.get((j+1)%m))) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int tc = Integer.parseInt(br.readLine());
			
			for(int t = 0; t < tc; t++) {
				int n = Integer.parseInt(br.readLine());
				
				List<Vector> nerds = new ArrayList<>();
				List<Vector> not_nerds = new ArrayList<>();
				for(int i = 0; i < n; i++) {
					double[] info = Arrays.stream(br.readLine().split(" "))
							.mapToDouble(Double::parseDouble)
							.toArray();
					if(info[0] == 1) {
						nerds.add(new Vector(info[1], info[2]));
					} else {
						not_nerds.add(new Vector(info[1], info[2]));
					}
				}
				
				String msg = "THEORY IS INVALID";
				if(solve(nerds, not_nerds)) {
					msg = "THEORY HOLDS";
				}
				
				System.out.println(msg);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean solve(List<Vector> nerds, List<Vector> not_nerds) {
		//get wrap
		List<Vector> nerdsWrap = getWrap(nerds);
		List<Vector> not_nerdsWrap = getWrap(not_nerds);
		
		//check each wrap intersected
		return !checkPloygonIntersects(nerdsWrap, not_nerdsWrap);
	}

	private static List<Vector> getWrap(List<Vector> list) {
		List<Vector> wrap = new ArrayList<>();
		//x값이 작은순으로 정렬
		list.sort(new Comparator<Vector>() {
			@Override
			public int compare(Vector arg0, Vector arg1) {
				// TODO Auto-generated method stub
				if(arg0.x == arg1.x) {
					return (int)(arg0.y - arg1.y);
				}
				return (int)(arg0.x - arg1.x);
			}
		});
		
		int n = list.size();
		Vector startVector = list.get(0);
		wrap.add(startVector);
		Vector distVector = startVector;
		while(true) {
			//find next wrap vector
			Vector nextVector = list.get(0);
			for(int i = 1; i < n; i++) {
				double cross = Vector.ccw(distVector, nextVector, list.get(i));
				double dist = (nextVector.minus(distVector)).norm()-
						(list.get(i).minus(distVector)).norm();
				//가장 왼쪽이 있거나 일직선상의 경우 가장 먼 점을 찾음
				if(cross > 0 || (cross == 0 && dist < 0)) {
					nextVector = list.get(i);
				}
			}
			
			if(nextVector == startVector) {
				break;
			}
			
			distVector = nextVector;
			wrap.add(nextVector);
		}
		
		return wrap;
	}
}
