package kaako.test2019;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @see https://programmers.co.kr/learn/courses/30/lessons/42889
 * @author jun

5
2, 1, 2, 6, 2, 4, 3, 3

3,4,2,1,5

4
4,4,4,4,4

4,1,2,3

 */
public class Failure {
	/*
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(br.readLine());
			int[] arr = Arrays.stream(br.readLine().replace(" ", "").split(","))
					.mapToInt(Integer::parseInt)
					.toArray();
			System.out.println(Arrays.toString(new Failure().solution(n, arr)));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//*/
	private class Stage {
		int num,here;
		double failure;
		public Stage(int num) {
			this.num = num;
		}
		@Override
		public String toString() {
			return "{num:"+num+",here:"+here+",failure:"+failure+"}";
		}
	}
	public int[] solution(int N, int[] stages) {
		int user = stages.length;
		Stage[] cnt = new Stage[N];
		for(int i = 0; i < N; i++) {
			Stage tmp = new Stage(i+1);
			cnt[i] = tmp;
		}
		for(int s : stages) {
			if(s > N) {
				continue;
			}
			cnt[s-1].here++;
		}
		for(int i = 0; i < cnt.length; i++) {
			if(user == 0) {
				cnt[i].failure = 0;
			} else {
				cnt[i].failure = ((double)cnt[i].here)/user;
				user -= cnt[i].here;
			}
		}
		Arrays.sort(cnt, new Comparator<Stage>() {
			@Override
			public int compare(Stage arg0, Stage arg1) {
				// TODO Auto-generated method stub
				if(arg0.failure == arg1.failure) {
                    return arg0.num-arg1.num;
                }
				return arg0.failure>arg1.failure?-1:1;
			}
		});
		//System.out.println(Arrays.toString(cnt));
		int[] answer = new int[N];
		for(int i = 0; i < cnt.length; i++) {
			answer[i] = cnt[i].num;
		}
		return answer;
    }
}
