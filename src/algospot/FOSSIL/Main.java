package algospot.FOSSIL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see https://algospot.com/judge/problem/read/FOSSIL
 * @author jun
 * input
2
5 5
35.74 35.85 69.64 50.00 73.52 82.55 43.50 92.22 17.67 76.18
16.47 8.02 60.98 14.62 66.80 37.74 45.89 67.22 13.04 55.19
4 3
73.84 11.41 71.61 32.72 39.87 38.84 22.41 17.87
75.13 51.64 47.72 87.34 15.97 64.56

 * output
27.6529680365
0.000000
 */
public class Main {
	//두 블록껍질에 포함된 점의 수
	static int n,m;
	//블록껍질의 점 리스트, 주어진 입력이 시계 반대방향
	static List<Point> hull1, hull2;
	//x가 증가하면 아래쪽, x가 감소하면 위쪽 껍질임
	//두 블록껍질에 대해 아래쪽 껍질과 위쪽 껍질을 저장함
	static List<Point[]> upper, lower;
	
	static class Point { 
		double x,y;
		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return "{"+this.x + "," + this.y+"}";
		}
	}
	
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int tc = Integer.parseInt(bf.readLine());
			for(int t = 0; t < tc; t++) {
				int[] tmp = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				n = tmp[0];
				m = tmp[1];
				
				hull1 = new ArrayList<>();
				hull2 = new ArrayList<>();
				lower = new ArrayList<>();
				upper = new ArrayList<>();
				
				double[] hull1_ptr = Arrays.stream(bf.readLine().split(" "))
						.mapToDouble(Double::parseDouble)
						.toArray();
				double[] hull2_ptr = Arrays.stream(bf.readLine().split(" "))
						.mapToDouble(Double::parseDouble)
						.toArray();
				for(int i = 0; i < n; i++) {
					hull1.add(new Point(hull1_ptr[i*2], hull1_ptr[i*2+1]));
				}
				for(int i = 0; i < m; i++) {
					hull2.add(new Point(hull2_ptr[i*2], hull2_ptr[i*2+1]));
				}
				
				decompose(hull1);
				decompose(hull2);
				
				System.out.println(solve());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//인접한 두 점에 대해 x가 증가하면 아래쪽, x가 감소하면 위쪽 껍질임
	static void decompose(List<Point> pointList) {
		int n = pointList.size();
		for(int i = 0; i < n; i++) {
			if(pointList.get(i).x < pointList.get((i+1)%n).x) {
				//해당 점과 다음점을 배열로 넣음
				lower.add(new Point[] {pointList.get(i), pointList.get((i+1)%n)});
			} else if(pointList.get(i).x > pointList.get((i+1)%n).x) {
				upper.add(new Point[] {pointList.get(i), pointList.get((i+1)%n)});
			}
		}
	}
	
	static double solve() {
		//껍질의 x,y 최대 최소값 검색
		double y1Max = 0, y1Min = 987654321;
		double y2Max = 0, y2Min = 987654321;
		double x1Max = 0, x1Min = 987654321;
		double x2Max = 0, x2Min = 987654321;
		for(int i = 0; i < n; i++) {
			x1Max = Math.max(hull1.get(i).x, x1Max);
			x1Min = Math.min(hull1.get(i).x, x1Min);
			
			y1Max = Math.max(hull1.get(i).y, y1Max);
			y1Min = Math.min(hull1.get(i).y, y1Min);
		}
		
		for(int i = 0; i < m; i++) {
			x2Max = Math.max(hull2.get(i).x, x2Max);
			x2Min = Math.min(hull2.get(i).x, x2Min);
			
			y2Max = Math.max(hull2.get(i).y, y2Max);
			y2Min = Math.min(hull2.get(i).y, y2Min);
		}
		
		//check y region overlap
		if(!( (y1Max > y2Min) || (y2Max > y1Min) )) {
			return 0;
		}
		
		//check x region overlap
		if(!(Math.max(x1Min,  x2Min) < Math.min(x1Max, x2Max))) {
			return 0;
		}
		//x값 최대 최소값 설정
		double low = Math.max(x1Min,  x2Min), high = Math.min(x1Max, x2Max);
		for(int i = 0 ; i < 100; i++) {
			//삼분검색
			double aab = (low*2+high)/3;
			double abb = (low+high*2)/3;
			
			if(vertical(aab) < vertical(abb)) {
				low = aab;
			} else {
				high = abb;
			}
		}
		
		return Math.max(0, vertical(high));
	}

	//x에 대해 블록이 겹치는 부분의 최대높이 반환
	//윗부분의 최소값 - 아랫부분 최대값
	private static double vertical(double x) {
		double minUpper = 987654321, maxLower = 0;
		
		//두 블록껍질의 윗부분 y 최대값 찾기
		for(int i = 0; i < upper.size(); i++) {
			if(between(upper.get(i), x)) {
				minUpper = Math.min(minUpper, getValue(upper.get(i), x));
			}
		}
		
		//두 블록껍질의 아랫부분 y 최소값 찾기
		for(int i = 0; i < lower.size(); i++) {
			if(between(lower.get(i), x)) {
				maxLower = Math.max(maxLower, getValue(lower.get(i), x));
			}
		}
		
		return minUpper - maxLower;
	}
	
	//주어진 x값이 주어진 두 점 사이에 있는지 여부 반환
	private static boolean between(Point[] points, double x) {
		return ((points[0].x <= x) && (points[1].x >= x)) ||
				((points[1].x <= x) && (points[0].x >= x));
	}
	//y=y0+(x-x0)*(dy/dx)
	private static double getValue(Point[] points, double x) {
		return points[0].y+(x-points[0].x)*(points[0].y-points[1].y)/(points[0].x-points[1].x);
	}
}
