package hackerrank.arrays.minimumswaps2;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
    	int cnt = 0;
    	for(int i = 0; i < arr.length; i++) {
    		//i번째 배열에는 i+1값이 들어와야함
    		while(arr[i] != i+1) {
    			//i번째 배열의 값과 값이 들어가야 하는 자리의 값을 바꿈
    			//이러면 i번째 배열은 맞는지는 알수없으나 원래 있던 값의 위치는 맞춤
    			int tmp = arr[i];
    			arr[i] = arr[tmp-1];
    			arr[tmp-1] = tmp;
    			cnt++;
    		}
    	}
    	return cnt;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

