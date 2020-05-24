package hackerrank.candies;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the candies function below.
    static long candies(int n, int[] arr) {
    	int[] candies = new int[n];
    	candies[0] = 1;
    	for(int i = 1; i < n; i++) {
    		if(arr[i] > arr[i-1]) {
    			candies[i] = candies[i-1]+1;
    		} else {
    			candies[i] = 1;
    		}
    	}
    	
    	for(int i = n-1; i > 0; i--) {
    		if(arr[i-1] > arr[i]) {
    			if(candies[i-1] > candies[i]) {
    				continue;
    			}
    			candies[i-1] = candies[i]+1;
    		} else {
    			if(arr[i-1] == arr[i] || candies[i-1] <= candies[i]) {
    				continue;
    			}
    			candies[i-1] = 1;
    		}
    	}
    	
    	long ret = 0;
    	for(int i = 0; i < n; i++) {
    		ret += candies[i];
    	}
    	return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        long result = candies(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
