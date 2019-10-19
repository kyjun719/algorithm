package nhn.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class Test2 {
	private static class NumInfo {
		int num;
		Queue<Integer> idxQueue = new LinkedList<>();
		NumInfo(int num) {
			this.num = num;
		}
		@Override
		public boolean equals(Object o) {
			if(o instanceof NumInfo) {
				NumInfo obj = (NumInfo) o;
				return obj.num == this.num;
			}
			return false;
		}
		@Override
		public int hashCode() {
			int ret = 17;
			ret = ret*31+num;
			return ret;
		}
		@Override
		public String toString() {
			return "("+num+">>"+idxQueue.toString()+")";
		}
	}
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(br.readLine());
			
			NumInfo[] numInfoArr = new NumInfo[101];
			for(int i = 0; i <= 100; i++) {
				numInfoArr[i] = new NumInfo(i);
			}
			String ret = "";
			int fqCnt = 0;
			for(int i = 0; i < n; i++) {
				String[] op = br.readLine().split(" ");
				//System.out.println(Arrays.toString(numInfoArr));
				
				if(op[0].equals("enqueue")) {
					numInfoArr[Integer.parseInt(op[1])].idxQueue.add(i);
					fqCnt++;
				} else {
					if(fqCnt > 0) {
						NumInfo[] sortedNumInfo = Arrays.copyOf(numInfoArr, 101);
						Arrays.sort(sortedNumInfo, new Comparator<NumInfo>() {
							@Override
							public int compare(NumInfo o1, NumInfo o2) {
								// TODO Auto-generated method stub
								if(o1.idxQueue.size() != o2.idxQueue.size()) {
									return o2.idxQueue.size()-o1.idxQueue.size();
								}
								
								if((o1.idxQueue.peek() != null) && (o2.idxQueue.peek() != null)) {
									return o1.idxQueue.peek() - o2.idxQueue.peek();
								}
								
								if(o1.idxQueue.peek() != null) {
									return 1;
								}
								
								if(o2.idxQueue.peek() != null) {
									return -1;
								}
								
								return 0;
							}
						});
						
						ret += (sortedNumInfo[0].num + " ");
						numInfoArr[sortedNumInfo[0].num].idxQueue.poll();
					} else {
						ret += "-1 ";
					}
				}
			}
			System.out.println(ret.substring(0, ret.length()-1));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
