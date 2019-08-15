package algospot.KAKURO2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see https://algospot.com/judge/problem/read/KAKURO2 
 * @author jun
 * input
1
8
0 0 0 0 0 0 0 0
0 1 1 0 0 1 1 1
0 1 1 0 1 1 1 1
0 1 1 1 1 1 0 0
0 0 1 1 0 1 1 0
0 0 0 1 1 1 1 1
0 1 1 1 1 0 1 1
0 1 1 1 0 0 1 1
24
2 1 0 16
2 5 0 24
3 1 0 17
3 4 0 29
4 1 0 35
5 2 0 7
5 5 0 8
6 3 0 16
7 1 0 21
7 6 0 5
8 1 0 6
8 6 0 3
1 2 1 23
1 3 1 30
1 6 1 27
1 7 1 12
1 8 1 16
2 5 1 17
3 4 1 15
4 7 1 12
5 5 1 7
5 8 1 7
6 2 1 11
6 3 1 10

 * output
0 0 0 0 0 0 0 0
0 9 7 0 0 8 7 9
0 8 9 0 8 9 5 7
0 6 8 5 9 7 0 0
0 0 6 1 0 2 6 0
0 0 0 4 6 1 3 2
0 8 9 3 1 0 1 4
0 3 1 2 0 0 2 1
 */
public class Main {
	//bitmask로 값들의 합과 갯수를 저장함
	static int[] calLen= new int[1024], calSum = new int[1024];
	//숫자칸 여부 저장 배열
	static int[][] color;
	static int[][] value;
	//hint[i][j][0 or 1] : (i,j)에 대해서 0일때 가로, 1일때 세로 힌트 
	static int[][][] hint;
	static int[][][] candidate = new int[10][46][1024];
	static int n;
	static HashMap<Integer, Hint> hintMap;
	
	static class Hint {
		//해당 힌트의 현재 숫자 길이
		int len;
		//해당 힌트의 숫자 총 합
		int sum;
		//해당 힌트에 넣은 숫자들
		int known;
		
		Hint(int len, int sum, int known) {
			this.len = len;
			this.sum = sum;
			this.known = known;
		}
		
		@Override
		public String toString() {
			return "{" + this.len + "," + this.sum + "," + this.known + "}";
		}
	}
	
	static int getLen(int tmp) {
		return calLen[tmp];
	}
	
	static int getSum(int tmp) {
		return calSum[tmp];
	}
	
	//값에 대한 길이와 합 계
	static void preLenAndSum() {
		for(int val = 0; val < 1024; val++) {
			for(int i = 0; i < 10; i++) {
				if((val & (1<<i)) > 0) {
					calSum[val] += i;
					calLen[val]++;
				}
			}
		}
	}
	
	//길이, 합, 들어있는 숫자들이 있을때 들어갈 수 있는 숫자 후보 계산
	static void preCal() {
		for(int set = 0; set < 1024; set+=2) {
			int len = getLen(set);
			int sum = getSum(set);
			int subset = set;
			
			while(true) {
				candidate[len][sum][subset] |= (set & ~subset);
				if(subset == 0) {
					break;
				}
				
				subset = (subset-1)&set;
			}
		}
	}
	
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		preLenAndSum();
		preCal();
		try {
			int t = Integer.parseInt(bf.readLine());
			
			for(int tc = 0; tc < t; tc++) {
				hintMap = new HashMap<>();
				n = Integer.parseInt(bf.readLine());
				color = new int[n][n];
				value = new int[n][n];
				hint = new int[n][n][2];
				for(int i = 0; i < n; i++) {
					color[i] = Arrays.stream(bf.readLine().split(" "))
							.mapToInt(Integer::valueOf)
							.toArray();
				}
				int hintCnt = Integer.parseInt(bf.readLine());
				for(int i = 0; i < hintCnt; i++) {
					int[] hintTmp = Arrays.stream(bf.readLine().split(" "))
							.mapToInt(Integer::valueOf).toArray();
					int y, x;
					if(hintTmp[2] == 0) {
						y = hintTmp[0] - 1;
						x = hintTmp[1];
					} else {
						y = hintTmp[0];
						x = hintTmp[1] - 1;
					}
					//hint에 대한 정보 저장(len, sum, known)
					hintMap.put(i, new Hint(0, hintTmp[3], 0));
					//가로 또는 세로줄에 해당 힌트 인덱스 삽입
					while(true) {
						//판 범위를 넘어간 경우 정지
						if((y >= n || x >= n) || color[y][x] == 0) {
							break;
						}
						//해당 힌트칸에 힌트 인덱스 넣음
						hint[y][x][hintTmp[2]] = i;
						if(hintTmp[2] == 0) {
							x++;
						} else {
							y++;
						}
						//힌트 길이 추가
						hintMap.get(i).len++;
					}
				}
				
				search();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static int getCandidate(int y, int x) {
		//세로 힌트
		Hint tmpHint0 = hintMap.get(hint[y][x][0]);
		//가로 힌트
		Hint tmpHint1 = hintMap.get(hint[y][x][1]);
		//세로 힌트와 가로힌트 공통 후보 반환
		return candidate[tmpHint0.len][tmpHint0.sum][tmpHint0.known] &
				 candidate[tmpHint1.len][tmpHint1.sum][tmpHint1.known];
	}
	
	static boolean search() {
		int x = -1;
		int y = -1;
		int minCandidate = 1023;
		
		//값을 쓰지 않은 칸중 후보의 길이가 제일 짧은 칸 검색
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(color[i][j] == 1 && value[i][j] == 0) {
					int tmpCandidate = getCandidate(i,j);
					if(calLen[minCandidate] > calLen[tmpCandidate]) {
						y = i;
						x = j;
						minCandidate = tmpCandidate;
					}
				}
			}
		}

		//칸을 채울수 없는 경우
		if(minCandidate == 0) {
			return false;
		}
		
		//칸을 다 채운경우 출력 후 종료
		if(y == -1) {
			printAnswer();
			return true;
		}


		for(int val = 1; val <= 9; val++) {
			if((minCandidate & (1 << val)) >= 1) {
				//해당 칸에 숫자 입력
				setValue(y,x,val);
				//칸을 다 채운경우 종료
				if(search()) {
					return true;
				}
				//해당 칸에 숫자 삭제
				removeValue(y,x,val);
			}
		}
		
		return false;
	}
	
	static String convertCandidateToString(int candidate) {
		List<Integer> list = new ArrayList<>();
		for(int i = 1; i <= 9; i++) {
			if((candidate & (1<<i)) > 1) {
				list.add(i);
			}
		}
		return list.toString();
	}
	
	//해당 칸에 숫자 입력
	static void setValue(int y, int x, int val) {
		for(int i = 0; i < 2; i++) {
			hintMap.get(hint[y][x][i]).known += (1<<val);
		}
		value[y][x] = val;
	}

	//해당 칸에 입력된 숫자 초기화
	static void removeValue(int y, int x, int val) {
		for(int i = 0; i < 2; i++) {
			hintMap.get(hint[y][x][i]).known -= (1<<val);
		}
		value[y][x] = 0;
	}
	
	static void printAnswer() {
		for(int i = 0; i < n; i++) {
			
			String rowStr = Arrays.toString(value[i]);
			rowStr = rowStr.replace("[", "").replace("]", "").replace(",", "");
			System.out.println(rowStr);
		}
	}
}
