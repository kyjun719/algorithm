package hackerrank.largestrectangle;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the largestRectangle function below.
    static long largestRectangle(int[] h) {
    	int[] arr = Arrays.copyOf(h, h.length+1);
    	Stack<Integer> st = new Stack<>();
    	long ret = 0;
    	for(int i = 0; i < arr.length; i++) {
    		if(!st.isEmpty() && arr[st.peek()] >= arr[i]) {
    			while(!st.isEmpty() && (arr[st.peek()] >= arr[i])) {
    				int idx = st.pop();
    				int width = 0;
    				if(st.isEmpty()) {
    					width = i;
    				} else {
    					width = i-1-st.peek();
    				}
    				ret = Math.max(ret, width*arr[idx]);
    			}
    		}
    		st.add(i);
    	}
    	
    	return ret;
    }
    
    static class Info {
    	int idx, val;
    	public Info(int idx, int val) {
    		this.idx = idx;
    		this.val = val;
    	}
    	@Override
    	public String toString() {
    		return idx+"::"+val;
    	}
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] h = new int[n];

        String[] hItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int hItem = Integer.parseInt(hItems[i]);
            h[i] = hItem;
        }

        long result = largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
