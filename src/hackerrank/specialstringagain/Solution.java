package hackerrank.specialstringagain;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    private static class NumInfo {
        int start, end, len;
        public NumInfo(int start, int end) {
            this.start = start;
            this.end = end;
            len = end-start+1;
        }
    }
    
    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
    	//연속하는 문자열 정보 배열
        Queue<NumInfo>[] idxArr = new LinkedList[26];
        for(int i = 0; i < idxArr.length; i++) {
            idxArr[i] = new LinkedList<>();
        }
        //마지막에 초기 입력시 나타날 수 없는 문자열을 넣어 추가 코드없이 마지막글자까지 정보를 저장하도록 함
        s += '0';
        int len = 0;
        char ch='0';
        for(int i = 0; i < n+1; i++) {
            if(ch == s.charAt(i)) {
                len++;
            } else {
            	//이전글자와 현재글자가 다른 경우 정보 저장
                if(ch != '0') {
                    idxArr[ch-'a'].add(new NumInfo(i-len+1, i));
                }
                ch = s.charAt(i);
                len = 1;
            }
        }
        
        long cnt = 0;
        for(int i = 0; i < idxArr.length; i++) {
            Queue<NumInfo> q = idxArr[i];
            while(!q.isEmpty()) {
                NumInfo info = q.poll();
                //연속된 문자열의 정보로 갯수 계산
                cnt += ((double)info.len/2*(info.len+1));
                NumInfo next = q.peek();
                //다음 연속된 문자열이 있는 경우 대칭 문자열여부 확인
                if(next != null && next.start == info.end+2) {
                    cnt += Math.min(next.len, info.len);
                }
            }
        }

        return cnt;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
