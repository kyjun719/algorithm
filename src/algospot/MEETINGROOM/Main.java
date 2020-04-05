package algospot.MEETINGROOM;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
	private static class MeetingTime {
		int startTime, endTime, idx;
		public MeetingTime(int startTime, int endTime, int idx) {
			this.startTime = startTime;
			this.endTime = endTime;
			this.idx = idx;
		}
		public String toString() {
			return startTime+" "+endTime;
		}
	}
	
	private static ArrayList<MeetingTime> timeList;
	private static ArrayList<Integer>[] adj;
	private static int n;
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				n = Integer.parseInt(br.readLine());
				
				//짝수 : 주간, 홀수 : 월간
				timeList = new ArrayList<>();
				
				for(int i = 0; i < n; i++) {
					int[] tmp = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
					timeList.add(new MeetingTime(tmp[0], tmp[1], 2*i));
					timeList.add(new MeetingTime(tmp[2], tmp[3], 2*i+1));
				}
				
				//i%4==0:주간, i%4==1:주간 열리지 않음, i%4==2:월간, i%4==3:월간 열리지 않음
				adj = new ArrayList[4*n];
				for(int i = 0; i < 4*n; i++) {
					adj[i] = new ArrayList<>();
				}
				
				for(int i = 0; i < 2*n; i+=2) {
					int j = i+1;
					//주간이 열리지 않으면 월간은 열림
					adj[i*2+1].add(j*2);
					//월간이 열리지 않으면 주간은 열림
					adj[j*2+1].add(i*2);
				}
				
				for(int i = 0; i < 2*n; i++) {
					MeetingTime now = timeList.get(i);
					//해당순번 뒤의 팀들을 돌면서 시간이 겹치는지 확인
					for(int j = 0; j < i; j++) {
						MeetingTime time = timeList.get(j);
						//시간이 안겹치지 않으면(= 시간이 겹치면)
						if(!(time.startTime >= now.endTime || time.endTime <= now.startTime)) {
							//해당 시간이 열리면 지금 미팅은 열리지 않음
							adj[i*2].add(j*2+1);
							//지금 미팅이 열리면 해당시간은 열리지 않음
							adj[j*2].add(i*2+1);
						}
					}
				}
				
				int[] ret = solve();
				if(ret.length == 0) {
					System.out.println("IMPOSSIBLE");
				} else {
					System.out.println("POSSIBLE");
					for(int i = 0; i < 2*n; i+=2) {
						MeetingTime time;
						if(ret[i] == 1) {
							time = timeList.get(i);
						} else {
							time = timeList.get(i+1);
						}
						System.out.println(time.startTime+" "+time.endTime);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//SCC id
	private static int[] sccId;
	//SCC 컴포넌트 분리시 노드가 발견된 순서
	private static int[] discovered;
	//sccCounter : SCC 그룹 id 카운터, verexCounter : 노드 발견 순서 카운터 
	private static int sccCounter, vertexCounter;
	//컴포넌트 분리시 발견된 노드를 저장한 스택
	private static Stack<Integer> stack;
	private static int[] tarjanSCC() {
		stack = new Stack<>();
		sccCounter = 0;
		vertexCounter = 0;
		
		sccId = new int[adj.length];
		discovered = new int[adj.length];
		Arrays.fill(sccId, -1);
		Arrays.fill(discovered, -1);
		
		for(int i = 0; i < discovered.length; i++) {
			if(discovered[i] == -1) {
				scc(i);
			}
		}
		
		return sccId;
	}
	
	private static int scc(int here) {
		discovered[here] = vertexCounter++;
		
		int ret = discovered[here];
		stack.add(here);
		//해당노드와 연결된 노드를 탐색하면서 가장 위로 올라갈 수 있는 노드번호 검색
		for(int i = 0; i < adj[here].size(); i++) {
			int there = adj[here].get(i);
			if(discovered[there] == -1) {
				ret = Math.min(ret, scc(there));
			} else if(sccId[there] == -1) {
				ret = Math.min(ret, discovered[there]);
			}
		}
		
		//연결된 노드들의 역방향간선으로 올라갈 수 있는 노드가 자기 자신일떄
		//서브트리들을 하나의 컴포넌트로 묶음
		if(ret == discovered[here]) {
			while(true) {
				int tmp = stack.pop();
				sccId[tmp] = sccCounter;
				if(tmp == here) {
					break;
				}
			}
			sccCounter++;
		}
		return ret;
	}
	
	private static class SCCOrder {
		int label, idx;
		public SCCOrder(int label, int idx) {
			this.label = label;
			this.idx = idx;
		}
		public String toString() {
			return "{"+label+"->"+idx+"}";
		}
	}
	
	private static int[] solve() {
		int[] scc = tarjanSCC();
		
		//한팀의 주간과 월간이 한 컴포넌트에 있는 경우(주간과 월간이 동시에 열리거나 동시에 안열리거나) 불가능 반환
		for(int i = 0; i < 4*n; i+=2) {
			if(scc[i] == scc[i+1]) {
				return new int[0];
			}
		}
		
		int[] ret = new int[4*n];
		Arrays.fill(ret, -1);
		
		//가장 마지막에 분류되는 컴포넌트가 위상정렬상 맨 오른쪽 컴포넌트 이므로
		//SCC 컴포넌트 id를 역순으로 정렬하면 위상정렬 순서가 됨
		ArrayList<SCCOrder> orderList = new ArrayList<>();
		for(int i = 0; i < 4*n; i++) {
			orderList.add(new SCCOrder(scc[i], i));
		}
		orderList.sort(new Comparator<SCCOrder>() {
			@Override
			public int compare(SCCOrder o1, SCCOrder o2) {
				return o2.label-o1.label;
			}
		});
		
		//먼저 나오는 정점에 대해서는 거짓으로 분류함
		for(SCCOrder order : orderList) {
			int vertex = order.idx;
			//i%4==0:주간, i%4==2:월간
			int variable = vertex/2;
			//홀수는 해당 미팅이 열리지 않음
			//모든 정점은 거짓정점으로 판단하므로 홀수인 경우 해당 미팅은 열림
			int isFalse = vertex%2==0?0:1;
			if(ret[variable] != -1) {
				continue;
			}
			ret[variable] = isFalse;
		}
		return ret;
	}
}
