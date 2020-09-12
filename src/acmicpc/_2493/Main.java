package acmicpc._2493;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] next = new int[n];
		Stack<Info> heightStack = new Stack<>();
		heightStack.add(new Info(0,987654321));
		for(int i = 0; i < n; i++) {
			if(heightStack.peek().height>arr[i]) {
				next[i]=heightStack.peek().idx;
			} else {
				while(true) {
					if(heightStack.peek().height<arr[i]) {
						heightStack.pop();
					} else {
						break;
					}
				}
				next[i]=heightStack.peek().idx;
			}
			heightStack.add(new Info(i+1,arr[i]));
//			System.out.println(i+">>"+bef+","+befIdx);
		}
		System.out.println(Arrays.toString(next).replace("[","").replace("]", "").replace(",", ""));
	}
	
	private static class Info {
		public Info(int i, int j) {
			idx=i;
			height=j;
		}
		int idx;
		int height;
	}
}
