package hackerrank.hashtables.icecreamparlor;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the whatFlavors function below.
    static void whatFlavors(int[] cost, int money) {
    	HashMap<Integer, Integer> left = new HashMap<>();
    	int[] ret = new int[2];
    	for(int i = 0; i < cost.length; i++) {
    		if(cost[i] >= money) {
    			continue;
    		}
    		
    		if(left.get(cost[i]) != null) {
    			ret[0] = left.get(cost[i])+1;
    			ret[1] = i+1;
    			break;
    		}
    		if(left.get(money-cost[i]) == null) {
    			left.put(money-cost[i], i);
    		}
    	}
    	System.out.println(ret[0]+ " " + ret[1]);
    }

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
    	scanner = new Scanner(new File("input.txt"));
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int money = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] cost = new int[n];

            String[] costItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int costItem = Integer.parseInt(costItems[i]);
                cost[i] = costItem;
            }

            whatFlavors(cost, money);
        }

        scanner.close();
    }
}

