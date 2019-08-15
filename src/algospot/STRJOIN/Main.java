package algospot.STRJOIN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @see https://algospot.com/judge/problem/read/STRJOIN
 * @author jun
 * input
3
3
2 2 4
5
3 1 3 4 1
8
1 1 1 1 1 1 1 2

 * output
12
26
27
 */
/*
 * a,b,c, a+b<c
 * 1) 탐욕적 선택속성 
 * a+b+(a+b)+c ? a+c+(a+c)+b => 3a+3b<2a+2b+c, 4a+3b<2a+b+2c이므로
 * a+b+(a+b)+c < a+c+(a+c)+b
 * 2) 최적 부분 구조
 * 1)에서 보인대로 최소값만 사용하는것이 이득
 */
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
