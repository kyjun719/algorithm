package codejam._2020.qualification.parentingpartneringreturns;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
	static class TimeInfo {
		int start;
		int end;
		int idx;
		String name;
		public TimeInfo(int idx, int start, int end) {
			this.idx = idx;
			this.start = start;
			this.end = end;
		}
		@Override
		public String toString() {
			return "["+start+"~"+end+"]";
		}
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 1; t <= tc; t++) {
				int n = Integer.parseInt(br.readLine());
				List<TimeInfo> timeList = new ArrayList<>();
				for(int i = 0; i < n; i++) {
					int[] tmp = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
					timeList.add(new TimeInfo(i, tmp[0], tmp[1]));
				}
				timeList.sort(new Comparator<TimeInfo>() {
					@Override
					public int compare(TimeInfo o1, TimeInfo o2) {
						return o1.start-o2.start;
					}
				});
				//System.out.println(timeList);
				
				int timeC = 0;
				int timeJ = 0;
				String ret = "";
				for(TimeInfo info : timeList) {
					int start = info.start;
					boolean canDo = false;
					if(timeC <= start) {
						timeC = info.end;
						info.name = "C";
						canDo = true;
					} else if(timeJ <= start) {
						timeJ = info.end;
						canDo = true;
						info.name = "J";
					}
					
					if(!canDo) {
						ret = "IMPOSSIBLE";
						break;
					}
				}
				if(ret.isEmpty()) {
					timeList.sort(new Comparator<TimeInfo>() {
						@Override
						public int compare(TimeInfo o1, TimeInfo o2) {
							return o1.idx-o2.idx;
						}
					});
					for(TimeInfo info : timeList) {
						ret += info.name;
					}
				}
				System.out.println("Case #"+t+": "+ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
