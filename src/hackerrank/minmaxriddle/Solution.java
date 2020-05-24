package hackerrank.minmaxriddle;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the riddle function below.
    static long[] riddle(long[] arr) {
        // complete this function
    	int n = arr.length;
    	arr = Arrays.copyOf(arr, n+1);
    	
    	long[] ret = new long[n];
    	HashMap<Long, Integer> cntMap = new HashMap<>();
    	Stack<Integer> st = new Stack<>();
    	for(int i = 0; i < arr.length; i++) {
    		if(!st.isEmpty() && arr[st.peek()] >= arr[i]) {
    			while(!st.isEmpty() && arr[st.peek()] >= arr[i]) {
    				int idx = st.pop();
    				int wid = 0;
    				if(st.isEmpty()) {
    					wid = i;
    				} else {
    					wid = i-st.peek()-1;
    				}
    				if(cntMap.get(arr[idx]) == null) {
    					cntMap.put(arr[idx], wid);
    				} else {
    					cntMap.put(arr[idx], Math.max(wid, cntMap.get(arr[idx])));
    				}
    			}
    		}
    		st.push(i);
    	}
    	for(Map.Entry<Long, Integer> e : cntMap.entrySet()) {
    		//System.out.println(e.getKey()+">>"+e.getValue());
    		for(int i = 0; i < e.getValue(); i++) {
    			ret[i] = Math.max(ret[i], e.getKey());
    		}
    	}
    	
    	return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] arr = new long[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long arrItem = Long.parseLong(arrItems[i]);
            arr[i] = arrItem;
        }

        long[] res = riddle(arr);

        for (int i = 0; i < res.length; i++) {
            bufferedWriter.write(String.valueOf(res[i]));

            if (i != res.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
