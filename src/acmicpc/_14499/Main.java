package acmicpc._14499;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://www.acmicpc.net/problem/14499
 * @author jun
 *
 */
public class Main {
	static int n,m,x,y,k;
	static int[][] board;
	static int[] order;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int[] info = Arrays.stream(br.readLine().split(" "))
								.mapToInt(Integer::parseInt)
								.toArray();
			n = info[0];
			m = info[1];
			y = info[2];
			x = info[3];
			k = info[4];
			board = new int[n][m];
			for(int i = 0; i < n; i++) {
				board[i] = Arrays.stream(br.readLine().split(" "))
								.mapToInt(Integer::parseInt)
								.toArray();
			}
			order = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			solve();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int[][] dir = {{},{0,1},{0,-1},{-1,0},{1,0}};
	private static void solve() {
		int[] dice = new int[7];
		int d=6,e=3,w=4,n=5,s=2,u=1;
		for(int i = 0; i < k; i++) {
			int nextY = y + dir[order[i]][0];
			int nextX = x + dir[order[i]][1];
			//System.out.println(y+","+x+">>"+nextY+","+nextX);
			if(!isInBound(nextX, nextY)) {
				continue;
			}
			
			y = nextY;
			x = nextX;
			
			int tmp;
			switch(order[i]) {
				case 1:
					//east
					//e->d->w->u->
					tmp = u;
					u = w;
					w = d;
					d = e;
					e = tmp;
					break;
				case 2:
					//west
					//w->d->e->u->
					tmp = u;					
					u = e;
					e = d;
					d = w;
					w = tmp;
					break;
				case 3:
					//north
					//n->d->s->u->
					tmp = u;
					u = s;
					s = d;
					d = n;
					n = tmp;
					break;
				case 4:
					//south
					//s->d->n->u->
					tmp = u;
					u = n;
					n = d;
					d = s;
					s = tmp;
					break;
			}
			
			if(board[y][x] == 0) {
				board[y][x] = dice[d];
			} else {
				dice[d] = board[y][x];
				board[y][x] = 0;
			}
			System.out.println(dice[u]);
		}
	}
	
	private static boolean isInBound(int x, int y) {
		return (x >= 0) && (x < m) && (y >= 0) && (y < n);
	}
}
