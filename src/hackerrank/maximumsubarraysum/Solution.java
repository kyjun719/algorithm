package hackerrank.maximumsubarraysum;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    // Complete the maximumSum function below.
    static long maximumSum(long[] a, long m) {
    	Info[] prefix = new Info[a.length];
    	long sum = 0;
    	long maxSum = -1;
    	for(int i = 0; i < a.length; i++) {
    		sum = (sum+a[i])%m;
    		maxSum = Math.max(maxSum, sum);
    		prefix[i] = new Info(i, sum);
    	}
    	
    	Arrays.sort(prefix, (o1, o2) -> {
    		if(o1.prefix>o2.prefix) {
    			return 1;
    		} else if(o1.prefix == o2.prefix) {
    			return 0;
    		} else {
    			return -1;
    		}
    	});
    	//System.out.println(Arrays.toString(prefix));
    	
    	long ret = 0;
    	long min = Long.MAX_VALUE;
    	for(int i = 1; i < prefix.length; i++) {
    		if(prefix[i].idx < prefix[i-1].idx) {
    			if(prefix[i].prefix-prefix[i-1].prefix < min) {
    				min = prefix[i].prefix-prefix[i-1].prefix;
    			}
    		}
    	}
    	
    	return Math.max(maxSum, m-min);
    }
    
    static class Info {
    	long prefix;
    	int idx;
    	public Info(int idx, long prefix) {
    		this.idx = idx;
    		this.prefix = prefix;
    	}
    	@Override
    	public String toString() {
    		return "("+idx+":"+prefix+")";
    	}
    }
    
    static long ret;
    private static void solve(long[] a, long m, int idx, long sum) {
    	if(idx == a.length) {
    		ret = Math.max(ret, sum);
    		return;
    	}
    	
    	solve(a,m,idx+1,(sum+a[idx])%m);
    	solve(a,m,idx+1,sum);
	}

	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            long m = Long.parseLong(nm[1]);

            long[] a = new long[n];

            String[] aItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                long aItem = Long.parseLong(aItems[i]);
                a[i] = aItem;
            }

            long result = maximumSum(a, m);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
