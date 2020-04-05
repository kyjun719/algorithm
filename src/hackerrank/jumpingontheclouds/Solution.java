package hackerrank.jumpingontheclouds;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the jumpingOnClouds function below.
    static int jumpingOnClouds(int[] c) {
    	int n = c.length;
    	int[] cntArr = new int[c.length];
    	Arrays.fill(cntArr, 987654321);
    	//초기값 설정
    	cntArr[0] = 0;
    	cntArr[1] = (c[1]==0)?1:987654321;
    	
    	for(int i = 2; i < c.length; i++) {
    		if(c[i] == 0) {
    			//바로 직전칸과 두번째 전 칸을 비교하여 최소값 배열에 저장
    			cntArr[i] = Math.min(cntArr[i-1], cntArr[i-2])+1;
    		}
    	}
    	return cntArr[n-1];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = jumpingOnClouds(c);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
