package acmicpc._14594;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(br.readLine());
			int[] parent = new int[n+1];
			for(int i = 0; i <= n; i++) {
				parent[i]=i;
			}
			
			int m = Integer.parseInt(br.readLine());
			List<Command> moveList = new ArrayList<>();
			for(int i = 0; i < m; i++) {
				int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				moveList.add(new Command(tmp));
			}
			
			Collections.sort(moveList, new Comparator<Command>() {
				@Override
				public int compare(Command o1, Command o2) {
					// TODO Auto-generated method stub
					return o1.x-o2.x;
				}
			});
			
			for(Command tmp : moveList) {
				int x = tmp.x;
				int y = tmp.y;
				int nowP=findParent(parent, x);
				
				for(int idx = x+1; idx <= y; idx++) {
					int next=findParent(parent,idx);
					parent[next]=nowP;
				}
//				System.out.println(Arrays.toString(parent));
			}
			
			int cnt=0, bef=0;
			for(int i = 1; i <= n; i++) {
				if(parent[i]==bef) {
					continue;
				}
				bef=parent[i];
				cnt++;
			}
			
			System.out.println(cnt);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static class Command {
		int x,y;
		
		public Command(int[] arr) {
			this(arr[0],arr[1]);
		}
		
		public Command(int x, int y) {
			this.x=x;
			this.y=y;
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
			if(o instanceof Command) {
				Command obj = (Command) o;
				return obj.x==this.x && obj.y==this.y;
			}
			return false;
		}
	}
	
	private static int findParent(int[] parent, int idx) {
		int next=idx;
		while(parent[next]!=next) {
			next=parent[next];
		}
		return next;
	}
}
