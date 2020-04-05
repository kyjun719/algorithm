package hackerrank.repeatedstring;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
    	//문자열에 a 가 없는경우 종료
    	if(!s.contains("a")) {
    		return 0;
    	}

    	int len = s.length();
    	//문자열 인덱스당 누적 a값 계산
    	long[] cnt = new long[len+1];
    	for(int i = 1; i <= len; i++) {
    		cnt[i] = cnt[i-1];
    		if(s.charAt(i-1) == 'a') {
    			cnt[i]++;
    		}
    	}
    	
    	//s가 반복되는 횟수와 나타나는 인덱스값을 계산하여 a갯수 계산
    	return (n/(long)len)*cnt[len] + cnt[(int)(n%len)]; 
     }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

