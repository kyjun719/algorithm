package hackerrank.reverseshufflemerge;

import java.io.*;
import java.math.*;
import java.nio.charset.Charset;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the reverseShuffleMerge function below.
    static String reverseShuffleMerge(String s) throws IOException {
    	int[] cnt = new int[26];
        char small = 'z';
        for(char ch : s.toCharArray()) {
            cnt[ch-'a']++;
            small = small>ch?ch:small;
        }

        int[] passAllowCnt = new int[26];
        int[] leftCnt = new int[26];
        for(int i = 0; i < cnt.length; i++) {
            if(cnt[i] > 0) {
                passAllowCnt[i] = cnt[i]/2;
                leftCnt[i] = cnt[i]/2;
            }
        }
    	
        String ret = "";
        int bestIdx = -1;
        char bestSeen = 'z'+1;
        
        for(int i = s.length()-1; i >= 0; i--) {
        	char nowCh = s.charAt(i);
        	//현재 문자를 쓸수 없는경우
        	if(leftCnt[nowCh-'a'] == 0) {
        		continue;
        	}
        	//현재 문자가 가장 작은 문자보다 큰 경우
        	if(nowCh > small) {
        		//해당 문자를 못지나 나가는경우
        		if(passAllowCnt[nowCh-'a'] == 0) {
        			//현재문자가 이전에 본 문자보다 작은경우
        			if(nowCh < bestSeen) {
        				leftCnt[nowCh-'a']--;
        				ret += nowCh;
        				bestSeen = 'z'+1;
        			} else {
        				//이전에 본 문자보다 큰 경우 이전에 본 문자로 돌아감
        				while(i < bestIdx) {
        					i++;
        					passAllowCnt[s.charAt(i)-'a']++;
        				}
        				leftCnt[s.charAt(i)-'a']--;
        				ret += s.charAt(i);
        				bestSeen = 'z'+1;
        			}
        		} else {
        			//해당 문자를 지나감
        			passAllowCnt[nowCh-'a']--;
        			//해당 문자가 이전에본 문자보다 작은경우 해당 문자를 가장 작은 문자로 설정
        			if(nowCh < bestSeen) {
        				bestSeen = nowCh;
        				bestIdx = i;
        			}
        		}
        	} else {
        		//현재 문자가 가장 작은 문자보다 작은 경우, 해당문자를 넣음
        		leftCnt[nowCh-'a']--;
    			ret += nowCh;
    		
    			//쓸수 있는 문자중 가장 작은문자 검색
    			for(int j = 0; j < cnt.length; j++) {
                    if(leftCnt[j] > 0) {
                        small = (char)('a'+j);
                        break;
                    }
                }
    			bestSeen = 'z'+1;
        	}
        }

        return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = scanner.nextLine();

        String result = reverseShuffleMerge(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
