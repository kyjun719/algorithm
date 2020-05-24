package hackerrank.balancedbrackets;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the isBalanced function below.
    static String isBalanced(String s) {
        Stack<Integer> st = new Stack<>();
        boolean ret = true;
        char[] openCh = {'(','{','['};
        char[] closeCh = {')','}',']'};
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == openCh[0] || ch == openCh[1] || ch == openCh[2]) {
                int idx = ch==openCh[0]?0:ch==openCh[1]?1:2;
                st.push(idx);
            } else {
                int idx = ch==closeCh[0]?0:ch==closeCh[1]?1:2;
                //스택이 비어있거나 여는 괄호가 일치하지 않는경우 실패
                if(st.isEmpty() || idx != st.pop()) {
                    ret = false;
                    break;
                }
            }
        }
        //모든 문자를 순회하였는데 닫히지 않은 괄호가 있는경우 실패
        if(!st.isEmpty()) {
            ret = false;
        }
        
        return ret?"YES":"NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            String result = isBalanced(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
