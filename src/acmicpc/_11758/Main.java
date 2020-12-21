package acmicpc._11758;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args )throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Vector a = new Vector(tmp[0], tmp[1]);
		
		tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Vector p = new Vector(tmp[0], tmp[1]);
		
		tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Vector b = new Vector(tmp[0], tmp[1]);
		
		double ret = ccw(p,b,a);
		System.out.println(ret>0?"1":ret<0?"-1":0);
	}
	
	private static class Vector {
		private double x=0,y=0;
		public Vector(double x, double y) {
			this.x = x;
			this.y = y;
		}
		Vector minus(Vector b) {
			return new Vector(x-b.x,y-b.y);
		}
		double cross(Vector b) {
			return b.y*x-b.x*y;
		}
	}
	
	//원점에서 벡터 b가 벡터 a의 반시계 방향이면 양수, 시계방향이면 음수, 평행이면 0을 반환함
	private static double ccw(Vector a, Vector b) {
		return a.cross(b);
	}
	
	private static double ccw(Vector p, Vector a, Vector b) {
		return ccw(a.minus(p), b.minus(p));
	}
}
