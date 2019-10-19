package nhn.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test4 {
	static int n,k;
	static int[][] path;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			n = info[0];
			k = info[1];
			path = new int[n][n];
			for(int i = 0; i < n; i++) {
				Arrays.fill(path[i], -1);
			}
			int cnt = Integer.parseInt(br.readLine());
			for(int i = 0; i < cnt; i++) {
				String[] tmp = br.readLine().split(" ");
				int a = tmp[0].charAt(0)-65;
				int b = tmp[1].charAt(0)-65;
				int len = Integer.parseInt(tmp[2]);
				path[a][b] = len;
				path[b][a] = len;
			}
			Stack<Integer> cityList = new Stack<Integer>();
			cityList.add(0);
			boolean[] checked = new boolean[n];
			checked[0] = true;
			solve(0,100,k,checked,cityList);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static void solve(int idx, int sum, int k, boolean[] checked,
			Stack<Integer> checkedCity) {
		if(k < 0) {
			String ret = "";
			String[] tmp = checkedCity.toString().replace("[", "").replace("]","").replace(" ", "").split(",");
			
			while(!checkedCity.isEmpty()) {
				ret = String.valueOf(((char)(checkedCity.pop()+60)))+" "+ret;
			}
			System.out.println(ret+" "+sum+" "+k);
			return;
		}
		if(idx == n-1) {
			String ret = "";
			System.out.println(checkedCity.toString());
			while(!checkedCity.isEmpty()) {
				ret = String.valueOf((char)(checkedCity.pop()+65))+" "+ret;
			}
			System.out.println(ret+" "+sum+" "+k);
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(i == idx) {
				continue;
			}
			if(path[idx][i] != -1 && !checked[i]) {
				System.out.println(idx+">>"+i);
				checked[i] = true;
				checkedCity.add(i);
				if(idx == 0) {
					solve(i,sum-100,k-path[idx][i],checked,checkedCity);
				} else {
					solve(i,sum+200,k+10-path[idx][i],checked,checkedCity);
				}
				checked[i] = false;
				checkedCity.pop();
			}
		}
	}
}
