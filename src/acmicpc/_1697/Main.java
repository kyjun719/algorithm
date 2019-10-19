package acmicpc._1697;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] board;
	static int n,k;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			n = info[0];
			k = info[1];
			board = new int[100001];
			Arrays.fill(board, -1);
			if(n < k) {
				System.out.println(solve());
			} else {
				System.out.println(n-k);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int solve() {
		board[n] = 0;
		int ret = 987654321;
		Queue<Integer> search = new LinkedList<>();
		search.add(n);
		while(!search.isEmpty()) {
			int now = search.poll();
			if(now == k) {
				ret = board[now];
				continue;
			}
			int next = board[now]+1;
			if(next >= ret) {
				continue;
			}
			if((now-1)>0 && board[now-1] == -1) {
				search.add(now-1);
				board[now-1] = next;
			}
			if((now+1)<100001 && board[now+1] == -1) {
				search.add(now+1);
				board[now+1] = next;
			}
			if((now*2)<100001 && board[now*2] == -1) {
				search.add(now*2);
				board[now*2] = next;
			}
		}
		return ret;
	}
}
