package acmicpc._3107;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] origin = new String[8];
		Arrays.fill(origin, "");
		
		if(s.contains("::")) {
			int start=0,end=7;
			String[] tmp = s.split("::");
			if(tmp.length>0 && !tmp[0].isEmpty()) {
				String[] left = tmp[0].split(":");
				for(start=0;start<left.length;start++) {
					origin[start]=getOrigin(left[start]);
				}
			}
			if(tmp.length>1 && !tmp[1].isEmpty()) {
				String[] right = tmp[1].split(":");
				for(int i = 0; i <right.length; i++) {
					origin[end--]=getOrigin(right[right.length-i-1]);
				}
			}
			for(int i = start;i<=end;i++) {
				origin[i]="0000";
			}
		} else {
			String[] shorted = s.split(":");
			for(int i = 0; i < shorted.length; i++) {
				origin[i]=getOrigin(shorted[i]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<origin.length; i++) {
			sb.append(origin[i]+(i==origin.length-1?"":":"));
		}
		System.out.println(sb.toString());
	}
	
	private static String getOrigin(String s) {
		String ret = "";
		for(int j = 0; j < 4-s.length(); j++) {
			ret+="0";
		}
		return ret+s;
	}
}
