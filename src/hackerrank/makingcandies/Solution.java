package hackerrank.makingcandies;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minimumPasses function below.
    static long minimumPasses(long m, long w, long p, long n) {
    	BigInteger candies = new BigInteger(Long.toString(0));
        long days = 0;
        long answer = (long)Math.ceil((double)n/m/w);
        BigInteger N = new BigInteger(Long.toString(n));
        while(candies.compareTo(N) < 0) {
            if(answer <= days) {
                return answer;
            }
            
            if(p > m*w) {
                long dayNeeds = (long)Math.ceil((double)(p-candies.longValue())/m/w);
                if(dayNeeds >= ((double)(n-candies.longValue())/m/w)) {
                    return Math.min(days+dayNeeds, answer);
                }
                candies = candies.add(new BigInteger(Long.toString(dayNeeds))
                        .multiply(new BigInteger(Long.toString(m)))
                        .multiply(new BigInteger(Long.toString(w)))
                        );
                days += dayNeeds;
            }
            
            answer = (long)Math.min(answer, days+Math.ceil(((double)n-candies.longValue())/m/w));
            
            long cnt = candies.longValue()/p;
            long purchased = Math.min(Math.abs(m-w), cnt);
            if(m < w) {
                m += purchased;
            } else {
                w += purchased;
            }
            long rest = cnt-purchased;
            m += rest/2;
            w += (rest-rest/2);
            
            candies = new BigInteger(Long.toString(candies.longValue()%p))
                    .add(new BigInteger(Long.toString(m)).multiply(new BigInteger(Long.toString(w))));
            days++;
            //System.out.println(days+","+answer+">>"+m+","+w+","+candies);
        }
        return days;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] mwpn = scanner.nextLine().split(" ");

        long m = Long.parseLong(mwpn[0]);

        long w = Long.parseLong(mwpn[1]);

        long p = Long.parseLong(mwpn[2]);

        long n = Long.parseLong(mwpn[3]);

        long result = minimumPasses(m, w, p, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
