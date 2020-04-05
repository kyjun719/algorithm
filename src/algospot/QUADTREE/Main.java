package algospot.QUADTREE;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t= 0; t < tc; t++) {
				String input = br.readLine();
				System.out.println(solve(0, input));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static String solve(int idx, String input) {
		//System.out.println(idx+">>"+input);		

		if(input.charAt(idx) == 'x') {
			idx++;
		} else {
			return input.substring(idx, idx+1);
		}
		
		int cnt = idx;
		String rt = solve(cnt, input);
		cnt += rt.length();
		
		String lt = solve(cnt, input);
		cnt += lt.length();
		
		String rb = solve(cnt, input);
		cnt += rb.length();
		
		String lb = solve(cnt, input);
		String ret = rb+lb+rt+lt;
		return "x"+ret;
	}
}
