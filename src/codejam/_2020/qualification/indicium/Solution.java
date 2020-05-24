package codejam._2020.qualification.indicium;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	/*
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 1; t <= tc; t++) {
				int[] tmp = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				n = tmp[0];
				k = tmp[1];
				arr = new int[n][n];
				solve(new int[n][n], 0, 0);
				
				if(arr[0][0] == 0) {
					System.out.println("Case #"+t+": IMPOSSIBLE");
				} else {
					System.out.println("Case #"+t+": POSSIBLE");
					StringBuilder sb = new StringBuilder();
					for(int i = 0; i < n; i++) {
						sb.append(Arrays.toString(arr[i]).replace("[", "").replace("]", "").replace(",", "")+"\n");
					}
					System.out.print(sb.toString());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	static int[][] arr;
	static int n, k;
	private static void solve(int[][] val, int pos, int sum) {
		if(arr[0][0] != 0) {
			return;
		}
		
		if(pos == (n*n)) {
			if(sum == k ) {
				for(int i = 0; i < n; i++) {
					arr[i] = Arrays.copyOf(val[i], n);
				}
			}
			return;
		}
		int y = pos/n;
		int x = pos%n;
		
		int[] candidate = new int[n+1];
		for(int i = 0; i < n; i++) {
			candidate[val[y][i]]--;
			candidate[val[i][x]]--;
		}
		for(int i = 1; i < candidate.length; i++) {
			if(candidate[i] >= 0) {
				if(x==y) {
					sum+=i;
				}
				val[y][x] = i;
				solve(val, pos+1, sum);
				val[y][x] = 0;
				if(x==y) {
					sum-=i;
				}
			}
		}
	}
	*/
	
	static int n, k;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 1; t <= tc; t++) {
				int[] tmp = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				n = tmp[0];
				k = tmp[1];

				int a=0,b=0,c=0;
				for(int i = 1; i < (1<<19-1); i++) {
					int x = (i&(63<<12))>>12;
					int y = (i&(63<<6))>>6;
					int z = i&63;
					if(x>n || y>n || z>n || x==0 || y==0 || z==0) {
						continue;
					}
					if(k==(x*(n-2)+y+z)) {
						a=x;
						b=y;
						c=z;
						//break;
					}
				}
				
				int aCnt = n-2;
				int bCnt = 1;
				int cCnt = 1;
				
				if(a == 0 || b == 0 || c == 0 || aCnt == 0 || bCnt == 0 || cCnt == 0) {
					System.out.println("Case #"+t+": IMPOSSIBLE");
				} else {
					int[][] arr = new int[n][n];
					
					//System.out.println(a+","+b+","+c+">>"+aCnt+","+bCnt+","+cCnt);
					for(int y = 0; y < n; y++) {
						for(int x = 0; x < n; x++) {						
							int[] candidate = new int[n+1];
							for(int i = 0; i < n; i++) {
								candidate[arr[y][i]]--;
								candidate[arr[i][x]]--;
							}
							
							if(x == y) {
								if(candidate[a] >= 0 && aCnt > 0) {
									arr[y][x] = a;
									aCnt--;
								} else if(candidate[b] >= 0 && bCnt > 0) {
									arr[y][x] = b;
									bCnt--;
								} else {
									arr[y][x] = c;
									cCnt--;
								}
							} else {
								int cnt = 0;
								for(int i = 1; i <= n; i++) {
									if(candidate[i]>=0) {
										cnt++;
									}
								}

								for(int i = 1; i <= n; i++) {
									if(candidate[i] >= 0) {
										arr[y][x] = i;
										break;
									}
								}
							}
						}
					}
					
					System.out.println("Case #"+t+": POSSIBLE");
					StringBuilder sb = new StringBuilder();
					for(int i = 0; i < n; i++) {
						sb.append(Arrays.toString(arr[i]).replace("[", "").replace("]", "").replace(",", "")+"\n");
					}
					System.out.print(sb.toString());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
