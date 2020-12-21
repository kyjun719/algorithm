package acmicpc._1623;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		board = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		adj = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}
		cache = new int[2][n];
		Arrays.fill(cache[0], -1);
		Arrays.fill(cache[1], -1);
		int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for(int i = 0; i < tmp.length; i++) {
			adj[tmp[i]-1].add(i+1);
		}
		
		int a = solve(0,true);
		int b = solve(0,false);
		
		participants = new ArrayList[2];
		participants[0] = new ArrayList<>();
		participants[1] = new ArrayList<>();
		
		findParticipants(0,false,0);
		findParticipants(0,true,1);
		
		Collections.sort(participants[0]);
		Collections.sort(participants[1]);
//		System.out.println(participants[1]+" "+participants[0]);
		System.out.println(a+" "+b);
		System.out.println(participants[1].toString().replace("[", "").replace("]", "").replace(",", "")+" -1");
		System.out.println(participants[0].toString().replace("[", "").replace("]", "").replace(",", "")+" -1");
	}

	private static int[] board;
	private static ArrayList<Integer>[] adj;
	private static int[][] cache;
	//in : 직전상위거 참석여부
	private static int solve(int i, boolean in) {		
		int idx=in?1:0;
		if(cache[idx][i]!=-1) {
			return cache[idx][i];
		}
		
		int ret=0;
		//participate
		if(in) {
			for(int next : adj[i]) {
				ret+=solve(next,false);
			}
			ret+=board[i];
		} else {
			for(int next : adj[i]) {
				ret += Math.max(solve(next,false), solve(next,true));
			}
		}

		cache[idx][i]=ret;
//		System.out.println((in?"not participate":"participate")+" "+i+">>"+ret);
		
		return ret;
	}
	
	private static ArrayList<Integer>[] participants;
	private static void findParticipants(int idx, boolean in, int isBoss) {
		if(in) {
			//현재 참가하는 경우
			for(int next : adj[idx]) {
				findParticipants(next,false,isBoss);
			}
			participants[isBoss].add(idx+1);
		} else {
			for(int next : adj[idx]) {
				//참가 안하는게 나은경우
				if(cache[0][next] > cache[1][next]) {
					findParticipants(next,false,isBoss);
				} else {
					findParticipants(next,true,isBoss);
				}
			}
		}
	}
}
