package acmicpc._1473;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		int n = tmp[0];
		int m = tmp[1];
		char[][] arr = new char[n][m];
		for(int i = 0; i < n; i++) {
			arr[i]=br.readLine().toCharArray();
		}
		
		//solve(0,0,n,m,arr,0);
		//System.out.println(ans==987654321?-1:ans);
		System.out.println(solve2(n,m,arr));
	}
	
	private static int solve2(int n, int m, char[][] arr) {
		boolean[][][] visited = new boolean[n][m][2];
		Queue<VisitedInfo> q = new LinkedList<>();
		q.add(new VisitedInfo(arr,0,0,0,0));
		
		spin(0,0,arr);
		spin(0,1,arr);
		q.add(new VisitedInfo(arr,0,0,1,1));
		visited[0][0][0]=true;
		visited[0][0][1]=true;
		while(!q.isEmpty()) {
			VisitedInfo now = q.poll();
//			System.out.println(now);
			if(now.y==n-1 && now.x==m-1) {
				return now.cnt;
			}
			
			int y = now.y, x = now.x;
			now.cnt++;
			switch(now.arr[now.y][now.x]) {
				case 'A':
					if(y-1>=0 && (now.arr[y-1][x]=='A' || now.arr[y][x]=='C') && !visited[y-1][x][now.spined]) {
						visited[y-1][x][now.spined]=true;
						q.add(new VisitedInfo(now.arr,y-1,x,now.cnt,now.spined));
					}
					if(y+1<n && (now.arr[y+1][x]=='A' || now.arr[y+1][x]=='C') && !visited[y+1][x][now.spined]) {
						visited[y+1][x][now.spined]=true;
						q.add(new VisitedInfo(now.arr,y+1,x,now.cnt,now.spined));
					}
					if(x-1>=0 && (now.arr[y][x-1]=='A' || now.arr[y][x-1]=='D') && !visited[y][x-1][now.spined]) {
						visited[y][x-1][now.spined]=true;
						q.add(new VisitedInfo(now.arr,y,x-1,now.cnt,now.spined));
					}
					if(x+1<m && (now.arr[y][x+1]=='A' || now.arr[y][x+1]=='D') && !visited[y][x+1][now.spined]) {
						visited[y][x+1][now.spined]=true;
						q.add(new VisitedInfo(now.arr,y,x+1,now.cnt,now.spined));
					}
					break;
				case 'B':
					break;
				case 'C':
					if(y-1>=0 && (now.arr[y-1][x]=='A' || now.arr[y][x]=='C') && !visited[y-1][x][now.spined]) {
						visited[y-1][x][now.spined]=true;
						q.add(new VisitedInfo(now.arr,y-1,x,now.cnt,now.spined));
					}
					if(y+1<n && (now.arr[y+1][x]=='A' || now.arr[y+1][x]=='C') && !visited[y+1][x][now.spined]) {
						visited[y+1][x][now.spined]=true;
						q.add(new VisitedInfo(now.arr,y+1,x,now.cnt,now.spined));
					}
					break;
				case 'D':
					if(x-1>=0 && (now.arr[y][x-1]=='A' || now.arr[y][x-1]=='D') && !visited[y][x-1][now.spined]) {
						visited[y][x-1][now.spined]=true;
						q.add(new VisitedInfo(now.arr,y,x-1,now.cnt,now.spined));
					}
					if(x+1<m && (now.arr[y][x+1]=='A' || now.arr[y][x+1]=='D') && !visited[y][x+1][now.spined]) {
						visited[y][x+1][now.spined]=true;
						q.add(new VisitedInfo(now.arr,y,x+1,now.cnt,now.spined));
					}
					break;
			}
			
			spin(y,0,now.arr);
			spin(x,1,now.arr);
//			for(char[] c : now.arr) {
//				System.out.println(Arrays.toString(c));
//			}
			
			now.spined^=now.spined;
			now.cnt++;
			switch(now.arr[now.y][now.x]) {
				case 'A':
					if(y-1>=0 && (now.arr[y-1][x]=='A' || now.arr[y][x]=='C') && !visited[y-1][x][now.spined]) {
						visited[y-1][x][now.spined]=true;
						q.add(new VisitedInfo(now.arr,y-1,x,now.cnt,now.spined));
					}
					if(y+1<n && (now.arr[y+1][x]=='A' || now.arr[y+1][x]=='C') && !visited[y+1][x][now.spined]) {
						visited[y+1][x][now.spined]=true;
						q.add(new VisitedInfo(now.arr,y+1,x,now.cnt,now.spined));
					}
					if(x-1>=0 && (now.arr[y][x-1]=='A' || now.arr[y][x-1]=='D') && !visited[y][x-1][now.spined]) {
						visited[y][x-1][now.spined]=true;
						q.add(new VisitedInfo(now.arr,y,x-1,now.cnt,now.spined));
					}
					if(x+1<m && (now.arr[y][x+1]=='A' || now.arr[y][x+1]=='D') && !visited[y][x+1][now.spined]) {
						visited[y][x+1][now.spined]=true;
						q.add(new VisitedInfo(now.arr,y,x+1,now.cnt,now.spined));
					}
					break;
				case 'B':
					break;
				case 'C':
					if(y-1>=0 && (now.arr[y-1][x]=='A' || now.arr[y][x]=='C') && !visited[y-1][x][now.spined]) {
						visited[y-1][x][now.spined]=true;
						q.add(new VisitedInfo(now.arr,y-1,x,now.cnt,now.spined));
					}
					if(y+1<n && (now.arr[y+1][x]=='A' || now.arr[y+1][x]=='C') && !visited[y+1][x][now.spined]) {
						visited[y+1][x][now.spined]=true;
						q.add(new VisitedInfo(now.arr,y+1,x,now.cnt,now.spined));
					}
					break;
				case 'D':
					if(x-1>=0 && (now.arr[y][x-1]=='A' || now.arr[y][x-1]=='D') && !visited[y][x-1][now.spined]) {
						visited[y][x-1][now.spined]=true;
						q.add(new VisitedInfo(now.arr,y,x-1,now.cnt,now.spined));
					}
					if(x+1<m && (now.arr[y][x+1]=='A' || now.arr[y][x+1]=='D') && !visited[y][x+1][now.spined]) {
						visited[y][x+1][now.spined]=true;
						q.add(new VisitedInfo(now.arr,y,x+1,now.cnt,now.spined));
					}
					break;
			}
		}
		return -1;
	}
	
	private static class VisitedInfo {
		char[][] arr;
		int y,x,cnt;
		int spined;
		public VisitedInfo(char[][] arr, int y, int x, int cnt, int spined) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.spined = spined;
			
			this.arr = new char[arr.length][arr[0].length];
			for(int i = 0; i < arr.length; i++) {
				this.arr[i]=Arrays.copyOf(arr[i], arr[i].length);
			}
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for(char[] c : arr) {
				sb.append(Arrays.toString(c)+"\n");
			}
			sb.append("("+y+","+x+")::"+cnt+",spined::"+spined+"\n");
			sb.append("=======================\n");
			return sb.toString();
			
		}
	}
	
	private static int ans=500;
	private static void solve(int y, int x, int n, int m, char[][] arr, int cnt) {
		if(y==n-1 && x==m-1) {
			ans=Math.min(ans, cnt);
			return;
		}
		if(cnt>=ans) {
			return;
		}
		switch(arr[y][x]) {
			case 'A':
//				if(y-1>=0 && (arr[y-1][x]=='A' || arr[y-1][x]=='C')) {
//					solve(y-1,x,n,m,arr,cnt+1);
//				}
				if(y+1<n && (arr[y+1][x]=='A' || arr[y+1][x]=='C')) {
					solve(y+1,x,n,m,arr,cnt+1);
				}
//				if(x-1>=0 && (arr[y][x-1]=='A' || arr[y][x-1]=='D')) {
//					solve(y,x-1,n,m,arr,cnt+1);
//				}
				if(x+1<m && (arr[y][x+1]=='A' || arr[y][x+1]=='D')) {
					solve(y,x+1,n,m,arr,cnt+1);
				}
				break;
			case 'B':
				break;
			case 'C':
//				if(y-1>=0 && (arr[y-1][x]=='A' || arr[y-1][x]=='C')) {
//					solve(y-1,x,n,m,arr,cnt+1);
//				}
				if(y+1<n && (arr[y+1][x]=='A' || arr[y+1][x]=='C')) {
					solve(y+1,x,n,m,arr,cnt+1);
				}
				break;
			case 'D':
//				if(x-1>=0 && (arr[y][x-1]=='A' || arr[y][x-1]=='D')) {
//					solve(y,x-1,n,m,arr,cnt+1);
//				}
				if(x+1<m && (arr[y][x+1]=='A' || arr[y][x+1]=='D')) {
					solve(y,x+1,n,m,arr,cnt+1);
				}
				break;
		}
		spin(y,0,arr);
		spin(x,1,arr);
		cnt+=1;
		switch(arr[y][x]) {
			case 'A':
//				if(y-1>=0 && (arr[y-1][x]=='A' || arr[y-1][x]=='C')) {
//					solve(y-1,x,n,m,arr,cnt+1);
//				}
				if(y+1<n && (arr[y+1][x]=='A' || arr[y+1][x]=='C')) {
					solve(y+1,x,n,m,arr,cnt+1);
				}
//				if(x-1>=0 && (arr[y][x-1]=='A' || arr[y][x-1]=='D')) {
//					solve(y,x-1,n,m,arr,cnt+1);
//				}
				if(x+1<m && (arr[y][x+1]=='A' || arr[y][x+1]=='D')) {
					solve(y,x+1,n,m,arr,cnt+1);
				}
				break;
			case 'B':
				break;
			case 'C':
//				if(y-1>=0 && (arr[y-1][x]=='A' || arr[y-1][x]=='C')) {
//					solve(y-1,x,n,m,arr,cnt+1);
//				}
				if(y+1<n && (arr[y+1][x]=='A' || arr[y+1][x]=='C')) {
					solve(y+1,x,n,m,arr,cnt+1);
				}
				break;
			case 'D':
//				if(x-1>=0 && (arr[y][x-1]=='A' || arr[y][x-1]=='D')) {
//					solve(y,x-1,n,m,arr,cnt+1);
//				}
				if(x+1<m && (arr[y][x+1]=='A' || arr[y][x+1]=='D')) {
					solve(y,x+1,n,m,arr,cnt+1);
				}
				break;
		}
		spin(y,0,arr);
		spin(x,1,arr);
		cnt-=1;
	}
	
	private static void spin(int idx, int rc, char[][] arr) {
		if(rc==0) {
			//row spin
			for(int i = 0; i < arr[idx].length; i++) {
				char ch = arr[idx][i];
				arr[idx][i]=ch=='A'?'A':ch=='B'?'B':ch=='C'?'D':'C';
			}
		} else {
			//col spin
			for(int i = 0; i < arr.length; i++) {
				char ch = arr[i][idx];
				arr[i][idx]=ch=='A'?'A':ch=='B'?'B':ch=='C'?'D':'C';
//				System.out.println(ch+">>"+i+","+idx+"::"+arr[i][idx]);
			}
		}
	}
}
