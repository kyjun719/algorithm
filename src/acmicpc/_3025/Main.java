package acmicpc._3025;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
	private static Stack<Integer>[] col;
	private static int y,x;
	private static char[][] board;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int r = info[0];
		int c = info[1];
		board = new char[r][c];
		for(int i = 0; i < r; i++) {
			board[i] = br.readLine().toCharArray();
		}
		int n = Integer.parseInt(br.readLine());
		//각 컬럼에서 갈 수 있는 점들을 순서대로 쌓은 스택
		col = new Stack[c];
		for(int i = 0; i < col.length; i++) {
			col[i]=new Stack<>();
		}
		
		for(int i = 0; i < n; i++) {
			int start=Integer.parseInt(br.readLine())-1;
			y=0;
			x=0;
			while(true) {
				//진행할 점이 없는 경우
				if(col[start].isEmpty()) {
					break;
				}
				//진행할 점이 있는 경우 확인
				int p = col[start].peek();
				y=p/c;
				x=p%c;
				//꺼낸점에서 더 진행할 수 있는 경우 종료
				if(board[y][x]=='.') {
					break;
				}
				//막혀있는 경우 비어있는점이 나오거나 갈수있는 점이 없을때까지 반복, 다음점 확인을 위해 스택에서 꺼냄
				col[start].pop();
			}
			//위에서 종료한 점 이후에 더 진행할 수 있는지 확인
			update(start, r, c);
			//해당컬럼에서 시작할 때 도착하는 점 확인
			int p = col[start].pop();
			y=p/c;
			x=p%c;
//			System.out.println("moved::"+y+","+x);
			//도착하는점에 돌을 놓음
			board[y][x]='O';
		}
		for(int i = 0; i < r; i++) {
			System.out.println(new String(board[i]));
		}
	}
	
	private static void update(int start, int r, int c) {
		y = 0;
		x = start;
		//갈수 있는 점이 있는경우 해당점부터 시작
		if(!col[start].isEmpty()) {
			int p = col[start].peek();
			y=p/c;
			x=p%c;
		}
		while(y < r) {
			//이동할 점 저장
			col[start].add(y*c+x);
			//한칸 내려갔을 때 장애물에 도착한 경우 종료
			if(y+1<r && board[y+1][x]=='X') {
				return;
			}
			//다음칸이 돌인 경우, 왼쪽과 오른쪽으로 내려갈 수 있는지 확인
			if(y+1<r && board[y+1][x]=='O') {
				//왼쪽으로 진행가능한 경우 왼쪽으로 이동
				if(x-1>=0 && board[y][x-1]=='.' && board[y+1][x-1]=='.') {
					x--;
				}
				//오른쪽으로 진행가능한 경우 오른쪽으로 이동
				else if(x+1<c && board[y][x+1]=='.' && board[y+1][x+1]=='.') {
					x++;
				}
				//움직이지 못하는 경우 종료
				else {
					return;
				}
			}
			//탐색을 계속 해야 하는 경우 다음줄로 이동
			y++;
		}
	}
}
