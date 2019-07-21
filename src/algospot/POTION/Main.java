package algospot.POTION;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/POTION
 * @author jun
 * input
3
4
4 6 2 4
6 4 2 4
4
4 6 2 4
7 4 2 4
3
4 5 6
1 2 3

 * output
0 5 1 2
1 8 2 4
3 3 3
 */
public class Main {
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int tc = Integer.parseInt(bf.readLine());
			
			for(int t = 0; t < tc; t++) {
				int n = Integer.parseInt(bf.readLine());
				int[] need = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				int[] input = Arrays.stream(bf.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				
				//get gcd of need
				int val = need[0];
				for(int i = 1; i < n; i++) {
					val = gcd(val, need[i]);
				}
				
				//get max(ceil(input[i]/need[i]*gcd))
				int a = 0;
				for(int i = 0; i < n; i++) {
					a = Math.max(a, (int) Math.ceil((double)input[i]/(need[i]/val)));
				}
				
				//calculate answer
				int[] answer = new int[n];
				for(int i = 0; i < n; i++) {
					answer[i] = a*need[i]/val - input[i];
				}
				
				System.out.println(Arrays.toString(answer)
						.replace("[", "")
						.replace("]", "")
						.replace(",", ""));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static int gcd(int a, int b) {
		return b == 0? a : gcd(b, a%b);
	}
}
