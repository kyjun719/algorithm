package algospot.TRAVERSAL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see https://algospot.com/judge/problem/read/TRAVERSAL
 * @author jun
 * input
2
7
27 16 9 12 54 36 72
9 12 16 27 36 54 72
6
409 479 10 838 150 441
409 10 479 150 838 441

 * output
12 9 16 36 72 54 27
10 150 441 838 479 409
 */
public class Main {
	private static int totalLen;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				totalLen = Integer.parseInt(br.readLine());
				List<Integer> pre = Arrays.stream(br.readLine().split(" "))
											.mapToInt(Integer::parseInt)
											.boxed()
											.collect(Collectors.toList());
				List<Integer> in = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.boxed()
						.collect(Collectors.toList());
				
				getPostOrder(pre, in);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void getPostOrder(List<Integer> pre, List<Integer> in) {
		// TODO Auto-generated method stub
		if(pre.isEmpty()) {
			return;
		}
		//System.out.println(pre+"::"+in);
		int n = pre.size();
		int c = pre.get(0);
		int cp = in.indexOf(c);
		int L = cp;
		int R = n - L -1;
		if(n > 1) {
			getPostOrder(pre.subList(1, L+1), in.subList(0, L));
			getPostOrder(pre.subList(L+1, n), in.subList(L+1, n));
		}
		//System.out.print(c + (n==totalLen?"":" "));
		System.out.print(c + " ");
		//System.out.println(c);
	}
}
