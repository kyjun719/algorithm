package algospot.PINBALL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see https://algospot.com/judge/problem/read/PINBALL
 * @author jun
 * input
1
5 5 1 1 5
22 40 12
61 26 20
19 78 21
51 86 7
84 57 15

 * output
0 1 2 1 2 0 3 4
 */
public class Main {
	static class Vector {
		double x,y;
		Vector(double x, double y) {
			this.x = x;
			this.y = y;
		}
		Vector plus(Vector v) {
			return new Vector(this.x+v.x, this.y+v.y);
		}
		Vector minus(Vector v) {
			return new Vector(this.x-v.x, this.y-v.y);
		}
		double norm() {
			return Math.sqrt(x*x+y*y);
		}
		double inner(Vector v) {
			return x*v.x+y*v.y;
		}
		Vector getUnit() {
			return new Vector(x/this.norm(), y/this.norm());
		}
		Vector mul(double c) {
			return new Vector(x*c, y*c);
		}
		Vector proj(Vector b) {
			Vector tmp = b.getUnit();
			return tmp.mul(tmp.inner(this));
		}
	}
	static class Circle {
		Vector c;
		double r;
		Circle(Vector c, double r) {
			this.c = c;
			this.r = r;
		}
	}
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				int[] arr = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				Vector p = new Vector(arr[0], arr[1]);
				Vector d = new Vector(arr[2], arr[3]);
				int n = arr[4];
				Circle[] circleArr = new Circle[n];
				for(int i = 0; i < n; i++) {
					int[] tmp = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
					circleArr[i] = new Circle(new Vector(tmp[0], tmp[1]), tmp[2]+1);
				}
				List<Integer> answer = solve(p,d,circleArr);
				System.out.println(answer.toString()
						.replace("[", "").replace("]", "").replace(",", ""));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static List<Integer> solve(Vector p, Vector d, Circle[] circleArr) {
		List<Integer> ret = new ArrayList<>();
		d = d.getUnit();
		for(int i = 0; i < 100; i++) {
			double[] tmp = getCollision(p,d,circleArr);
			if(tmp[0] == -1) {
				break;
			}
			ret.add((int) tmp[0]);
			Vector newP = getNewP(p,d,tmp[1]);
			Vector newD = getNewD(d,newP,circleArr[(int)tmp[0]]);
			p = newP;
			d = newD;
		}
		
		return ret;
	}
	private static double INF = 987654321;
	private static double[] getCollision(Vector p, Vector d, Circle[] circleArr) {
		// TODO Auto-generated method stub
		double t = INF;
		double ret = -1;
		for(int i = 0; i < circleArr.length; i++) {
			double calT = getCollisionTime(p,d,circleArr[i]);
			if(t > calT) {
				t = calT;
				ret = i;
			}
		}
		return new double[] {ret, t};
	}
	private static double getCollisionTime(Vector p, Vector d, Circle circle) {
		// TODO Auto-generated method stub
		Vector cc = circle.c;
		double a = d.inner(d);
		double b = 2*(p.inner(d)-cc.inner(d));
		double c = cc.inner(cc)+p.inner(p)-2*cc.inner(p)-circle.r*circle.r;
		double dis = b*b-4*a*c;
		if(dis < 0) {
			return INF;
		}
		if(dis == 0) {
			return -b/(2*a);
		}
		double ret1 = (-b-Math.sqrt(dis))/(2*a);
		double ret2 = (-b+Math.sqrt(dis))/(2*a);
		return ret1 < 0 ? INF : ret1;
	}
	private static Vector getNewD(Vector d, Vector q, Circle circle) {
		// TODO Auto-generated method stub
		Vector n = q.minus(circle.c);
		return d.minus(d.proj(n).mul(2)).getUnit();
	}
	private static Vector getNewP(Vector p, Vector d, double e) {
		// TODO Auto-generated method stub
		return p.plus(d.mul(e));
	}
	
}
