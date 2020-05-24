package codejam._2020.round1a.squaredance;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static final int RIGHT=0, LEFT=1,TOP=2,BOTTOM=3;
	static class Competitor {
		int y,x,val;
		private Competitor[] compArr = new Competitor[4];
		public Competitor(int y, int x, int val) {
			this.y = y;
			this.x = x;
			this.val = val;
		}
		@Override
		public String toString() {
			return "("+y+","+x+")";
		}
		
		public Competitor getComp(int pos) {
			return compArr[pos];
		}
		
		public void setComp(int pos, Competitor comp) {
			compArr[pos] = comp;
		}

		public boolean isEliminated() {
			//System.out.println(y+","+x+">>"+val+"*"+getCount()+"::"+getSum());
			int sum = 0;
			int cnt = 0;
			for(int i = 0; i < 4; i++) {
				sum += compArr[i].val;
				if(compArr[i].val > 0) {
					cnt++;
				}
			}
			return (val*cnt) < sum;
		}
	}
	
    public static void main( String[] args ) {
    	try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 1; t <= tc; t++) {
				int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				int r = info[0];
				int c = info[1];
				Competitor[][] arr = new Competitor[r][c];
				boolean[][] checked = new boolean[r][c];
				long totalSum = 0;

				for(int y=0; y < r; y++) {
					int[] tmp=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
					for(int x = 0; x < c; x++) {
						arr[y][x] = new Competitor(y, x, tmp[x]);
						totalSum += tmp[x];
					}
				}
				
				//Queue<Competitor> q = new LinkedList<>();
				Queue<Competitor> remove = new LinkedList<>();
				long ret = totalSum;
				
				for(int y=0; y < r; y++) {
					for(int x = 0; x < c; x++) {
						if(x-1>=0) {
							arr[y][x].setComp(LEFT, arr[y][x-1]);
						} else {
							arr[y][x].setComp(LEFT, new Competitor(-1,-1,0));
						}
						
						if(x+1<c) {
							arr[y][x].setComp(RIGHT, arr[y][x+1]);
						} else {
							arr[y][x].setComp(RIGHT, new Competitor(-1,-1,0));
						}
						
						if(y-1>=0) {
							arr[y][x].setComp(TOP, arr[y-1][x]);
						} else {
							arr[y][x].setComp(TOP, new Competitor(-1,-1,0));
						}
						
						if(y+1<r) {
							arr[y][x].setComp(BOTTOM, arr[y+1][x]);
						} else {
							arr[y][x].setComp(BOTTOM, new Competitor(-1,-1,0));
						}
						
						if(arr[y][x].isEliminated()) {
							remove.add(arr[y][x]);
							checked[y][x] = true;
						}
					}
				}
				
				while(!remove.isEmpty()) {
					Queue<Competitor> q = new LinkedList<>();
					//change compass obj
					while(!remove.isEmpty()) {
						Competitor comp = remove.poll();						
						totalSum -= comp.val;
						
						comp.getComp(LEFT).setComp(RIGHT, comp.getComp(RIGHT));
						comp.getComp(RIGHT).setComp(LEFT, comp.getComp(LEFT));
						comp.getComp(TOP).setComp(BOTTOM, comp.getComp(BOTTOM));
						comp.getComp(BOTTOM).setComp(TOP, comp.getComp(TOP));
						
						for(int i = 0; i < 4; i++) {
							if(comp.getComp(i).val != 0) {
								q.add(comp.getComp(i));
							}
						}
						
						comp=null;
					}
					
					//System.out.println(q);
					while(!q.isEmpty()) {
						Competitor comp = q.poll();
						
						if(checked[comp.y][comp.x]) {
							continue;
						}
						
						//val < compass val, add remove queue
						if(comp.isEliminated()) {
							remove.add(comp);
							checked[comp.y][comp.x] = true;
						}
					}
					//while end
					//System.out.println("remove::"+remove);

					ret += totalSum;
				}
				//while end
				//print ret
				System.out.println("Case #"+t+": "+ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
}
