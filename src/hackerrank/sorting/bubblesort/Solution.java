package hackerrank.sorting.bubblesort;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the countSwaps function below.
    static void countSwaps(int[] a) {
    	int swapCnt = 0;
    	for(int i = 0; i < a.length; i++) {
    		boolean isSwap = false;
    		for(int j = 0; j < a.length-1; j++) {
    			if(a[j] > a[j+1]) {
    				swapCnt++;
    				isSwap = true;
    				
    				int tmp = a[j+1];
    				a[j+1] = a[j];
    				a[j] = tmp;
    			}
    		}
    		if(!isSwap) {
    			break;
    		}
    	}
    	System.out.println("Array is sorted in "+swapCnt+" swaps.");
    	System.out.println("First Element: "+a[0]);
    	System.out.println("Last Element: "+a[a.length-1]);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        countSwaps(a);

        scanner.close();
    }
}
