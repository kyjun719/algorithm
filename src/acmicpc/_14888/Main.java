package acmicpc._14888;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see 
 * @author jun
 *
 */
public class Main {
	private static int min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(br.readLine());
			int[] arr = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			//+ - * /
			int[] op = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			solve(1, arr[0], arr, op);
			
			System.out.println(max);
			System.out.println(min);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void solve(int idx, int num, int[] arr, int[] op) {
		if(idx == arr.length) {
			max = Math.max(max, num);
			min = Math.min(min, num);
			
			return;
		}
		
		if(op[0] != 0) {
			int[] nextOp = Arrays.copyOf(op, 4);
			nextOp[0]--;
			solve(idx+1,num+arr[idx],arr,nextOp);
		}
		
		if(op[1] != 0) {
			int[] nextOp = Arrays.copyOf(op, 4);
			nextOp[1]--;
			solve(idx+1,num-arr[idx],arr,nextOp);
		}
		
		if(op[2] != 0) {
			int[] nextOp = Arrays.copyOf(op, 4);
			nextOp[2]--;
			solve(idx+1,num*arr[idx],arr,nextOp);
		}
		
		if(op[3] != 0) {
			int[] nextOp = Arrays.copyOf(op, 4);
			nextOp[3]--;
			solve(idx+1,num/arr[idx],arr,nextOp);
		}
	}
}
