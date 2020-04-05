package algospot.FORTRESS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
	//성벽 객체
	static class Wall {
		int n,x,y,r;
		List<Wall> subWallList = new ArrayList<>();
		int height = 0;
		public Wall(int n, int[] info) {
			this.n = n;
			this.x = info[0];
			this.y = info[1];
			this.r = info[2];
		}
	}
	
	//가장 긴 잎간 길이
	static int longest;
	//성벽 갯수
	static int n;
	//성벽 리스트
	static List<Wall> wallList;
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				n = Integer.parseInt(br.readLine());
				wallList = new ArrayList<>();
				for(int i = 0; i < n; i++) {
					Wall wall = new Wall(
							i,
							Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray());
					wallList.add(wall);
				}
				//0번성벽을 기준으로 트리 생성
				Wall root = getTree(0);
				//높이와 잎간 최대길이 계산
				int ret = solve(root);
				System.out.println(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//트리 생성
	private static Wall getTree(int root) {
		Wall node = wallList.get(root);
		for(int i = 0; i < n; i++) {
			if(isSubWall(root, i)) {
				//트리를 만들고 자손에 추가함
				node.subWallList.add(getTree(i));
			}
		}
		
		return node;
	}
	
	//b가 a에 포함되는지 확인
	static boolean isSubWall(int a, int b) {
		//포함하는지 확인
		if(!isEncloses(wallList.get(a), wallList.get(b))) {
			return false;
		}
		
		//a번째 성벽이 b번째 성벽을 직접 포함하는지 확인
		for(int i = 0; i < n; i++) {
			//a와 b사이에 i번째 성벽이 있는지 확인
			if(i != a && i != b && 
					isEncloses(wallList.get(a), wallList.get(i)) && 
					isEncloses(wallList.get(i), wallList.get(b))) {
				return false;
			}
		}
		return true;
	}
	
	//성벽 a가 성벽 b를 포함하는지 확인
	static boolean isEncloses(Wall a, Wall b) {
		//포함하는 조건
		//1) a의 반지름이 b의 반지름보다 큼
		//2) b의 중점이 a의 반지름에서 b의 반지름을 뺀 영역안에 있어야함 
		return (a.r > b.r) && (getDist(a, b) < sqr(a.r - b.r));
	}
	
	private static int sqr(int x) {
		return x*x;
	}
	
	private static int getDist(Wall a, Wall b) {
		return sqr(a.x-b.x)+sqr(a.y-b.y);
	}

	//트리 높이와 잎간 길이중 최대값 반환
	private static int solve(Wall root) {
		longest = 0;
		//트리의 높이 계산
		int height = getHeight(root);
		//잎간 거리와 트리 높이중 최대값 반환
		return Math.max(longest, height);
	}

	//각 자손들마다 최대 잎간 길이와 높이 반환
	private static int getHeight(Wall root) {
		List<Wall> subWallList = root.subWallList;
		int n = subWallList.size();
		int[] heightArr = new int[n];
		//루트에서 각 자손들의 트리 높이 계산
		for(int i = 0; i < n; i++) {
			heightArr[i] = getHeight(subWallList.get(i));
		}
		
		if(n == 0) {
			return 0;
		}
		
		Arrays.sort(heightArr);
		if(n >= 2) {
			//최대 잎간 거리 = 루트에서 해당 잎 까지의 높이 = 서브트리의 최대 높이값 2개 + 2
			longest = Math.max(longest, heightArr[n-2]+heightArr[n-1]+2);
		}
		//트리 높이 = 서브트리의 높이 + 1
		return heightArr[n-1]+1;
	}
}
