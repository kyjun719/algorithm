package codejam._2020.qualification.nestingdepth;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				String arr = br.readLine();
				StringBuilder sb = new StringBuilder();
				int befDepth = 0;
				int cnt = 0;
				for(int i = 0; i < arr.length(); i++) {
					int defDepth = (arr.charAt(i)-'0')-befDepth;
					if(defDepth > 0) {
						while(defDepth > 0) {
							defDepth--;
							sb.append("(");
							cnt++;
						}
					} else if(defDepth < 0) {
						while(defDepth < 0) {
							defDepth++;
							sb.append(")");
							cnt--;
						}
					}
					sb.append(arr.charAt(i));
					befDepth = (arr.charAt(i)-'0');
				}
				for(int i = 0; i < cnt; i++) {
					sb.append(")");
				}
				System.out.println("Case #"+(t+1)+": "+sb.toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
