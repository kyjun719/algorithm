package algospot.STRJOIN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			
			for(int tc = 0; tc < c; tc++) {
				int n = Integer.parseInt(bf.readLine());
				Stack<Integer> tmp = new Stack<>();
				tmp.addAll(Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.boxed()
						.collect(Collectors.toList()));
				int ret = 0;
				while(tmp.size() > 1) {
					//정렬 후 최소 두개값만 합침
					tmp.sort(new Comparator<Integer>() {
						@Override
						public int compare(Integer arg0, Integer arg1) {
							return arg1 - arg0;
						}
					});
					
					int a = tmp.pop();
					int b = tmp.pop();
					tmp.push(a+b);
					ret += a+b;
				}
				System.out.println(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
