package acmicpc._9019;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc=Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				int a = tmp[0];
				int b = tmp[1];
				System.out.println(solve(a,b));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String solve(int a, int b) {
		final int MAX = 10000;
		int[] cache = new int[10000];
		String[] path = new String[10000];
		Arrays.fill(cache, -1);
		
		Queue<Integer> q = new LinkedList<>();
		q.add(a);
		cache[a]=0;
		path[a]="";
		while(!q.isEmpty()) {
			int now = q.poll();
			if(now==b) {
				break;
			}
			
			if(cache[(now*2)%MAX] == -1) {
				cache[(now*2)%MAX]=cache[now]+1;
				path[(now*2)%MAX]=path[now]+"D";
				q.add((now*2)%MAX);
			}
			
			if(cache[(now-1+MAX)%MAX]==-1) {
				cache[(now-1+MAX)%MAX]=cache[now]+1;
				path[(now-1+MAX)%MAX]=path[now]+"S";
				q.add((now-1+MAX)%MAX);
			}
			
			int left=(now%1000)*10+(now/1000);
			if(cache[left]==-1) {
				cache[left]=cache[now]+1;
				path[left]=path[now]+"L";
				q.add(left);
			}
			
			int right=(now/10)+(now%10)*1000;
			if(cache[right]==-1) {
				cache[right]=cache[now]+1;
				path[right]=path[now]+"R";
				q.add(right);
			}
		}
		return path[b];
	}
}
