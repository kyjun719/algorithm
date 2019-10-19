package acmicpc._5373;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @see https://www.acmicpc.net/problem/5373
 * @author jun
 *
 */

/*
4
1
L-
2
F+ B+
4
U- D- L+ R+
10
L- U- L+ U- L- U- U- L+ U+ U+

//https://rubiks-cube-solver.com/fr/
1
16
B+ D- U+ F- R- B+ B- B- R- R- U+ L- L+ L- R+ B-
//B D' U F' R' B B' B' R' R' U L' L L' R B'

1
20
D+ D+ L+ L+ D+ D+ R+ R+ U+ R+ R+ D- L+ L+ U+ L- B+ D+ D+ F- R+ B- F+ D- B+ B+ F- U+
//D D L L D D R R U R R D' L L U L' B D D F' R B' F D' B B F' U

1
30
U+ U+ F+ F+ R+ R+ F+ F+ L+ L+ R+ R+ F+ F+ D- U- B- D- B+ B+ L+ L+ F- D+ D+ F+ L- B- D+ U+
//U U F F R R F F L L R R F F D' U' B' D' B B L L F' D D F L' B' D U


1
19
U+ U+ F+ F+ R+ R+ F+ F+ L+ L+ R+ R+ F+ F+ D- U- B- D- B+
//U U F F R R F F L L R R F F D' U' B' D' B
 */
