package codejam._2020.round1a.pascalwalk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
	static int[][] pascalArr;
	
	static class Info {
		int i,j;
		public Info(int i, int j) {
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "("+i+","+j+")";
		}
	}
	
	static ArrayList<Info> arr;
	public static void main(String[] args) {
		try {
			precal();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			//int tc = 500;
			for(int t = 1; t <= tc; t++) {
				int n = Integer.parseInt(br.readLine());
				//int n = t;
				
				arr = new ArrayList<>();
				
				boolean[][] visited = new boolean[31][31];
				visited[1][1] = true;
				n--;
				arr.add(new Info(1,1));
				//solve(1,1, visited, n, new ArrayList<Info>());
				solve2(n);
				System.out.println("Case #"+t+":");
				/*
				for(Info tmp : arr) {
					System.out.println(tmp.i +" "+tmp.j);
				}
				//*/
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void solve2(int n) {
		arr.clear();
		n++;
		int i = 0, j = 0;
		boolean isSide = false;
		while(n > 0 && !isSide) {
			int nextI = i+1;
			int nextJ = nextI%2==0?j:j+1;
			
			int nextLeft = 0;
			if(nextI > 30) {
				nextLeft = 987654321;
			} else {
				for(int k = 1; k < nextJ; k++) {
					nextLeft += pascalArr[nextI][nextJ];
				}
			}
			//System.out.println(n+">>"+"("+nextI+","+nextJ+")"+pascalArr[nextI][nextJ]+","+tmp);
			if(nextI <= 30) {
				System.out.println(n+">>"+"("+nextI+","+nextJ+")"+(n-pascalArr[nextI][nextJ])+","+(nextLeft));
			}
			
			//if(n >= pascalArr[nextI][nextJ] && ((n-pascalArr[nextI][nextJ]-nextLeft > 0))) {
			if(nextI <= 30 && (n-pascalArr[nextI][nextJ]-nextLeft) >= 0) {
				i = nextI;
				j = nextJ;
			} else {
				if(j == 1) {
					isSide = true;
				} else {
					j--;
				}
			}
			
			System.out.println(">>>>>>>"+i+","+j+","+n);
			if(!isSide) {
				n -= pascalArr[i][j];
				arr.add(new Info(i,j));
			}
		}
		
		while(n > 0) {
			n--;
			i++;
			arr.add(new Info(i,1));
		}
		
		System.out.println("left::"+n+",size::"+arr.size());
		
		/*
		int sum = 0;
		for(Info tmp : arr) {
			sum += pascalArr[tmp.i][tmp.j];
		}
		System.out.println(sum);
		*/
	}
	
	private static void solve(int i, int j, boolean[][] visited, int n, ArrayList<Info> s) {
		// TODO Auto-generated method stub
		if(n < 0 || arr.size() > 1) {
			return;
		}
		//System.out.println(i+","+j+">>"+s);
		if(n == 0) {
			arr.addAll(s);
			return;
		}
		
		int[][] idx = {{-1,-1}, {-1,0}, {0,-1}, {0,1}, {1,0}, {1,1}};
		for(int k = 0; k < idx.length; k++) {
			if(inBound(i,j,idx[k])) {
				int nextI = i+idx[k][0];
				int nextJ = j+idx[k][1];
				if(!visited[nextI][nextJ]) {
					s.add(new Info(nextI, nextJ));
					visited[nextI][nextJ] = true;
					solve(nextI, nextJ, visited, n-pascalArr[nextI][nextJ], s);
					visited[nextI][nextJ] = false;
					s.remove(s.size()-1);
				}
			}
		}
	}

	private static boolean inBound(int i, int j, int[] ks) {
		int nextI = i+ks[0];
		int nextJ = j+ks[1];
		return nextI >= 1 && nextI <= 30 && nextJ >= 1 && nextJ <= nextI;
	}

	private static void precal() {
		pascalArr = new int[31][31];
		pascalArr[1][1] = 1;
		for(int i = 2; i < pascalArr.length; i++) {
			for(int j = 1; j <= i; j++) {
				pascalArr[i][j] = pascalArr[i-1][j-1]+pascalArr[i-1][j];
			}
		}
	}
}
