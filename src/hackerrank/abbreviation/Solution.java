package hackerrank.abbreviation;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the abbreviation function below.
    static String abbreviation(String a, String b) {
        cache = new int[a.length()][b.length()];
        for(int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }
        boolean ret = solve2(a,b,a.length()-1,b.length()-1);
        return ret?"YES":"NO";
    }
    
    static int[][] cache;
    static boolean solve2(String a, String b, int idxA, int idxB) {
        if(idxB < 0) {
            for(int i = 0; i < idxA; i++) {
                if(a.charAt(i) >= 'A' && a.charAt(i) <= 'Z') {
                    return false;
                }
            }
            return true;
        }
        
        if(idxA < 0) {
            return false;
        }

        if(cache[idxA][idxB] != -1) {
            return cache[idxA][idxB]==1;
        }
        
        if(a.charAt(idxA) >= 'A' && a.charAt(idxA) <= 'Z') {
            if(a.charAt(idxA) == b.charAt(idxB)) {
                boolean ret = solve2(a,b,idxA-1, idxB-1);
                cache[idxA][idxB] = ret?1:0;
                return ret;
            } else {
                cache[idxA][idxB] = 0;
                return false;
            }
        } else {
            boolean ret = solve2(a,b,idxA-1, idxB);
            if(a.charAt(idxA)-32 == b.charAt(idxB)) {
                ret |= solve2(a,b,idxA-1, idxB-1);
            }
            cache[idxA][idxB] = ret?1:0;
            return ret;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
