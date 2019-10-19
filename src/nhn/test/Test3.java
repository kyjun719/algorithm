package nhn.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Test3 {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(br.readLine());
			int[] candy = new int[n];
			boolean[][] isFollower = new boolean[n][n]; 
			Queue<String> opQueue = new LinkedList<>(Arrays.asList(br.readLine().split(" ")));
			int player = 0;
			while(!opQueue.isEmpty()) {
				String op = opQueue.poll();
				if(op.equals("A") || op.equals("J")) {
					if(op.equals("A")) {
						candy[player]++;
					} else {
						candy[(n+player-1)%n]++;
						candy[(player+1)%n]++;
					}
					
					Queue<Integer> followQueue = new LinkedList<>();
					followQueue.add(player);
					boolean[] isFollowAdd = new boolean[n];
					while(!followQueue.isEmpty()) {
						int checked = followQueue.poll();
						for(int idx = 0; idx < n; idx++) {
							if(isFollower[checked][idx] && !isFollowAdd[idx]) {
								candy[idx]++;
								isFollowAdd[idx]=true;
								followQueue.add(idx);
							}
						}
					}
				} else if(op.equals("Q")) {
					for(int i = 0; i < n; i++) {
						candy[i]++;
					}
				} else {
					int idx =Integer.parseInt(opQueue.poll());
					isFollower[player][idx] = true;
				}
				//System.out.println(player+"::"+op+">>"+Arrays.toString(candy));
				player = (player+1)%n;
			}
			
			String ret = Arrays.toString(candy);
			ret = ret.replace("[", "");
			ret = ret.replace("]", "");
			ret = ret.replace(",", "");
			System.out.println(ret);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
