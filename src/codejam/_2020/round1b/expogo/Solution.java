package codejam._2020.round1b.expogo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
	static HashMap<Long[], Integer> cache;
	static int maxCnt;
	static long[] dest;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 1; t <= tc; t++) {
				dest = Arrays.stream(br.readLine().split(" "))
						.mapToLong(Long::parseLong)
						.toArray();
				long val = Math.abs(dest[0]+dest[1]);
				for(int i = 31; i >= 0; i--) {
					if(val > (Math.pow(2,i))) {
						maxCnt = i+1;
						break;
					}
				}
				
				cache = new HashMap<>();
				strCache = new String[32];
				String ret = reconstruct(new Long[]{(long) 0,(long) 0}, 0);
				if(ret.isEmpty()) {
					System.out.println("Case #"+t+": IMPOSSIBLE");
				} else {
					System.out.println("Case #"+t+": "+ret);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	static String[] dirStr = {"W","E","S","N"};
	static String[] strCache;
	private static String reconstruct(Long[] ptr, int cnt) {
		if(cnt >= maxCnt) {
			return "";
		}
		
		if(strCache[cnt] != null) {
			return strCache[cnt];
		}
		
		String ret = "";
		int val = 1<<cnt;
		for(int i = 0; i < 4; i++) {
			Long[] nextPtr = {ptr[0]+dir[i][0]*val, ptr[1]+dir[i][1]*val};
			//System.out.println(Arrays.toString(ptr)+"("+solve(ptr, cnt)+")"+"::"+Arrays.toString(nextPtr)+"("+solve(nextPtr, cnt+1)+")");
			if(solve(ptr,cnt) != 987654321 && (solve(ptr, cnt) == solve(nextPtr, cnt+1))) {
				ret = dirStr[i]+reconstruct(nextPtr, cnt+1);
			}
		}
		return ret;
	}

	private static int solve(Long[] ptr, int cnt) {
		//System.out.println(Arrays.toString(ptr)+">>"+cnt);
		if(ptr[0] == dest[0] && ptr[1] == dest[1]) {
			return cnt;
		}
		
		if(cnt >= maxCnt) {
			return 987654321;
		}
		
		if(cache.get(ptr) != null) {
			return cache.get(ptr);
		}
		
		int ret = 987654321;
		int val = 1<<cnt;
		for(int i = 0; i < 4; i++) {
			Long[] nextPtr = {ptr[0]+dir[i][0]*val, ptr[1]+dir[i][1]*val};
			ret = Math.min(ret, solve(nextPtr, cnt+1));			
		}
		//System.out.println(Arrays.toString(ptr)+">>"+ret);
		cache.put(ptr, ret);
		return ret;
	}
}
