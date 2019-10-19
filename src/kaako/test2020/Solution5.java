package kaako.test2020;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
5 8
1,0,0,1
1,1,1,1
2,1,0,1
2,2,1,1
5,0,0,1
5,1,0,1
4,2,1,1
3,2,1,1

1,0,0
1,1,1
2,1,0
2,2,1
3,2,1
4,2,1
5,0,0
5,1,0

5 10
0,0,0,1
2,0,0,1
4,0,0,1
0,1,1,1
1,1,1,1
2,1,1,1
3,1,1,1
2,0,0,0
1,1,1,0
2,2,0,1

0,0,0
0,1,1
1,1,1
2,1,1
3,1,1
4,0,0
 */
public class Solution5 {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			int n = info[0];
			int[][] frame = new int[info[1]][4];
			for(int i = 0; i < info[1]; i++) {
				frame[i] = Arrays.stream(br.readLine().split(","))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			new Solution5().solution(n, frame);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int[][] solution(int n, int[][] build_frame) {
		int[][] arr = new int[n+1][n+1];
		for(int i = 0; i < build_frame.length; i++) {
			System.out.println(Arrays.toString(build_frame[i]));
			for(int j =0;j<n+1;j++) {
				System.out.println(Arrays.toString(arr[j]));
			}
			System.out.println("------------------------");
			
			int x = build_frame[i][0];
			int y = build_frame[i][1];
			int a = build_frame[i][2];
			int b = build_frame[i][3];
			if(b == 0) {
				//delete
				if(a == 0) {
					if((y+2 <= n && (arr[y+2][x] == 1))) {
						continue;
					}
					
					System.out.println("?");
					
					if(x-1 >= 0 && (arr[y][x-1] == 1)) {
						boolean notDel = true;
						for(int j = x-1; j < 0; j--) {
							if(arr[y-1][j] == 1) {
								notDel = false;
								break;
							}
						}
						System.out.println("-->>"+notDel);
						if(notDel) {
							continue;
						}
					}
					System.out.println("?????");
					if(x+1 <= n && (arr[y][x+1] == 1)) {
						boolean notDel = true;
						for(int j = x+1; j >= n; j++) {
							if(arr[y-1][j] == 1) {
								notDel = false;
								break;
							}
						}
						System.out.println("++>>"+notDel);
						if(notDel) {
							continue;
						}
					}
					
					arr[y][x] = 0;
				} else {
					if(((y+1 <= n && (arr[y+1][x] == 1)) && (arr[y-1][x] == 0 || 
							(arr[y][x+1] == 1 && arr[y-1][x+1] == 0)))) {
						continue;
					}
					
					if(x-1 >= 0 && (arr[y][x-1] == 1)) {
						boolean notDel = true;
						for(int j = x-1; j < 0; j--) {
							if(arr[y-1][j] == 1) {
								notDel = false;
								break;
							}
						}
						
						if(notDel) {
							continue;
						}
					}
					
					if(x+1 <= n && (arr[y][x+1] == 1)) {
						boolean notDel = true;
						for(int j = x+1; j >= n; j++) {
							if(arr[y-1][j] == 1) {
								notDel = false;
								break;
							}
						}
						
						if(notDel) {
							continue;
						}
					}
					
					arr[y][x+1] = 0;
				}
			} else {
				//build
				if(a == 0) {
					if(y != 0 && 
							!((x-1 >= 0 && (arr[y][x-1] == 1)) || (x+1 <= n && (arr[y][x+1] == 1))
									|| (arr[y][x] == 1))) {
						continue;
					}
					
					arr[y][x] = 1;
					arr[y+1][x] = 1;
				} else {
					if(!((x-1 >= 0 && (arr[y][x-1] == 1)) || (x+1 <= n && (arr[y][x+1] == 1))
							|| (y-1 >= 0 && (arr[y-1][x] == 1)))) {
						continue;
					}
					
					arr[y][x+1] = 1;
				}
			}
		}
		for(int j =0;j<n+1;j++) {
			System.out.println(Arrays.toString(arr[j]));
		}
		System.out.println("------------------------");
		List<Integer[]> ret = new ArrayList<>();
		for(int y = 1; y < n+1; y++) {
			for(int x = 0; x < n+1; x++) {
				if((arr[y][x] == 1) && (arr[y-1][x] == 1)) {
					ret.add(new Integer[] {x,y,0});
				}
				
				if((arr[y][x] == 1) && (arr[y][x+1] == 1)) {
					ret.add(new Integer[] {x,y,1});
				}
			}
		}
		int[][] answer = new int[ret.size()][3];
		for(int i = 0; i < ret.size(); i++) {
			Integer[] tmp = ret.get(i);
			answer[i] = new int[] {tmp[0],tmp[1],tmp[2]};
		}
		return answer;
    }
}
