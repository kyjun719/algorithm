package algospot.FESTIVAL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int c = Integer.parseInt(br.readLine());
			for(int t = 0; t < c; t++) {
				int[] tmp = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				int n = tmp[0];
				int l = tmp[1];
				int[] arr = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				
				double ret = 987654321;
				for(int start = 0; start < n; start++) {
					//start~end까지의 구간합
					double sum = 0;
					for(int end = start; end < n; end++) {
						sum += arr[end];
						if(end-start+1 >= l) {
							//갯수가 l개 이상인 경우 평균값 계산
							double avg = sum/(end-start+1);
							ret = ret > avg?avg:ret;
						}
					}
				}
				System.out.println(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
