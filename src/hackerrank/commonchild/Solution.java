package hackerrank.commonchild;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
	static ArrayList<Integer>[] idxArr;
	static int[][] cache;
    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {
    	/*
    	idxArr = new ArrayList[26];
    	int n = s1.length();
    	cache = new int[n][n];
    	for(int i = 0; i < n; i++) {
    		Arrays.fill(cache[i], -1);
    	}
    	
    	for(int i = 0; i < idxArr.length; i++) {
    		idxArr[i] = new ArrayList<>();
    	}
    	
    	for(int i = 0; i < s2.length(); i++) {
    		idxArr[s2.charAt(i)-'A'].add(i);
    	}

    	return solve(s1, 0, -1);
    	*/
    	int n = s1.length();
    	int[][] arr = new int[n+1][n+1];
    	for(int i = 1; i <= n; i++) {
    		for(int j = 1; j <= n; j++) {
    			if(s1.charAt(i-1) == s2.charAt(j-1)) {
    				//현재 글자를 더하므로 이전상태 글자의 길이 +1
    				arr[i][j] = arr[i-1][j-1]+1;
    			} else {
    				//s1 또는 s2의 상태중 가장 큰값
    				arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]);
    			}
    		}
    	}
    	return arr[n][n];
    }

    private static int solve(String s1, int s1_idx, int s2_idx) {
    	if(s1_idx == s1.length()) {
    		return 0;
    	}
    	
    	if(s2_idx >= 0 && cache[s1_idx][s2_idx] != -1) {
    		return cache[s1_idx][s2_idx];
    	}
    	
    	int ret = 0;
    	//s1에 해당하는 글자의 s2인덱스로 순환
    	for(int tmp : idxArr[s1.charAt(s1_idx)-'A']) {
    		if(tmp > s2_idx) {
    			ret = Math.max(ret, solve(s1, s1_idx+1, tmp)+1);
    		}
    	}
    	
    	//s1_idx의 글자 선택안함
    	ret = Math.max(ret, solve(s1, s1_idx+1, s2_idx));
    	if(s2_idx >= 0) {
    		cache[s1_idx][s2_idx] = ret;
    	}
		return ret;
	}

	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

