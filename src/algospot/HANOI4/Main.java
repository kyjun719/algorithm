package algospot.HANOI4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * timeout
 */
public class Main {
	//양방향탐색 상태값 저장배열
	private static int[] c;
	
	//base상태에서 idx번째 원반을 row번째 기둥으로 옮김
	static int set(int base, int idx, int row) {
	    return (base & ~(3 << (idx*2))) | (row << (idx*2));
	}
	
	//base상태에서 idx번째 원반이 있는 기둥 반환
	static int get(int base, int idx) {
	    return (base >> (idx*2)) & 3;
	}
	
	//부호 판별
	static int sgn(int n) {
		if(n == 0) {
			return 0;
		}
		
		return n>0?1:-1;
	}
	
	//부호에 따른 증가된값 반환
	static int incr(int n) {
		return n<0?n-1:n+1;
	}
	
	//양방향탐색
	static int bidir(int N, int start, int end) {
		//시작과 종료가 같은경우 0 반환
		if(start == end) {
			return 0;
		}
		
		//정방향인경우 양수, 역방향인경우 음수이므로 0으로 초기화
		c = new int[1 << N * 2];
		
		//시작값과 종료값 큐에 저장
		Queue<Integer> q = new LinkedList<>();
		c[start] = 1;
		c[end] = -1;
		q.add(start);
		q.add(end);
		
		while(!q.isEmpty()) {
			int now = q.poll();
			//원반 번호가 큰순서로 순회하여 기둥에 어떤 원반이 가장 작은지 검색 
			int[] top = new int[]{-1, -1, -1, -1};
	        for(int i = N-1; i>=0; --i) {
	        	top[get(now, i)] = i;
	        }
	        
	        for(int i = 0; i<4; i++) {
	        	for(int j = 0; j<4; j++) {
	        		//i번째 기둥에서 j번째 기둥으로 원반 이동이 가능한 경우
	        		if(i != j && top[i] != -1 && (top[j] == -1 || top[j] > top[i])) {
	                    int there = set(now, top[i], j);
	                    //해당 상태를 탐색하지 않은경우 큐에 추가
	                    if(c[there] == 0) {
	                        c[there] = incr(c[now]);
	                        q.add(there);
	                    } else if(sgn(c[there]) != sgn(c[now])) {
	                    	//정방향 탐색과 역방향 탐색이 만난 경우 종료
	                    	return Math.abs(c[there]) + Math.abs(c[now]) -1;
	                    }
	                }
	        	}
	        }
	            
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				int N = Integer.parseInt(br.readLine());
				
				int now = 0, end = 0;
				for(int i = 0; i < 4; i++) {
					String[] tmp = br.readLine().split(" ");
					int n = Integer.parseInt(tmp[0]);
					//현재 원반의 상태 초기화
					for(int j = 0; j < n; j++) {
						now = set(now, Integer.parseInt(tmp[j+1])-1, i);
					}
				}
				//종료상태 초기화
				end = (1 << N*2) - 1;
				System.out.println(bidir(N, now, end));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
