package hackerrank.decibinarynumbers;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
	// Complete the decibinaryNumbers function below.
    static long decibinaryNumbers(long x) {
        if(f == null) {
            buildF();
            buildC();
        }

        //x번째의 숫자 찾기
        int s = findS(x);
        //x번째 숫자 = s의 x-(s-1의 누적합)번째 수
        long g = x - (s == 0 ? 0 : c[s - 1]);

        StringBuilder result = new StringBuilder();
        for (int d = LIMIT_D; d >= 1; d--) {
            int j = -1;
            long prevNumberCount = -1;
            long numberCount = 0;
            //모든 자릿수를 순회하면서 해당 자릿수의 수 찾음
            while (numberCount < g) {
                j++;

                prevNumberCount = numberCount;
                numberCount += f[d - 1][s - j * (1 << (d - 1))];
            }
            
            result.append(j);
            s -= j * (1 << (d - 1));
            g -= prevNumberCount;
        }
        return Long.parseLong(result.toString());
    }
    
    //최대 자릿수
    static final int LIMIT_D = 19;
    //최대 숫자
    static final int LIMIT_S = 300000;

    static long[][] f;
    static long[] c;

    static int findS(long x) {
    	//누적합이 x인 수 검색
        int index = Arrays.binarySearch(c, x);
        //사이값인경우 숫자 재계산
        if (index < 0) {
            index = -1 - index;
        }
        return index;
    }

    static void buildF() {
    	//숫자s를 d자리의 수로 표현할 떄 갯수 계산 
        f = new long[LIMIT_D + 1][LIMIT_S + 1];
        for (int d = 0; d <= LIMIT_D; d++) {
            for (int s = 0; s <= LIMIT_S; s++) {
                if (d == 0) {
                    if (s == 0) {
                        f[d][s] = 1;
                    } else {
                        f[d][s] = 0;
                    }
                } else {
                    f[d][s] = 0;
                    for (int i = 0; i <= 9; i++) {
                        long nextS = s - i * (1L << (d - 1));
                        if (nextS >= 0) {
                            f[d][s] += f[d - 1][(int) nextS];
                        }
                    }
                }
            }
        }
    }

    static void buildC() {
        //숫자 i에 대한 갯수의 누적합 계산
        c = new long[LIMIT_S + 1];
        long sum = 0;
        for (int i = 0; i < c.length; i++) {
            sum += f[LIMIT_D][i];
            c[i] = sum;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            long x = scanner.nextLong();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            long result = decibinaryNumbers(x);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
