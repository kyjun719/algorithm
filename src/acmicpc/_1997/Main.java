package acmicpc._1997;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int n = info[0];
		int w = info[1];
		int b = info[2];
		
		DecoPlane[] arr = new DecoPlane[n];
		for(int i = 0; i < n; i++) {
			DecoPlane dp = new DecoPlane(Integer.parseInt(br.readLine()));
			for(int j=0; j<dp.h; j++) {
				dp.block[j]=br.readLine();
			}
			arr[i]=dp;
		}
		
		DecoPlane bef=new DecoPlane(0);
		ArrayList<Integer> list = new ArrayList<>();
		int ret=0;
		for(DecoPlane now : arr) {
//			System.out.println(now);
//			System.out.println("=========================");
//			System.out.println(bef);
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
			if(bef.h==0) {
				ret+=now.h;
				bef=now;
			} else {
				int tmp = bef.minSummationHeight(now); 
				if(ret+tmp>b) {
					list.add(ret);
					ret=now.h;
				} else {
					ret+=tmp;
				}
				bef=now;
			}
//			System.out.println(list+","+ret);
//			System.out.println("======================");
		}
		if(ret>0) {
			list.add(ret);
		}
		if(list.size()>0) {
			System.out.println(list.toString().replace("[", "").replace("]","").replace(",", ""));
		}
	}
	
	private static class DecoPlane {
		int h;
		String[] block;
		public DecoPlane(int h) {
			this.h = h;
			block = new String[h];
		}
		public int minSummationHeight(DecoPlane next) {
			int ret = next.h;
			
			for(int i = 0; i < h; i++) {
				boolean isSuccess = true;
				for(int k=0; k<=i; k++) {
					if(k>=next.h) {
						break;
					}
					
					for(int j = 0; j < block[k].length(); j++) {
						if(block[k].charAt(j)=='X' && next.block[next.h-k-1].charAt(j)=='X') {
							isSuccess = false;
							break;
						}
					}
//					System.out.println((k)+">>"+block[k]);
//					System.out.println((next.h-k-1)+">>"+next.block[next.h-k-1]);
//					System.out.println("===================");
				}
				if(!isSuccess) {
					break;
				}
				ret--;
			}
			return ret;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for(String s : block) {
				sb.append(s+"\n");
			}
			return sb.toString();
		}
	}
}
