package algospot.TREASURE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see https://algospot.com/judge/problem/read/TREASURE
 * @author jun
 * input
2
26 34 76 72 15
41 52
50 71
42 87
26 84
16 58
33 33
51 23
64 32
73 17
86 14
91 38
92 68
82 79
68 45
61 58
50 20 70 80 4
86 50 
30 10
90 50
30 90

 * output
1343.0948739496 
57.1428571429 
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
				
				List<Vector> rect = new ArrayList<>();
				rect.add(new Vector(maxx, maxy));
				rect.add(new Vector(minx, maxy));
				rect.add(new Vector(minx, miny));
				rect.add(new Vector(maxx, miny));
				
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
		// TODO Auto-generated method stub
		List<Vector> ptrList = new ArrayList<>();
		ptrList = cutPoly(p, rect.get(0), rect.get(1));
		ptrList = cutPoly(ptrList, rect.get(1), rect.get(2));
		ptrList = cutPoly(ptrList, rect.get(2), rect.get(3));
		ptrList = cutPoly(ptrList, rect.get(3), rect.get(0));
		return getArea(ptrList);
	}
	private static double getArea(List<Vector> ptrList) {
		// TODO Auto-generated method stub
		double ret = 0;
		int n = ptrList.size();
		for(int i = 0; i < n; i++) {
			int j = (i+1)%n;
			ret += ptrList.get(i).cross(ptrList.get(j));
		}
		return Math.abs(ret)/2;
	}
	private static List<Vector> cutPoly(List<Vector> p, Vector a, Vector b) {
		// TODO Auto-generated method stub
		int n = p.size();
		boolean[] isInside = new boolean[n];
		for(int i = 0; i < n; i++) {
			isInside[i] = Vector.ccw(a,b,p.get(i)) >= 0;
		}
		List<Vector> ret = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			int j = (i+1)%n;
			if(isInside[i]) {
				ret.add(p.get(i));
			}
			if(isInside[i] != isInside[j]) {
				Vector interP = new Vector();
				if(intersection(p.get(i), p.get(j), a,b,interP)) {
					ret.add(interP);
				}
			}
		}
		return ret;
	}
	private static boolean intersection(Vector a, Vector b, Vector c, Vector d, Vector interP) {
		// TODO Auto-generated method stub
		double det = (b.minus(a)).cross(d.minus(c));
		if(det == 0) {
			return false;
		}
		
		Vector tmp = a.plus(b.minus(a).mul(c.minus(a).cross(d.minus(c))/det)); 
		interP.x = tmp.x;
		interP.y = tmp.y;
		return true;
	}
}
