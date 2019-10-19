package acmicpc._13460;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @see https://www.acmicpc.net/problem/13460
 * @author jun
 */
public class Main {
	static int n,m;
	static int[][] direction = {{-1,0},{0,-1},{1,0},{0,1}};
	static int MAX_VAL = 987654321;
	
	public static void main(String[] args) {
		try {
			//wall('#',0),blank('.',1),hole('O',2),red('R',3),blue('B',4);
			HashMap<Character, Integer> map = new HashMap<>();
			map.put('#', 0);
			map.put('.', 1);
			map.put('O', 2);
			map.put('R', 1);
			map.put('B', 1);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
			n = info[0];
			m = info[1];
			int[][] board = new int[n][m];
			int[] red = new int[2];
			int[] blue = new int[2];
			for(int i = 0; i < n; i++) {
				String tmp = br.readLine().replace("\n", "");
				for(int j = 0; j < m; j++) {
					board[i][j] = map.get(tmp.charAt(j));
					if(tmp.charAt(j) == 'R') {
						red[0] = i;
						red[1] = j;
					}
					if(tmp.charAt(j) == 'B') {
						blue[0] = i;
						blue[1] = j;
					}
				}
			}
			int ret = solve(board, 0, red, blue, -1);
			System.out.println(ret==MAX_VAL?-1:ret);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static int solve(int[][] board, int count, int[] red, int[] blue, int prevDir) {
		if(count > 10) {
			return MAX_VAL;
		}
		
		if(blue[0]+blue[1] == -2) {
			return MAX_VAL;
		}
		
		if(red[0]+red[1] == -2) {
			return 0;
		}
		
		int ret = MAX_VAL;
		for(int i = 0; i < 4; i++) {
			if(prevDir != -1 && ((prevDir + 2)%4 == i || prevDir == i)) {
				continue;
			}
			int[][] tmpBoard = board.clone();
			int[] tmpRed = red.clone();
			int[] tmpBlue = blue.clone();
			boolean isRedMove = true;
			boolean isBlueMove = true;
			boolean isRedFinish=false, isBlueFinish=false;
			
			//showBoard(tmpBoard);
			/*System.out.println("RED::"+Arrays.toString(tmpRed)+",BLUE::"+Arrays.toString(tmpBlue)+
					",count::"+count+",prev::"+prevDir+",now::"+i);*/
			
			if(blueFirst(tmpRed, tmpBlue, direction[i])) {
				while(true) {
					if(isBlueMove) {
						int moveRet = moveBall(tmpBoard, tmpBlue, tmpRed, direction[i]);
						//System.out.println("BLUE::"+Arrays.toString(tmpBlue)+">>"+moveRet);
						if(moveRet == 1) {
							isBlueMove = true;
							tmpBlue[0] += direction[i][0];
							tmpBlue[1] += direction[i][1];
						} else if(moveRet == 2) {
							isBlueMove = false;
							isBlueFinish = true;
							tmpBlue[0] = -1;
							tmpBlue[1] = -1;
						} else {
							isBlueMove = false;
						}
					}
					if(isRedMove) {
						int moveRet = moveBall(tmpBoard, tmpRed, tmpBlue, direction[i]);
						//System.out.println("RED::"+Arrays.toString(tmpRed)+">>"+moveRet);
						if(moveRet == 1) {
							isRedMove = true;
							tmpRed[0] += direction[i][0];
							tmpRed[1] += direction[i][1];
						} else if(moveRet == 2) {
							isRedMove = false;
							isRedFinish = true;
							tmpRed[0] = -1;
							tmpRed[1] = -1;
						} else {
							isRedMove = false;
						}
					}
					
					if(!isRedMove && !isBlueMove) {
						break;
					}
				}
			} else {
				while(true) {
					if(isRedMove) {
						int moveRet = moveBall(tmpBoard, tmpRed, tmpBlue, direction[i]);
						//System.out.println("RED::"+Arrays.toString(tmpRed)+">>"+moveRet);
						if(moveRet == 1) {
							isRedMove = true;
							tmpRed[0] += direction[i][0];
							tmpRed[1] += direction[i][1];
						} else if(moveRet == 2) {
							isRedMove = false;
							isRedFinish = true;
							tmpRed[0] = -1;
							tmpRed[1] = -1;
						} else {
							isRedMove = false;
						}
					}
					if(isBlueMove) {
						int moveRet = moveBall(tmpBoard, tmpBlue, tmpRed, direction[i]);
						//System.out.println("BLUE::"+Arrays.toString(tmpBlue)+">>"+moveRet);
						if(moveRet == 1) {
							isBlueMove = true;
							tmpBlue[0] += direction[i][0];
							tmpBlue[1] += direction[i][1];
						} else if(moveRet == 2) {
							isBlueMove = false;
							isBlueFinish = true;
							tmpBlue[0] = -1;
							tmpBlue[1] = -1;
						} else {
							isBlueMove = false;
						}
					}
					
					if(!isRedMove && !isBlueMove) {
						break;
					}
				}
			}
			
			ret = Math.min(ret, 1+solve(tmpBoard, count+1, tmpRed, tmpBlue, i));
		}
		
		return ret;
	}
	
	private static boolean blueFirst(int[] tmpRed, int[] tmpBlue, int[] dir) {
		// TODO Auto-generated method stub
		return (tmpBlue[0] == tmpRed[0]+dir[0]) && (tmpBlue[1] == tmpRed[1]+dir[1]);
	}

	private static void showBoard(int[][] tmpBoard) {
		for(int i = 0; i < tmpBoard.length; i++) {
			System.out.println(Arrays.toString(tmpBoard[i]));
		}
	}

	static int moveBall(int[][] board, int[] moveBall, int[] stopBall, int[] dir) {
		if(board[moveBall[0]+dir[0]][moveBall[1]+dir[1]] == 2) {
			return 2;
		}
		
		if((moveBall[0]+dir[0] == stopBall[0]) && (moveBall[1]+dir[1] == stopBall[1])) {
			return 0;
		}
		return board[moveBall[0]+dir[0]][moveBall[1]+dir[1]];
	}
}
