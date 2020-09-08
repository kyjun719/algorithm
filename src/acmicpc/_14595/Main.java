package acmicpc._14595;

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
			int cnt=n;
			for(int i = 0; i < m; i++) {
				int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int x = tmp[0],y=tmp[1];
                while(findParent(parent,x)!=findParent(parent,y)) {
                    cnt--;
                    int py=findParent(parent,y);
                    int next=py-1;
                    parent[py]=findParent(parent,x);
                    y=next;
                }
//                System.out.println(Arrays.toString(parent));
			}
			
			System.out.println(cnt);
		} catch(Exception e) {
			e.printStackTrace();
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
