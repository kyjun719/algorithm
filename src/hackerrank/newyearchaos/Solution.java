package hackerrank.newyearchaos;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
    	long ret = 0;
    	for(int i = 0; i < q.length; i++) {
    		//배열값-(인덱스+1)이 2보다 크면 배열값의 원래 위치보다 두개 초과 나온것이므로 chaotic 반환
    		if(q[i]-(i+1) > 2) {
    			System.out.println("Too chaotic");
    			return;
    		}
    		
    		//앞의 두 배열을 본인과 비교하여 큰값이 있으면 "뇌물"을 준것이므로 큰값이 있으면 더함
    		int startIdx = Math.max(0, q[i]-2);
    		for(int j=startIdx; j < i; j++) {
    			if(q[j] > q[i]) {
    				ret++;
    			}
    		}
    	}
    	
    	System.out.println(ret);
    }



    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}
