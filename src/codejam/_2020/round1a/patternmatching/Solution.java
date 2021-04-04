package codejam._2020.round1a.patternmatching;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 1; t <= tc; t++) {
				int n = Integer.parseInt(br.readLine());
				
				ArrayList<String> p = new ArrayList<>();
				for(int i = 0; i < n; i++) {
					p.add(br.readLine());
				}
				String ret = solve(p, n);
				if(ret == null || ret.isEmpty()) {
					ret = "*";
				}
				System.out.println("Case #"+t+": "+ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String solve(ArrayList<String> p, int n) {
		String[] prefixArr = new String[n];
		String[] suffixArr = new String[n];
		String[][] middleArr = new String[100][n];
		
		for(int i = 0; i < 100; i++) {
			Arrays.fill(middleArr[i], "");
		}
		
		for(int i = 0; i < n; i++) {
			String word = p.get(i);
			
			int firstIdx = word.indexOf("*");
			int lastIdx = word.lastIndexOf("*");
			
			if(firstIdx == lastIdx) {
				//has 1 *
				String prefix = "";
				String suffix = "";
				if(firstIdx == 0) {
					//start with *, empty prefix
					suffix = word.substring(firstIdx+1);
				} else if(firstIdx == word.length()-1) {
					//end with *, empty suffix
					prefix = word.substring(0, firstIdx);
				} else {
					//middle of *
					prefix = word.substring(0, firstIdx);
					suffix = word.substring(lastIdx+1);
				}
				prefixArr[i] = prefix;
				suffixArr[i] = suffix;
			} else {
				//has many *
				//set prefix and suffix
				String prefix = word.substring(0, firstIdx);
				String suffix = word.substring(lastIdx+1);
				
				prefixArr[i] = prefix;
				suffixArr[i] = suffix;
				
				String tmpWord = word.substring(firstIdx+1, lastIdx);
				int cnt = 0;
				while(true) {
					int asterIdx = tmpWord.indexOf("*");
					if(asterIdx == -1) {
						middleArr[cnt][i] = tmpWord;
						break;
					} else {
						String middle = tmpWord.substring(0, asterIdx);
						middleArr[cnt][i] = middle;
						cnt++;
						tmpWord = tmpWord.substring(asterIdx+1);
					}
				}
			}
		}
		
		///*
		System.out.println(Arrays.toString(prefixArr));
		System.out.println(Arrays.toString(suffixArr));
		System.out.println("==================");
		for(int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(middleArr[i]));
		}
		System.out.println("==================");
		//*/
		
		String prefix = getOverlapWord(prefixArr, true);
		String suffix = getOverlapWord(suffixArr, false);
		if(prefix == null || suffix == null) {
			return null;
		}
		
		String ret = prefix;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < n; j++) {
				ret += middleArr[i][j];
			}
		}
		ret += suffix;
		return ret;
	}

	private static String getOverlapWord(String[] arr, boolean isStart) {
		//find max len word
		String maxWord = "";
		for(int i = 0; i < arr.length; i++) {
			if(maxWord.length() < arr[i].length()) {
				maxWord = arr[i];
			}
		}
		
		//max len word is empty, return empty string
		if(maxWord.isEmpty()) {
			return "";
		}
		
		if(isStart) {
			//check maxWord contains other word
			for(int i = 0; i < arr.length; i++) {
				if(!maxWord.startsWith(arr[i])) {
					return null;
				}
			}
		} else {
			//check maxWord contains other word
			for(int i = 0; i < arr.length; i++) {
				if(!maxWord.endsWith(arr[i])) {
					return null;
				}
			}
		}
		
		return maxWord;
	}
}
