package hackerrank.counttriplets;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
    	//create r's geometric progression array
    	HashMap<Long, Long> map2 = new HashMap<>();
        HashMap<Long, Long> map3 = new HashMap<>();
        long cnt = 0;
        for(long k : arr) {
            //k가 a*r*r이 되는 경우
            if(map3.get(k) != null) {
                cnt += map3.get(k);
            }
            //k가 a*r이 되는 경우
            if(map2.get(k) != null) {
            	//k*r(=a*r*r)의 갯수를 더함
                long val = map3.get(k*r)==null?0:map3.get(k*r);
                map3.put(k*r, val+map2.get(k));
            }
            //k에 r을 곱한 값의 갯수를 더함
            long map2Cnt = map2.get(k*r)==null?0:map2.get(k*r);
            map2.put(k*r, map2Cnt+1);
        }
        
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
