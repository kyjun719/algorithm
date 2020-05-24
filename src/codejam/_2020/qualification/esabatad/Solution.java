package codejam._2020.qualification.esabatad;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {	
	static int queryNum, b;
	static int[] ret;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] info = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			int tc = info[0];
			b = info[1];
			for(int t = 1; t <= tc; t++) {
				queryNum = 0;
				ret = new int[200];				
				for(int idx = 1; idx <= b/2; idx++) {
					if((queryNum > 0) && (queryNum%10 == 0)) {
						refresh(br, idx-1);
					}
					ret[idx] = query(br, idx);
					ret[b-idx+1] = query(br, b-idx+1);
				}
				
				String str = "";
				for(int i = 1; i <= b; i++) {
					str = str + ret[i];
				}
				System.out.println(str);
				System.out.flush();
				String res = br.readLine();
				if(res.equals("N")) {
					return;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void refresh(BufferedReader br, int idx) throws Exception {
		int compIdx = -1;
		int revIdx = -1;
		
		for(int i = 1; i <= idx; i++) {
			if(ret[i] == ret[b-i+1]) {
				compIdx = i;
			} else {
				revIdx = i;
			}
		}
		
		if(compIdx != -1) {
			//in same pair, comp:dif, rev:same
			//in dif pair, comp:dif, rev:dif
			if(ret[compIdx] != query(br, compIdx)) {
				//comp
				for(int i = 1; i <= idx; i++) {
					ret[i] ^= 1;
					ret[b-i+1] ^= 1;
				}
			}
			if(revIdx != -1) {
				if(ret[revIdx] != query(br, revIdx)) {
					//rev
					for(int i = 1; i <= idx; i++) {
						int tmp = ret[b-i+1];
						ret[b-i+1] = ret[i];
						ret[i] = tmp;
					}
				}
			} else {
				query(br, compIdx);
			}
		} else {
			if(revIdx != -1) {
				if(ret[revIdx] != query(br, revIdx)) {
					//rev
					for(int i = 1; i <= idx; i++) {
						int tmp = ret[b-i+1];
						ret[b-i+1] = ret[i];
						ret[i] = tmp;
					}
				}
				query(br, revIdx);
			}
		}
	}
	
	static int query(BufferedReader br, int i) throws Exception {
		queryNum++;
		System.out.println(i);
        System.out.flush();
        return Integer.parseInt(br.readLine());
	}
}
