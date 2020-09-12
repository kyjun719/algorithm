package acmicpc._16925;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayList<String> list = new ArrayList<>();
		ArrayList<String> sorted = new ArrayList<>();
		for(int i = 0; i < 2*n-2; i++) {
			String tmp = br.readLine();
			list.add(tmp);
			sorted.add(tmp);
		}
		
		int s = sorted.size();
		sorted.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o1.length()-o2.length();
			}
		});
		
		ArrayList<String> candidiate = new ArrayList<>();
		candidiate.add(sorted.get(0)+sorted.get(s-1));
		candidiate.add(sorted.get(0)+sorted.get(s-2));
		candidiate.add(sorted.get(1)+sorted.get(s-1));
		candidiate.add(sorted.get(1)+sorted.get(s-2));
		
		String ans = "";
		for(String c : candidiate) {
			HashMap<String, Integer> map = new HashMap<>();
			String tmpRet="";
			boolean fail = false;
			int scnt=0,pcnt=0;
			for(String tmp : list) {
				if(c.startsWith(tmp) && map.get(tmp) == null) {
					map.put(tmp, 1);
					tmpRet+="P";
					pcnt++;
				} else if(c.endsWith(tmp)) {
					tmpRet+="S";
					scnt++;
				} else {
					fail=true;
					break;
				}
			}

			if(fail || (pcnt!=scnt)) {
				continue;
			}
			
			ans=c+"\n"+tmpRet;
			break;
		}
		System.out.println(ans);
	}
}
