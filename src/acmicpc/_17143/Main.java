package acmicpc._17143;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @see 17143
 * @author jun
 *
 */
public class Main {
	private static class Shark {
		int y,x,s,d,z;
		Shark(int y, int x, int s, int d, int z) {
			this.y = y;
			this.x = x;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		@Override
		public String toString() {
			return "("+y+","+x+")>>"+s+"("+d+")";
		}
		@Override
		public boolean equals(Object o) {
			if(o instanceof Shark) {
				Shark obj = (Shark)o;
				return (obj.y == y) && (obj.x == x);
			}
			return false;
		}
		@Override
		public int hashCode() {
			int ret = 17;
			ret = ret*31+x;
			ret = ret*31+y;
			return ret;
		}
	}
	static int r,c;
	static int[][] board;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			r = info[0];
			c = info[1];
			board = new int[r][c];
			int m = info[2];
			Queue<Shark> sharkQueue = new LinkedList<>();
			for(int i = 0; i < m; i++) {
				int[] tmp = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				sharkQueue.add(new Shark(tmp[0]-1,tmp[1]-1,tmp[2],tmp[3],tmp[4]));
				board[tmp[0]-1][tmp[1]-1] = tmp[4];
			}
			//showBoard();
			System.out.println(solve(sharkQueue));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static int solve(Queue<Shark> sharkQueue) {
		int ret = 0;
		for(int x = 0; x < c; x++) {
			if(sharkQueue.isEmpty()) {
				break;
			}
			
			int catchY = -1;
			for(int y = 0; y < r; y++) {
				if(board[y][x] != 0) {
					catchY = y;
					ret += board[y][x];
					board[y][x] = 0;
					break;
				}
			}
			
			int[][] nextBoard = new int[r][c];
			ArrayList<Shark> nextSharkList = new ArrayList<>();
			while(!sharkQueue.isEmpty()) {
				Shark shark = sharkQueue.poll();
				if((shark.y == catchY) && (shark.x==x)) {
					continue;
				}
				int max,start,nextY,nextX;
				if(shark.d <= 2) {
					max = r-1;
					start = shark.y;
					nextX = shark.x;
					
					nextY = shark.d==1?start-shark.s:start+shark.s;
					if(nextY < 0) {
						nextY = -nextY;
						shark.d = 2;
					}
					if(nextY > max) {
						if((nextY/max)%2==0) {
							shark.d = 2;
							nextY = nextY%max;
						} else {
							shark.d = 1;
							nextY = max-nextY%max;
						}
					}
				} else {
					max = c-1;
					start = shark.x;
					nextY = shark.y;
					
					nextX = shark.d==4?start-shark.s:start+shark.s;
					if(nextX < 0) {
						nextX = -nextX;
						shark.d = 3;
					}
					if(nextX > max) {
						if((nextX/max)%2==0) {
							shark.d = 3;
							nextX = nextX%max;
						} else {
							shark.d = 4;
							nextX = max-nextX%max;
						}
					}
				}
				//System.out.println(shark.y+","+shark.x+">>"+nextY+","+nextX+"::"+shark.d+","+shark.s+","+max);
				boolean isAdd = true;
				if(nextBoard[nextY][nextX] != 0) {
					if(nextBoard[nextY][nextX] < shark.z) {
						nextSharkList.remove(new Shark(nextY,nextX,0,0,0));
					} else {
						isAdd = false;
					}
				}
				if(isAdd) {
					shark.x = nextX;
					shark.y = nextY;
					nextSharkList.add(shark);
					nextBoard[nextY][nextX] = shark.z;
				}
			}
			board = nextBoard;
			sharkQueue.addAll(nextSharkList);
			//showBoard();
		}
		return ret;
	}
	private static void showBoard() {
		// TODO Auto-generated method stub
		for(int i = 0; i < r; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println("=========================");
	}
}
