package algospot.PINBALL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		Vector normalize() {
			return new Vector(x/this.norm(), y/this.norm());
		}
		Vector mul(double c) {
			return new Vector(x*c, y*c);
		}
		Vector proj(Vector b) {
			Vector tmp = b.normalize();
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
				//현재 공위치
				Vector p = new Vector(arr[0], arr[1]);
				//공의 방향
				Vector d = new Vector(arr[2], arr[3]);
				int n = arr[4];
				Circle[] circleArr = new Circle[n];
				for(int i = 0; i < n; i++) {
					int[] tmp = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
					//두 장애물 사이에 2이하가 없으므로 공의 반지름을 0으로 하고
					//원들의 반지름을 1씩 증가시킴
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
		d = d.normalize();
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
	//공과 충돌하는 원과 충돌하는 시간 반환
	private static double[] getCollision(Vector p, Vector d, Circle[] circleArr) {
		double t = INF;
		double ret = -1;
		//충돌시간이 가장 빠른 원 검색
		for(int i = 0; i < circleArr.length; i++) {
			double calT = getCollisionTime(p,d,circleArr[i]);
			if(t > calT) {
				t = calT;
				ret = i;
			}
		}
		return new double[] {ret, t};
	}
	
	//d*d*t^2+2(p-c)*d*t+c*c+p*p-2(c*p)-r^2=0
	private static double getCollisionTime(Vector p, Vector d, Circle circle) {
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
	
	//q점에서 원 충돌시 나가는 방향벡터 반환
	private static Vector getNewD(Vector d, Vector q, Circle circle) {
		Vector n = q.minus(circle.c);
		return d.minus(d.proj(n).mul(2)).normalize();
	}
	
	//p+dt로 공과 원이 충돌하는 지점 반환
	private static Vector getNewP(Vector p, Vector d, double e) {
		return p.plus(d.mul(e));
	}
}
