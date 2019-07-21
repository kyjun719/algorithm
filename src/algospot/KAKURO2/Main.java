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
	static int[] calLen= new int[1024], calSum = new int[1024];
	static int[][] color;
	static int[][] value;
	static int[][][] hint;
	static int[][][] candidate = new int[10][46][1024];
	static int n;
	static HashMap<Integer, Hint> hintMap;
	
	static class Hint {
		int len;
		int sum;
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

					hintMap.put(i, new Hint(0, hintTmp[3], 0));
					while(true) {
						if((y >= n || x >= n) || color[y][x] == 0) {
							break;
						}
						hint[y][x][hintTmp[2]] = i;
						if(hintTmp[2] == 0) {
							x++;
						} else {
							y++;
						}
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
		Hint tmpHint0 = hintMap.get(hint[y][x][0]);
		Hint tmpHint1 = hintMap.get(hint[y][x][1]);
		return candidate[tmpHint0.len][tmpHint0.sum][tmpHint0.known] &
				 candidate[tmpHint1.len][tmpHint1.sum][tmpHint1.known];
	}
	
	static boolean search() {
		int x = -1;
		int y = -1;
		int minCandidate = 1023;
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

		if(minCandidate == 0) {
			return false;
		}
		
		if(y == -1) {
			printAnswer();
			return true;
		}
		
		List<Integer> candidateList = new ArrayList<>();
		for(int i = 1; i <= 9; i++) {
			if((minCandidate & (1 << i)) > 1) {
				candidateList.add(i);
			}
		}

		for(int val = 1; val <= 9; val++) {
			if((minCandidate & (1 << val)) >= 1) {
				setValue(y,x,val);
				if(search()) {
					return true;
				}
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
	
	static void setValue(int y, int x, int val) {
		for(int i = 0; i < 2; i++) {
			hintMap.get(hint[y][x][i]).known += (1<<val);
		}
		value[y][x] = val;
	}
	
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
