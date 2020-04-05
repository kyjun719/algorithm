package hackerrank.arraymanipulation;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {   	
    	int[] valArr = new int[n+2];
    	
    	//값의 시작값에 +k, 끝값+1에 -k를 더함
    	for(int i = 0; i < queries.length; i++) {
    		int a = queries[i][0];
    		int b = queries[i][1];
    		int k = queries[i][2];
    		
    		valArr[a] += k;
    		valArr[b+1] -= k;
    	}
    	
    	long ret = 0;
    	long val = 0;
    	//더한값들을 돌면서 최대값 계산
    	for(int i = 0; i < n+1; i++) {
    		val += valArr[i];
    		ret = Math.max(ret, val);
    	}
    	
    	return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
