package hackerrank.maximumxor;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
	static class Node {
		Node left,right;
	}
	
	static void insert(Node root, int num) {
		for(int i = 31; i >= 0; i--) {
			int val = (num>>i)&1;
			if(val == 0) {
				if(root.left == null) {
					root.left = new Node();
				}
				root = root.left;
			} else {
				if(root.right == null) {
					root.right = new Node();
				}
				root = root.right;
			}
		}
	}
	
	static int find(Node root, int num) {
		int max = 0;
		for(int i = 31; i >= 0; i--) {
			int val = (num>>i)&1;
			if(val == 0) {
				if(root.right != null) {
					max += (1<<i);
					root = root.right;
				} else {
					root = root.left;
				}
			} else {
				if(root.left != null) {
					max += (1<<i);
					root = root.left;
				} else {
					root = root.right;
				}
			}
		}
		
		return max;
	}

    // Complete the maxXor function below.
    static int[] maxXor(int[] arr, int[] queries) {
        // solve here
    	int m = queries.length;
    	int n = arr.length;
    	Node root = new Node();
    	for(int i = 0; i < n; i++) {
    		insert(root, arr[i]);
    	}
    	
    	int[] ret = new int[m];
    	for(int i = 0; i < m; i++) {
    		//String num = Integer.toBinaryString(queries[i]);
    		ret[i] = find(root, queries[i]);
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

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] queries = new int[m];

        for (int i = 0; i < m; i++) {
            int queriesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            queries[i] = queriesItem;
        }

        int[] result = maxXor(arr, queries);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
