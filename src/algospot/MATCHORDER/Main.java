package algospot.MATCHORDER;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int c = Integer.parseInt(bf.readLine());
			for(int tc = 0; tc < c; tc++) {
				int n = Integer.parseInt(bf.readLine());
				//러시아팀 레이팅 내림차순 정렬
				List<Integer> russian = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.sorted()
						.boxed()
						.collect(Collectors.toList());
				//한국팀 레이팅 내림차순 정렬
				List<Integer> korean = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.sorted()
						.boxed()
						.collect(Collectors.toList());
				int wins = 0;
				for(int i = 0; i < n; i++) {
					//러시아팀의 레이팅이 한국팀의 가장 높은 레이팅보다 높은경우
					//한국팀의 레이팅이 가장 낮은팀 보냄
					if(russian.get(i) > korean.get(korean.size() - 1)) {
						korean.remove(0);
					} else {
						for(int j = 0; j < korean.size(); j++) {
							//러시아팀보다 가장 근소하게 높은 한국팀 보냄
							if(russian.get(i) <= korean.get(j)) {
								korean.remove(j);
								wins++;
								break;
							}
						}
					}
				}
				System.out.println(wins);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
