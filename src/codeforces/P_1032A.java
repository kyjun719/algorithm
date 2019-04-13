package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P_1032A {	
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			int n = Integer.parseInt(reader.readLine().split(" ")[1]);
			int[] numList = new int[101];
			String[] val_arr = reader.readLine().split(" ");
			for(String tmp : val_arr) {
				numList[Integer.parseInt(tmp)]++;
			}
			Arrays.sort(numList);
			int max_val = numList[100]%n == 0? numList[100]: n*((int)numList[100]/n+1);
			int answer = 0;
			for(int i = 1; i < numList.length; i++) {
				if(numList[i] == 0) 
					continue;
				answer += (max_val - numList[i]);
			}
			System.out.println(answer);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
