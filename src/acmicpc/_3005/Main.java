package acmicpc._3005;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int r = tmp[0];
			int c = tmp[1];
			char[][] board = new char[r][c];
			for(int i = 0; i < r; i++) {
				board[i]=br.readLine().toCharArray();
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < Math.max(r, c); i++) {
				sb.append("z");
			}
			String ans=sb.toString();
			
			for(int i = 0; i < r; i++) {
				sb = new StringBuilder();
				for(int j = 0; j < c; j++) {
					if(board[i][j]=='#') {
						if(sb.length()<2) {
							sb=new StringBuilder();
							continue;
						}
						if(ans.compareTo(sb.toString())>0) {
							ans=sb.toString();
							sb=new StringBuilder();
						}
					} else {
						sb.append(board[i][j]);
					}
				}
				if(sb.length()>=2 && ans.compareTo(sb.toString())>0) {
					ans=sb.toString();
					sb=new StringBuilder();
				}
			}
			
			for(int j = 0; j < c; j++) {
				sb = new StringBuilder();
				for(int i = 0; i < r; i++) {
					if(board[i][j]=='#') {
						if(sb.length()<2) {
							sb=new StringBuilder();
							continue;
						}
						if(ans.compareTo(sb.toString())>0) {
							ans=sb.toString();
							sb=new StringBuilder();
						}
					} else {
						sb.append(board[i][j]);
					}
				}
				if(sb.length()>=2 && ans.compareTo(sb.toString())>0) {
					ans=sb.toString();
					sb=new StringBuilder();
				}
			}
			
			System.out.println(ans);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
