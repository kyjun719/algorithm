package nhn.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Test1 {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(br.readLine());
			String[] cards = br.readLine().split(" ");
			Integer[] cardCount = new Integer[n];
			Arrays.fill(cardCount, 0);
			HashMap<String, Integer> cardMap = new HashMap<>();
			int idx = 0;
			for(int i = 0; i < n ;i++) {
				if(cardMap.containsKey(cards[i])) {
					cardCount[cardMap.get(cards[i])]++;
					continue;
				} else {
					cardCount[idx] = 1;
					cardMap.put(cards[i], idx);
					idx++;
				}
			}
			//System.out.println(Arrays.toString(cardCount));
			boolean beforeBuy = false;
			boolean canBuy = true;
			Arrays.sort(cardCount, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return o2-o1;
				}
			});
			//System.out.println(Arrays.toString(cardCount));
			int cnt = cardCount[0];
			for(int i = 1; i < cardMap.size(); i++) {
				//System.out.println(cnt+","+cardCount[i]+">>"+beforeBuy+","+canBuy);
				if(cnt == cardCount[i]) {
					continue;
				}
				if((cnt == cardCount[i]+1) && !beforeBuy) {
					beforeBuy = true;
				} else {
					canBuy = false;
					break;
				}
			}
			
			if(canBuy) {
				System.out.println("Y");
				System.out.println(beforeBuy?n+1:n);
				System.out.println(cardMap.keySet().size());
			} else {
				System.out.println("N");
				System.out.println(n);
				System.out.println(cardMap.keySet().size());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
