package acmicpc._2633;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	/*
	 * ??????????????????????????????
	 */
	private static final int INF=987654321;
	private static final int RT=1<<0,RB=1<<1,LT=1<<2,LB=1<<3,INIT=(1<<4)-1;
	private static int bxmin,bymin,bxmax,bymax;
	private static int[] end;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int[] start = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			end = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int m = Integer.parseInt(br.readLine());
			bxmin=INF;
			bymin=INF;
			bxmax=0;
			bymax=0;
			
			Point[][] pointArr = new Point[101][101];
			for(int x = 0; x <= 100; x++) {
				for(int y = 0; y <= 100; y++) {
					pointArr[y][x]=new Point();
				}
			}
			
			for(int i = 0; i < m; i++) {
				int[] ptr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				
				int xmin=INF, ymin=INF;
				int xmax=0, ymax=0;
				int[] inner= new int[2];
				for(int c = 0; c < 4; c++) {
					int[] selected = {ptr[c*2],ptr[c*2+1]};
					//블록의 x값범위와 y값범위 계산
					xmin=Math.min(selected[0], xmin);
					xmax=Math.max(selected[0], xmax);
					ymin=Math.min(selected[1], ymin);
					ymax=Math.max(selected[1], ymax);
					
					int cnt=0;
					for(int k = 0; k < 4; k++) {
						if(c==k) {
							continue;
						}
						//현재 선택한점이 다른 세 점 안에 있는 형태인지 확인
						if((ptr[k*2]>selected[0] && ptr[k*2+1]>selected[1]) || 
								(ptr[k*2]>selected[0] && ptr[k*2+1]<selected[1]) ||
								(ptr[k*2]<selected[0] && ptr[k*2+1]>selected[1]) ||
								(ptr[k*2]<selected[0] && ptr[k*2+1]<selected[1])) {
							cnt++;
						}
					}
					
					if(cnt==3) {
						//inner point
						inner=selected;
						break;
					}
				}
				
				//비어있는 구간 확인
				//rt,rb,lt,lb;
				int virtualExisted = INIT;
				for(int k = 0; k < 4; k++) {
					if(ptr[k*2]==xmin && ptr[k*2+1]==ymin) {
						virtualExisted &= ~LB;
						continue;
					}
					if(ptr[k*2]==xmin && ptr[k*2+1]==ymax) {
						virtualExisted &= ~LT;
						continue;
					}
					if(ptr[k*2]==xmax && ptr[k*2+1]==ymin) {
						virtualExisted &= ~RB;
						continue;
					}
					if(ptr[k*2]==xmax && ptr[k*2+1]==ymax) {
						virtualExisted &= ~RT;
						continue;
					}
				}
				//System.out.println(Arrays.toString(ptr)+">>"+Integer.toBinaryString(virtualExisted));
				
				//전체 판의 수행범위
				bxmin=Math.min(bxmin, xmin);
				bxmax=Math.max(bxmax, xmax);
				bymin=Math.min(bymin, ymin);
				bymax=Math.max(bymax, ymax);
				
				switch(virtualExisted) {
					case RT:
						setRT(pointArr, ymin, ymax, xmin, xmax, inner);
						break;
					case RB:
						setRB(pointArr, ymin, ymax, xmin, xmax, inner);
						break;
					case LT:
						setLT(pointArr, ymin, ymax, xmin, xmax, inner);
						break;
					case LB:
						setLB(pointArr, ymin, ymax, xmin, xmax, inner);
						break;
				}
				
				/*
				for(int x = xmin; x <= xmax; x++) {
					for(int y = ymin; y <= ymax; y++) {
						System.out.println(x+","+y+">>"+pointArr[y][x]);
					}
				}
				*/
			}
			
			bxmin=Math.max(bxmin-1, 0);
			bymin=Math.max(bymin-1, 0);
			bxmax=Math.min(bxmax+1, 100);
			bymax=Math.min(bymax+1, 100);
			//System.out.println(bxmin+","+bymin+"~"+bxmax+","+bymax);

			//수행범위는 xmin-1,ymin-1 ~ xmax+1,ymax+1
			//최단경로가 엄청 꺾이는경우 실패하므로 최단꺾기를 구해야함
			ans=INF;
			visited=new int[4][101][101];
			for(int j = 0; j < 4; j++) {
				for(int i = 0; i <= 100; i++) {
					Arrays.fill(visited[j][i], 987654321);
				}
			}
			
			solve(pointArr,start[1],start[0]);
			System.out.println(ans);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//왼쪽아래가 비어있을때 해당구역의 점에대해 갈수있는 방향 설정
	private static void setLB(Point[][] pointArr, int ymin, int ymax, int xmin, int xmax, int[] inner) {
		//오른쪽 L불가, 위쪽 D불가
		for(int y=ymin+1; y<ymax; y++) {
			//오른쪽 L불가
			pointArr[y][xmax].L=false;
			if(y>inner[1]) {
				//왼쪽 내점에서 끝까지 R불가
				pointArr[xmin][y].R=false;
			} else {
				//y최소값~내점까지 R불가
				pointArr[y][inner[0]].R=false;
			}
		}

		for(int x=xmin+1; x<xmax; x++) {
			//위쪽 D불가
			pointArr[ymax][x].D=false;
			if(x<=inner[0]) {
				//x최소~내점까지 U불가
				pointArr[inner[1]][x].U=false;
			} else {
				//아래쪽 내점에서 끝까지 U불가
				pointArr[ymin][x].U=false;
			}
			
			//내부칸 막기
			for(int y=ymin+1; y<ymax; y++) {
				if(x>inner[0]) {
					pointArr[y][x].D=false;
					pointArr[y][x].U=false;
					pointArr[y][x].L=false;
					pointArr[y][x].R=false;
				} else if(y>inner[1]) {
					pointArr[y][x].D=false;
					pointArr[y][x].U=false;
					pointArr[y][x].L=false;
					pointArr[y][x].R=false;
				} else {
					continue;
				}
			}
		}	
	}

	//왼쪽위가 비어있을때 해당구역의 점에대해 갈수있는 방향 설정
	private static void setLT(Point[][] pointArr, int ymin, int ymax, int xmin, int xmax, int[] inner) {
		for(int y=ymin+1; y<ymax; y++) {
			//오른쪽 L불가
			pointArr[y][xmax].L=false;
			if(y<inner[1]) {
				//오른쪽 내점까지 R불가
				pointArr[xmin][y].R=false;
			} else {
				//내점~y끝까지 R불가
				pointArr[y][inner[0]].R=false;
			}
		}

		for(int x=xmin+1; x<xmax; x++) {
			//아래쪽 U불가
			pointArr[ymin][x].U=false;
			if(x<=inner[0]) {
				//x최소~내점까지 D불가
				pointArr[inner[1]][x].D=false;
			} else {
				//위쪽 내점부터 끝까지 D불가
				pointArr[ymax][x].D=false;
			}
			
			//내부칸 막기
			for(int y=ymin+1; y<ymax; y++) {
				if(x>inner[0]) {
					pointArr[y][x].D=false;
					pointArr[y][x].U=false;
					pointArr[y][x].L=false;
					pointArr[y][x].R=false;
				} else if(y<inner[1]) {
					pointArr[y][x].D=false;
					pointArr[y][x].U=false;
					pointArr[y][x].L=false;
					pointArr[y][x].R=false;
				} else {
					continue;
				}
			}
		}
	}

	//오른쪽아래가 비어있을때 해당구역의 점에대해 갈수있는 방향 설정
	private static void setRB(Point[][] pointArr, int ymin, int ymax, int xmin, int xmax, int[] inner) {
		for(int y=ymin+1; y<ymax; y++) {
			//왼쪽 R불가
			pointArr[y][xmin].R=false;
			if(y>inner[1]) {
				//오른쪽 내점까지 L불가
				pointArr[xmax][y].L=false;
			} else {
				//내점~y끝까지 L불가
				pointArr[y][inner[0]].L=false;
			}
		}

		for(int x=xmin+1; x<xmax; x++) {
			//위쪽 D불가
			pointArr[ymax][x].D=false;
			if(x<inner[0]) {
				//아래쪽 내점까지 U불가
				pointArr[ymin][x].U=false;
			} else {
				//내점~x끝까지 U불가
				pointArr[inner[1]][x].U=false;
			}
			
			//내부칸 막기
			for(int y=ymin+1; y<ymax; y++) {
				if(x<inner[0]) {
					pointArr[y][x].D=false;
					pointArr[y][x].U=false;
					pointArr[y][x].L=false;
					pointArr[y][x].R=false;
				} else if(y>inner[1]) {
					pointArr[y][x].D=false;
					pointArr[y][x].U=false;
					pointArr[y][x].L=false;
					pointArr[y][x].R=false;
				} else {
					continue;
				}
			}
		}
	}

	//오른쪽위가 비어있을때 해당구역의 점에대해 갈수있는 방향 설정
	private static void setRT(Point[][] pointArr, int ymin, int ymax, int xmin, int xmax, int[] inner) {
		for(int y=ymin+1; y<ymax; y++) {
			//왼쪽 R불가
			pointArr[y][xmin].R=false;
			if(y<inner[1]) {
				//오른쪽 내점까지 L불가
				pointArr[xmax][y].L=false;
			} else {
				//내점~y끝까지 L불가
				pointArr[y][inner[0]].L=false;
			}
		}

		for(int x=xmin+1; x<xmax; x++) {
			//아래쪽 U불가
			pointArr[ymin][x].U=false;
			if(x<inner[0]) {
				//위쪽 내점까지 D불가
				pointArr[ymax][x].D=false;
			} else {
				//내점~x끝까지 D불가
				pointArr[inner[1]][x].D=false;
			}
			
			//내부칸 막기
			for(int y=ymin+1; y<ymax; y++) {
				if(x<inner[0]) {
					pointArr[y][x].D=false;
					pointArr[y][x].U=false;
					pointArr[y][x].L=false;
					pointArr[y][x].R=false;
				} else if(y<inner[1]) {
					pointArr[y][x].D=false;
					pointArr[y][x].U=false;
					pointArr[y][x].L=false;
					pointArr[y][x].R=false;
				} else {
					continue;
				}
			}
		}
	}
	
	private static class Point {
		boolean U=true,D=true,L=true,R=true;
		
		@Override
		public String toString() {
			String ret = "";
			if(U) ret+="U";
			if(D) ret+="D";
			if(L) ret+="L";
			if(R) ret+="R";
			return ret;
		}
	}
	
	private static class MovePoint {
		int y,x,cnt,dir;
		
		public MovePoint(int y, int x, int cnt, int dir) {
			this.y=y;
			this.x=x;
			this.cnt=cnt;
			this.dir=dir;
		}
	}

	private static int U=0,D=1,L=2,R=3;
	private static int ans;
	private static int[][][] visited;
	private static void solve(Point[][] pointArr, int Y, int X) {
		Queue<MovePoint> q = new LinkedList<>();
		q.add(new MovePoint(Y,X,0,-1));
		visited[U][Y][X]=0;
		visited[D][Y][X]=0;
		visited[L][Y][X]=0;
		visited[R][Y][X]=0;
		
		while(!q.isEmpty()) {
			MovePoint n = q.poll();
//			System.out.println(n.x+","+n.y+">>"+n.cnt);
			if(n.dir!=-1 && n.cnt>visited[n.dir][n.y][n.x]) {
				continue;
			}
			
			if(ans<=n.cnt) {
				continue;
			}
			
			if(n.x==end[0] && n.y==end[1]) {
				ans=Math.min(ans, n.cnt);
				continue;
			}
			
//			System.out.println(visited[n.y-1][n.x]+","+(1<<D)+","+(visited[n.y-1][n.x]&(1<<D)));
			int nextCnt = (n.dir==-1||n.dir==D)?n.cnt:n.cnt+1;
			if(n.y-1>=bymin && pointArr[n.y][n.x].D && (visited[D][n.y-1][n.x]>nextCnt)) {
				visited[D][n.y-1][n.x]=nextCnt;
				q.add(new MovePoint(n.y-1,n.x,nextCnt,D));
			}
			
			nextCnt = (n.dir==-1||n.dir==U)?n.cnt:n.cnt+1;
			if(n.y+1<=bymax && pointArr[n.y][n.x].U && (visited[U][n.y+1][n.x]>nextCnt)) {
				visited[U][n.y+1][n.x]=nextCnt;
				q.add(new MovePoint(n.y+1,n.x,nextCnt,U));
			}
			
			nextCnt = (n.dir==-1||n.dir==L)?n.cnt:n.cnt+1;
			if(n.x-1>=bxmin && pointArr[n.y][n.x].L && (visited[L][n.y][n.x-1]>nextCnt) ) {
				visited[L][n.y][n.x-1]=nextCnt;
				q.add(new MovePoint(n.y,n.x-1,nextCnt,L));
			}
			
			nextCnt = (n.dir==-1||n.dir==R)?n.cnt:n.cnt+1;
			if(n.x+1<=bxmax && pointArr[n.y][n.x].R && (visited[R][n.y][n.x+1]>nextCnt) ) {
				visited[R][n.y][n.x+1]=nextCnt;
				q.add(new MovePoint(n.y,n.x+1,nextCnt,R));
			}
		}
	}
}