public class Main {
	public static int U=0,D=1,L=2,R=3,F=4,B=5;
	public static char[] color = {'w','y','g','b','r','o'};
	//public static char[] color = {'w','y','o','r','g','b'};
	public static int[][][] plane;
	public static void init() {
		plane = new int[6][3][3];
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 3; j++) {
				for(int k = 0; k < 3; k++) {
					plane[U][j][k] = U;
					plane[D][j][k] = D;
					plane[L][j][k] = L;
					plane[R][j][k] = R;
					plane[F][j][k] = F;
					plane[B][j][k] = B;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				init();
				int n = Integer.parseInt(br.readLine());
				String[] order = br.readLine().split(" ");
				for(int i = 0; i < order.length; i++) {
					rotateCube(order[i]);
					/*
					System.out.println(order[i]);
					printPlane(U);
					System.out.println("=================");
					//*/
				}
				printPlane(U);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void printPlane(int num) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				sb.append(color[plane[num][i][j]]);
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

	private static void rotateCube(String order) {
		// TODO Auto-generated method stub
		char plane = order.charAt(0);
		char dir = order.charAt(1);
		
		//U=0,D=1,L=2,R=3,F=4,B=5;
		switch(plane) {
			case 'U':
				rotateUp(dir);
				break;
			case 'D':
				rotateDown(dir);
				break;
			case 'L':
				rotateLeft(dir);
				break;
			case 'R':
				rotateRight(dir);
				break;
			case 'F':
				rotateFront(dir);
				break;
			case 'B':
				rotateBack(dir);
				break;
		}
	}
	
	private static void turnPlane(int num, char dir) {
		//System.out.println(num+","+dir);
		int[][] tmp = new int[3][3];
		if(dir == '-') {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					tmp[i][j] = plane[num][j][2-i];
				}
			}
		} else {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					tmp[i][j] = plane[num][2-j][i];
				}
			}
		}
		plane[num] = tmp;
	}
	
	//B->R->F->L->B
	private static void rotateUp(char dir) {
		// TODO Auto-generated method stub
		turnPlane(U, dir);
		int[] tmp = new int[3];
		tmp[0] = plane[B][0][0];
		tmp[1] = plane[B][0][1];
		tmp[2] = plane[B][0][2];
		if(dir == '-') {			
			for(int i = 0; i < 3; i++) {
				plane[B][0][i] = plane[R][0][i];
				plane[R][0][i] = plane[F][0][i];
				plane[F][0][i] = plane[L][0][i];
				plane[L][0][i] = tmp[i];
			}
		} else {
			for(int i = 0; i < 3; i++) {
				plane[B][0][i] = plane[L][0][i];
				plane[L][0][i] = plane[F][0][i];
				plane[F][0][i] = plane[R][0][i];
				plane[R][0][i] = tmp[i];
			}
		}
	}

	//U->R->D->L->U
	private static void rotateFront(char dir) {
		// TODO Auto-generated method stub
		turnPlane(F, dir);
		int[] tmp = new int[3];
		tmp[0] = plane[U][2][0];
		tmp[1] = plane[U][2][1];
		tmp[2] = plane[U][2][2];
		if(dir == '-') {			
			for(int i = 0; i < 3; i++) {
				plane[U][2][i] = plane[R][i][0];
				plane[R][i][0] = plane[D][0][2-i];
				plane[D][0][2-i] = plane[L][2-i][2];
				plane[L][2-i][2] = tmp[i];
			}
		} else {
			for(int i = 0; i < 3; i++) {
				plane[U][2][i] = plane[L][2-i][2];
				plane[L][2-i][2] = plane[D][0][2-i];
				plane[D][0][2-i] = plane[R][i][0];
				plane[R][i][0] = tmp[i];
			}
		}
	}
	
	//U->L->D->R->U
	private static void rotateBack(char dir) {
		// TODO Auto-generated method stub
		turnPlane(B, dir);
		int[] tmp = new int[3];
		tmp[0] = plane[U][0][0];
		tmp[1] = plane[U][0][1];
		tmp[2] = plane[U][0][2];
		if(dir == '-') {
			for(int i = 0; i < 3; i++) {
				plane[U][0][i] = plane[L][2-i][0];
				plane[L][2-i][0] = plane[D][2][2-i];
				plane[D][2][2-i] = plane[R][i][2];
				plane[R][i][2] = tmp[i];
			}
		} else {
			for(int i = 0; i < 3; i++) {
				plane[U][0][i] = plane[R][i][2];
				plane[R][i][2] = plane[D][2][2-i];
				plane[D][2][2-i] = plane[L][2-i][0];
				plane[L][2-i][0] = tmp[i];
			}
		}
	}
	
	private static void rotateDown(char dir) {
		// TODO Auto-generated method stub
		turnPlane(D, dir);
		int[] tmp = new int[3];
		tmp[0] = plane[F][2][0];
		tmp[1] = plane[F][2][1];
		tmp[2] = plane[F][2][2];
		if(dir == '-') {
			for(int i = 0; i < 3; i++) {
				plane[F][2][i] = plane[R][2][i];
				plane[R][2][i] = plane[B][2][i];
				plane[B][2][i] = plane[L][2][i];
				plane[L][2][i] = tmp[i];
			}
		} else {
			for(int i = 0; i < 3; i++) {
				plane[F][2][i] = plane[L][2][i];
				plane[L][2][i] = plane[B][2][i];
				plane[B][2][i] = plane[R][2][i];
				plane[R][2][i] = tmp[i];
			}
		}
	}

	//U->B->D->F->U
	private static void rotateRight(char dir) {
		// TODO Auto-generated method stub
		turnPlane(R, dir);
		int[] tmp = new int[3];
		tmp[0] = plane[U][0][2];
		tmp[1] = plane[U][1][2];
		tmp[2] = plane[U][2][2];
		if(dir == '-') {
			for(int i = 0; i < 3; i++) {
				plane[U][i][2] = plane[B][2-i][0];
				plane[B][2-i][0] = plane[D][i][2];
				plane[D][i][2] = plane[F][i][2];
				plane[F][i][2] = tmp[i];
			}
		} else {
			for(int i = 0; i < 3; i++) {
				plane[U][i][2] = plane[F][i][2];
				plane[F][i][2] = plane[D][i][2];
				plane[D][i][2] = plane[B][2-i][0];
				plane[B][2-i][0] = tmp[i];
			}
		}
	}

	private static void rotateLeft(char dir) {
		// TODO Auto-generated method stub
		turnPlane(L, dir);
		int[] tmp = new int[3];
		tmp[0] = plane[U][0][0];
		tmp[1] = plane[U][1][0];
		tmp[2] = plane[U][2][0];
		if(dir == '-') {
			for(int i = 0; i < 3; i++) {
				plane[U][i][0] = plane[F][i][0];
				plane[F][i][0] = plane[D][i][0];
				plane[D][i][0] = plane[B][2-i][2];
				plane[B][2-i][2] = tmp[i];
			}
		} else {
			for(int i = 0; i < 3; i++) {
				plane[U][i][0] = plane[B][2-i][2];
				plane[B][2-i][2] = plane[D][i][0];
				plane[D][i][0] = plane[F][i][0];
				plane[F][i][0] = tmp[i];
			}
		}
	}
}
