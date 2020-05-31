package hackerrank.friendcirclequeries;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the maxCircle function below.
    static int[] maxCircle(int[][] queries) {
    	HashMap<Integer, Integer[]> map = new HashMap<>();
    	int n = queries.length;
    	int[] ret = new int[n];
    	int val = 0;
    	for(int i = 0; i < n; i++) {
    		int a = queries[i][0];
    		int b = queries[i][1];
    		
    		boolean fa=false, fb=false;
    		while(true) {
    			if(!fa) {
    				if(map.get(a) == null || map.get(a)[0] == a) {
    					fa = true;
    				} else {
    					a = map.get(a)[0];
    				}
    			}
    			
    			if(!fb) {
    				if(map.get(b) == null || map.get(b)[0] == b) {
    					fb = true;
    				} else {
    					b = map.get(b)[0];
    				}
    			}
    			if(fa && fb) {
    				break;
    			}
    		}
    		
    		if(a != b) {
    			if(a > b) {
        			int tmp = a;
        			a = b;
        			b = tmp;
        		}
        		
        		if(map.get(b) == null) {
        			map.put(b, new Integer[]{a, 1});
        		} else {
        			map.put(b, new Integer[]{a, map.get(b)[1]});
        		}
        		
        		if(map.get(a) == null) {
        			map.put(a, new Integer[]{a, map.get(b)[1]+1});
        		} else {
        			map.put(a, new Integer[]{a, map.get(b)[1]+map.get(a)[1]});
        		}
        		
        		val =  Math.max(map.get(a)[1], val);
    		}
    		
    		ret[i] = val;
    	}
    	
    	return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] queries = new int[q][2];

        for (int i = 0; i < q; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        int[] ans = maxCircle(queries);

        for (int i = 0; i < ans.length; i++) {
            bufferedWriter.write(String.valueOf(ans[i]));

            if (i != ans.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
