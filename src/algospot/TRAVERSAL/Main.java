package algospot.TRAVERSAL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
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
		//전위 순회값이 없는경우 리턴
		if(pre.isEmpty()) {
			return;
		}
		
		int n = pre.size();
		//루트
		int c = pre.get(0);
		//중위에서의 루트 위치
		int cp = in.indexOf(c);
		//왼쪽자손 길이
		int L = cp;
		int R = n - L -1;
		if(n > 1) {
			//왼쪽 후위순회
			getPostOrder(pre.subList(1, L+1), in.subList(0, L));
			//오른쪽 후위순회
			getPostOrder(pre.subList(L+1, n), in.subList(L+1, n));
		}
		//루트 출력
		System.out.print(c + " ");
	}
}
