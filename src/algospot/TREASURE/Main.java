package algospot.TREASURE;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int tc = Integer.parseInt(br.readLine());
			
			for(int t = 0; t < tc; t++) {
				double[] info = Arrays.stream(br.readLine().split(" "))
						.mapToDouble(Double::parseDouble)
						.toArray();
				double minx = info[0];
				double miny = info[1];
				double maxx = info[2];
				double maxy = info[3];
				if(minx > maxx) {
					double tmp = maxx;
					maxx = minx;
					minx = tmp;
				}
				if(miny > maxy) {
					double tmp = maxy;
					maxy = miny;
					miny = tmp;
				}
				
				//직사각형 좌표 저장
				List<Vector> rect = new ArrayList<>();
				rect.add(new Vector(maxx, maxy));
				rect.add(new Vector(minx, maxy));
				rect.add(new Vector(minx, miny));
				rect.add(new Vector(maxx, miny));
				
				//다각형의 좌표정보 저장
				int n = (int) info[4];
				List<Vector> p = new ArrayList<>();
				for(int i = 0; i < n; i++) {
					double[] tmp = Arrays.stream(br.readLine().split(" "))
							.mapToDouble(Double::parseDouble)
							.toArray();
					p.add(new Vector(tmp[0], tmp[1]));
				}
				
				System.out.println(solve(rect, p));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static double solve(List<Vector> rect, List<Vector> p) {
		//직사각형의 선분을 기준으로 왼쪽에 있는 다각형의 점들 검색
		List<Vector> ptrList = new ArrayList<>();
		ptrList = cutPoly(p, rect.get(0), rect.get(1));
		ptrList = cutPoly(ptrList, rect.get(1), rect.get(2));
		ptrList = cutPoly(ptrList, rect.get(2), rect.get(3));
		ptrList = cutPoly(ptrList, rect.get(3), rect.get(0));
		/*
		GraphFrame frame = new GraphFrame(rect, p, ptrList);
		frame.setSize(500, 500);
		frame.setVisible(true);
		*/
		return getArea(ptrList);
	}
	//다각형의 넓이 반환
	//다각형의 넓이 = 각 점들의 외적값/2
	private static double getArea(List<Vector> ptrList) {
		double ret = 0;
		int n = ptrList.size();
		for(int i = 0; i < n; i++) {
			int j = (i+1)%n;
			ret += ptrList.get(i).cross(ptrList.get(j));
		}
		return Math.abs(ret)/2;
	}
	private static List<Vector> cutPoly(List<Vector> p, Vector a, Vector b) {
		int n = p.size();
		boolean[] isInside = new boolean[n];
		//다각형의 점들이 왼쪽에 있는지 여부 탐색
		for(int i = 0; i < n; i++) {
			isInside[i] = Vector.ccw(a,b,p.get(i)) >= 0;
		}
		List<Vector> ret = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			int j = (i+1)%n;
			//왼쪽에 있는 경우 직사각형 내부에 있는 점
			if(isInside[i]) {
				ret.add(p.get(i));
			}
			//다각형의 선분이 직사각형 선분에 교차하는 경우 
			if(isInside[i] != isInside[j]) {
				Vector interP = new Vector();
				if(intersection(p.get(i), p.get(j), a,b,interP)) {
					ret.add(interP);
				}
			}
		}
		return ret;
	}
	//교차하는 경우 true, 하지 않는경우 false반환, 교차점은 파라미터에 저장함
	private static boolean intersection(Vector a, Vector b, Vector c, Vector d, Vector interP) {
		double det = (b.minus(a)).cross(d.minus(c));
		//교차하지 않는 경우
		if(det == 0) {
			return false;
		}
		
		//교차하는 경우 교차점의 좌표 계산
		Vector tmp = a.plus(b.minus(a).mul(c.minus(a).cross(d.minus(c))/det)); 
		interP.x = tmp.x;
		interP.y = tmp.y;
		return true;
	}
	
	//점들의 위치를 그림으로 보기위한 GUI
	static class GraphFrame extends Frame {
		public GraphFrame(List<Vector> rect, List<Vector> poly, List<Vector> pointList) {
			GraphPanel panel = new GraphPanel(rect, poly, pointList);
			this.add(panel);
		}
		
		class GraphPanel extends Panel {
			private List<Vector> pointList;
			private List<Vector> rect;
			private List<Vector> poly;
			private int size = 500;
			private int mul = size/100;
			GraphPanel(List<Vector> rect, List<Vector> poly, List<Vector> pointList) {
				this.pointList = pointList;
				this.rect = rect;
				this.poly = poly;
				this.setSize(size, size);
			}
			
			public void paint(Graphics g) {
				g.setColor(Color.red);
				for(int i = 0; i <rect.size(); i++) {
					int j = (i+1)%rect.size();
					g.drawLine((int)(rect.get(i).x*mul), 
							(int)(rect.get(i).y*mul),
							(int)(rect.get(j).x*mul),
							(int)(rect.get(j).y*mul));
				}
				
				g.setColor(Color.BLUE);
				for(int i = 0; i < poly.size(); i++) {
					int j = (i+1)%poly.size();
					g.drawLine((int)(poly.get(i).x*mul), 
							(int)(poly.get(i).y*mul),
							(int)(poly.get(j).x*mul),
							(int)(poly.get(j).y*mul));
				}
				
				g.setColor(Color.BLACK);
				int ptrSize = 10;
				for(int i = 0; i < pointList.size(); i++) {
					g.drawOval((int)(pointList.get(i).x*mul-ptrSize/2),
							(int)(pointList.get(i).y*mul-ptrSize/2)
							, ptrSize, ptrSize);
				}
			}
		}
	}
}
