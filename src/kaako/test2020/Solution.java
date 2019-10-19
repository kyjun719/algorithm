package kaako.test2020;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
aabbaccc
7

ababcdcdababcdcd
9

abcabcdede
8

abcabcabcabcdededededede
14

xababcdcdababcdcd
17
 */
class Solution {
	///*
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			new Solution().solution(br.readLine());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//*/
	public int solution(String s) {
		if(s.length() == 1) {
			return 1;
		}
    	String answerStr = "";
    	int n = s.length();
    	for(int i = 1; i <= n/2; i++) {
    		String tmpAnswer = "";
    		String comp = "";
    		int compCnt = 1;
    		String inputStr = s;
    		while(true) {
    			if(inputStr.length() < i || inputStr.isEmpty()) {
    				tmpAnswer += compCnt == 1?comp:(compCnt+comp);
    				tmpAnswer += inputStr;
    				break;
    			}
    			
    			String nextComp = inputStr.substring(0,i);
    			inputStr = inputStr.substring(i);
    			
    			if(comp.isEmpty()) {
    				comp = nextComp;
    			} else {
    				if(comp.equals(nextComp)) {
    					compCnt++;
    				} else {
    					tmpAnswer += compCnt == 1?comp:(compCnt+comp);
    					comp = nextComp;
    					compCnt = 1;
    				}
    			}
    		}
    		
    		//System.out.println(i+">>"+tmpAnswer);
    		if(answerStr.isEmpty() || (answerStr.length() > tmpAnswer.length())) {
    			answerStr = tmpAnswer;
    		}
    	}
    	
    	System.out.println(answerStr.length()+">>"+answerStr);
    	return answerStr.length();
    }
	
	public String getCompString(int i, String inputStr) {
		String ret = "";
		String comp = "";
		int compCnt = 1;
		while(true) {
			if(inputStr.length() < i || inputStr.isEmpty()) {
				ret += compCnt == 1?comp:(compCnt+comp);
				ret += inputStr;
				break;
			}
			
			String nextComp = inputStr.substring(0,i);
			inputStr = inputStr.substring(i);
			
			if(comp.isEmpty()) {
				comp = nextComp;
			} else {
				if(comp.equals(nextComp)) {
					compCnt++;
				} else {
					ret += compCnt == 1?comp:(compCnt+comp);
					comp = nextComp;
					compCnt = 1;
				}
			}
		}
		
		return ret;
	}
}