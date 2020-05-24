package hackerrank.sherlockandthevalidstring;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the isValid function below.
    static String isValid(String s) {
    	int[] chCnt = new int[26];
    	for(int i = 0; i < s.length(); i++) {
    		chCnt[s.charAt(i)-'a']++;
    	}

    	int[] info = new int[4];
    	Arrays.fill(info, -1);
    	int idx = 0;
    	for(int i = 0; i <chCnt.length; i++) {
    		if(chCnt[i] == 0) {
    			continue;
    		}
    		
    		boolean added = false;
    		if(info[0] == chCnt[i]) {
    			info[1]++;
    			added = true;
    		}
    		if(info[2] == chCnt[i]) {
    			info[3]++;
    			added = true;
    		}
    		
    		if(!added) {
    			if(idx == 4) {
    				return "NO";
    			}
    			
    			if(info[idx] == -1) {
        			info[idx] = chCnt[i];
        			info[++idx] = 1;
        			++idx;
        		}
    		}
    	}
    	if(info[2] == -1) {
    		return "YES";
    	}
    	
    	if((info[0] == 1 && info[1] == 1) && (info[2] == 1 && info[3] == 1)) {
    		return "YES";
    	}
    	System.out.println(Arrays.toString(chCnt));
    	System.out.println(Arrays.toString(info));
    	if((Math.abs(info[0]-info[2]) > 1) || (info[1] >= 2 && info[3] >= 2)) {
    		return "NO";
    	}
    	return "YES";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

