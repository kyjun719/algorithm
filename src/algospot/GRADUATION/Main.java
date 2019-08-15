package algospot.GRADUATION;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/GRADUATION
 * @author jun
 * input
2
4 4 4 4
0
1 0
3 0 1 3
0
4 0 1 2 3 
4 0 1 2 3
3 0 1 3
4 0 1 2 3
4 2 2 4
1 1
0
1 3
1 2
3 0 2 3
3 1 2 3

 * output
3
IMPOSSIBLE
 */
public class Main {
	//n:전공과목수, k:들어야할 과목수, m:학기수, l:한학기에 최대로 들을수 있는 과목수
	private static int n, k, m, l;
	//subject[i] : i번째 과목의 선수과목
	private static int[] subject;
	//session[i] : i번째 학기에 개설되는 과목
	private static int[] session;
	
	private static int[][] cache;
	
	private static int INF = 987654321;
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int tc = Integer.parseInt(br.readLine());
			
			for(int t = 0; t < tc; t++) {
				int[] info = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				n = info[0];
				k = info[1];
				m = info[2];
				l = info[3];
				
				cache = new int[m][1<<n];
				
				//선수과목 정보 저
				subject = new int[n];
				for(int i = 0; i < n; i++) {
					int[] r = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
					for(int j = 0; j < r[0]; j++) {
						subject[i] |= (1<<r[j+1]);
					}
				}
				
				//학기중 열리는 과목정보 저잦
				session = new int[m];
				for(int i = 0; i < m; i++) {
					Arrays.fill(cache[i], -1);
					int[] c = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
					for(int j = 0; j < c[0]; j++) {
						session[i] |= (1<<c[j+1]);
					}
				}
				
				int answer = solve(0, 0);
				
				System.out.println(answer == INF ? "IMPOSSIBLE" : answer);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static int solve(int sessionCnt, int subjectList) {
		//졸업이 가능한 경우
		if(Integer.bitCount(subjectList) >= k) {
			return 0;
		}
		
		//학기를 다들은 경
		if(sessionCnt == m) {
			return INF;
		}
		
		if(cache[sessionCnt][subjectList] != -1) {
			return cache[sessionCnt][subjectList];
		}
		
		int ret = INF;
		
		//해당 학기 과목중 안들은 과목
		int canTaken = session[sessionCnt] & ~subjectList;
		//해당 학기에 들을수 있는 과목중 선수과목을 안들은 과목 제
		for(int i = 0; i < n; i++) {
			if(((canTaken & (1<<i)) >= 1) && ((subjectList & subject[i]) != subject[i])) {
				canTaken &= ~(1<<i);
			}
		}
		
		//들을수 있는 과목 조합 순회
		for(int take = canTaken; take > 0; take=((take-1) & canTaken)) {
			//한학기당 최대 과목을 넘은 경우 넘어
			if(Integer.bitCount(take) > l) {
				continue;
			}
			ret = Math.min(ret, solve(sessionCnt+1, subjectList | take) + 1);
		}
		//해당학기에 과목을 안듣고 넘어감
		ret = Math.min(ret, solve(sessionCnt+1, subjectList));
		
		cache[sessionCnt][subjectList] = ret;
		return ret;
	}	
}
