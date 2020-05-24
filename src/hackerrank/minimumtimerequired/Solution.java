package hackerrank.minimumtimerequired;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minTime function below.
    static long minTime(long[] machines, long goal) {
    	Arrays.sort(machines);
    	//System.out.println(Arrays.toString(machines));
    	double low = (double)(goal/machines.length)*machines[0];
    	double high = (double)(goal/machines.length)*machines[machines.length-1];
    	//double low =  269029389928f;
    	//double high = 269029389988f;
    	for(int i = 0; i < 100; i++) {
    		double mid = (low+high)/2;
    		long tmp = 0;
    		for(Long day : machines) {
    			tmp += ((long)mid)/day;
    		}
    		System.out.println(String.format("%.12f", mid)+">>"+tmp+","+goal);
    		if(tmp >= goal) {
    			high = mid;
    		} else {
    			low = mid;
    		}
    	}
    	//System.out.println(String.format("%.12f", low)+","+String.format("%.12f", high));
    	return (long)Math.round((low+high)/2);
    }

    //private static final Scanner scanner = new Scanner(System.in);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nGoal = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nGoal[0]);

        long goal = Long.parseLong(nGoal[1]);

        long[] machines = new long[n];

        String[] machinesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long machinesItem = Long.parseLong(machinesItems[i]);
            machines[i] = machinesItem;
        }

        long ans = minTime(machines, goal);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
