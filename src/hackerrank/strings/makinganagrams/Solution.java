package hackerrank.strings.makinganagrams;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
    	int idx = 0;
    	int cnt = 0;
    	String atmp = new String(a);
    	String btmp = new String(b);
    	while(idx < atmp.length()) {
    		String ch = String.valueOf(atmp.charAt(idx));
    		if(btmp.contains(ch)) {
    			atmp = atmp.substring(0, idx)+atmp.substring(idx+1);
    			int b_idx = btmp.indexOf(ch);
    			btmp = btmp.substring(0, b_idx)+btmp.substring(b_idx+1);
    			cnt++;
    		} else {
    			idx++;
    		}
    	}
    	return a.length()+b.length()-2*cnt;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

