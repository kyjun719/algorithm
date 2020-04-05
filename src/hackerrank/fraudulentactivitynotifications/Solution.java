package hackerrank.fraudulentactivitynotifications;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
    	int cnt = 0;
    	int[] numCnt = new int[201];
    	
    	for(int i = 0; i < d; i++) {
    		numCnt[expenditure[i]]++;
    	}
    	
    	for(int i = d; i < expenditure.length; i++) {
			int midIdx = (d+1)/2;
			int oddNum = 0, evenNum = 0;
			for(int j = 0; j < numCnt.length; j++) {
				if(midIdx > numCnt[j]) {
					midIdx -= numCnt[j];
				} else {
					oddNum = j;
					if(midIdx == numCnt[j]) {
						j++;
						while(numCnt[j] == 0) {
							j++;
						}
						evenNum = j;
					} else {
						evenNum = j;
					}
					break;
				}
			}
			
			//System.out.println("odd::"+oddNum+",even::"+evenNum);
			double midNum = (d%2==0)?(double)(oddNum+evenNum)/2:oddNum;
			if(midNum*2 <= (double)expenditure[i]) {
				cnt++;
			}
			
			numCnt[expenditure[i-d]]--;
			numCnt[expenditure[i]]++;
    	}

    	return cnt;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

