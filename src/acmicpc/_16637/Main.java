package acmicpc._16637;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			String formula = br.readLine();
			boolean[] isChecked = new boolean[n];
			System.out.println(solve(0,isChecked,formula));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve(int idx, boolean[] isChecked, String formula) {
		if(idx >= n) {
			return calc(isChecked, formula);
		}
		
		int ret = solve(idx+2, isChecked, formula);
		if(idx+2 < n) {
			isChecked[idx] = true;
			ret = Math.max(ret, solve(idx+4, isChecked, formula));
			isChecked[idx] = false;
		}
		
		return ret;
	}

	private static int calc(boolean[] isChecked, String formula) {
		long ret = 0;
		Queue<String> queue = new LinkedList<>();
		for(int i = 0; i < n; i++) {
			if(i%2 == 1 || !isChecked[i]) {
				queue.add(String.valueOf(formula.charAt(i)));
				continue;
			}

			int tmp = 0;
			int a = formula.charAt(i)-48;
			int b = formula.charAt(i+2)-48;
			switch(formula.charAt(i+1)) {
				case '+':
					tmp = a+b;
					break;
				case '-':
					tmp = a-b;
					break;
				case '*':
					tmp = a*b;
					break;
			}
			queue.add(String.valueOf(tmp));
			i+=2;
		}
		
		ret = Integer.valueOf(queue.poll());
		while(!queue.isEmpty()) {
			String tmp = queue.poll();
			if(tmp.equals("-")) {
				ret -= Integer.valueOf(queue.poll());
			} else if(tmp.equals("+")) {
				ret += Integer.valueOf(queue.poll());
			} else {
				ret *= Integer.valueOf(queue.poll());
			}
		}
		return (int)ret;
	}
}
