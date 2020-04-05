package hackerrank.sherlockandanagrams;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
    	HashMap<String, Integer> map = new HashMap<>();
    	int n = s.length();
    	for(int i = 0; i < n; i++) {
    		char[] keyArr = new char[n];
    		for(int j = i; j < n; j++) {
    			keyArr[0] = s.charAt(j);
    			Arrays.sort(keyArr);
    			String key = new String(keyArr);
    			
    			if(map.containsKey(key)) {
    				map.put(key, map.get(key)+1);
    			} else {
    				map.put(key, 1);
    			}
    		}
    	}

    	int ret = 0;
    	for(Map.Entry<String, Integer> entry : map.entrySet()) {
    		int val = entry.getValue();
    		if(val >= 2) {
    			ret += (val*(val-1)/2);
    		}
    	}
    	
    	return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
