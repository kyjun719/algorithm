package algospot.OCR;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see https://algospot.com/judge/problem/read/OCR
 * @author jun
 * input
5 3
I am a boy buy
1.0 0.0 0.0 0.0 0.0
0.1 0.6 0.1 0.1 0.1
0.1 0.1 0.6 0.1 0.1
0.1 0.1 0.1 0.6 0.1
0.2 0.2 0.2 0.2 0.2
0.2 0.2 0.2 0.2 0.2
0.8 0.1 0.0 0.1 0.0
0.1 0.7 0.0 0.2 0.0
0.0 0.1 0.8 0.0 0.1
0.0 0.0 0.0 0.5 0.5
0.0 0.0 0.0 0.5 0.5
4 I am a buy
4 I I a boy
4 I am am boy

 * output
I am a boy
I am a boy
I am a boy

베이즈 정리
조건부확률 P(Q|R)=P(R|Q)*P(Q)/P(R)
Q : 원본
R : 인식기가 인식한 문장
P(Q|R) : Q에서 R이 나올 확률
P(Q|R) = R에서 Q가 나올 확률*Q가 나올 확률	
		 = M[Q[i][R[i]]*(i==0? B[i]:T[Q[i-1]][Q[i]])
 */
public class Main {
	static int m,q,n;
	static List<String> words;
	//B : 첫단어로 출현할 확률
	static double[] B;
	//T : i번째 단어 다음 j번째 단어가 출현할 확률
	//M : i번째 단어를 j번째 단어로 분류할 확률
	static double[][] T,M;
	//인식한 단어의 단어 리스트의 인덱스 배열
	static int[] recog;
	//현재단어 인덱스가 i, 이전단어 인덱스가 j일때 countCache[i+1][j+1]이 나올 최대확률
	static double[][] cache;
	//현재단어 인덱스가 i, 이전단어 인덱스가 j일때 최대확률의 단어 인덱스
	static int[][] countCache;
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			String[] mq = bf.readLine().split(" ");
			m = Integer.parseInt(mq[0]);
			q = Integer.parseInt(mq[1]);

			words = Arrays.asList(bf.readLine().split(" "));
			//확률은 로그값으로 저장
			B = Arrays.stream(bf.readLine().split(" "))
					.mapToDouble(Double::parseDouble)
					.map(Math::log)
					.toArray();
			
			T = new double[m][m];
			for(int i = 0; i < m; i++) {
				T[i] = Arrays.stream(bf.readLine().split(" "))
						.mapToDouble(Double::parseDouble)
						.map(Math::log)
						.toArray();
			}
			
			M = new double[m][m];
			for(int i = 0; i < m; i++) {
				M[i] = Arrays.stream(bf.readLine().split(" "))
						.mapToDouble(Double::parseDouble)
						.map(Math::log)
						.toArray();
			}
			
			for(int i = 0; i < q; i++) {
				String[] tmp = bf.readLine().split(" ");
				n = Integer.parseInt(tmp[0]);
				cache = new double[n][m];
				countCache = new int[n+1][m+1];
				recog = new int[n];
				Arrays.fill(countCache[0], -1);
				for(int j = 1; j < tmp.length; j++) {
					recog[j-1] = words.indexOf(tmp[j]);
					Arrays.fill(cache[j-1], 1);
					Arrays.fill(countCache[j], -1);
				}
				
				recognize(0, -1);
				
				String answer = "";
				int seg = 1;
				int prev = 0;
				while(seg <= n) {
					answer += words.get(countCache[seg][prev]);
					answer += " ";
					prev = countCache[seg][prev]+1;
					seg++;
				}
				System.out.println(answer.substring(0, answer.length() - 1));
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static double MIN_MAX = -987654321;
	static double recognize(int seg, int prev) {
		//다 찾은 경우
		if(seg == n) {
			return 0;
		}
		
		if(prev != -1 && cache[seg][prev] != 1) {
			return cache[seg][prev];
		}
		
		double ret = MIN_MAX;
		int now_index = -1;
		//처음일 경우 처음 나타날 확률, 아닐경우 i번째 단어 다음 j번째 단어가 출현할 확률 사용
		double[] word_arr = prev == -1? B : T[prev];
		for(int now = 0; now < m; now++) {
			//now번째 단어를 seg번째 단어로 분류할 확률 + now번째 단어가 나타날 확률 
			double cal_result = M[now][recog[seg]] + word_arr[now];
			cal_result += recognize(seg+1, now);
			if(ret < cal_result) {
				now_index = now;
				ret = cal_result;
			}
		}
		
		if(prev != -1) {
			cache[seg][prev] = ret;
		}
		countCache[seg+1][prev+1] = now_index;
		return ret;
	}
}
