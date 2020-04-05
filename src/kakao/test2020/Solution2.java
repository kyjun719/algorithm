package kakao.test2020;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
(()())()
(()())()

)(
()

()))((()
()(())()
*/
public class Solution2 {
	/*
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(new Solution2().solution(br.readLine()));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//*/
	
	public String solution(String p) {
		//1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
		if(p.isEmpty()) {
			return "";
		}
		String answer = "";
		//2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 
		//단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며,
		//v는 빈 문자열이 될 수 있습니다.
		String u = "", v = "";
		for(int i = p.length(); i > 0; i-=2) {
			String tmpU = p.substring(0, i);
			int startCnt = getCount('(', tmpU);
			int endCnt = getCount(')', tmpU);
			if(startCnt == endCnt) {
				u = tmpU;
				v = p.substring(i);
			}
		}
		//System.out.println("before::"+u+","+v);
		
		if(isRightBracket(u)) {
			//3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다. 
			//3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
			//System.out.println(u+","+v+">>"+u+solution(v));
			return u+solution(v);
		} else {
			//4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다. 
			//4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
			//4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다. 
			//4-3. ')'를 다시 붙입니다.
			//System.out.println(u+","+v+">>"+"("+solution(v)+")"+removeAndReverse(u));
			answer = "("+solution(v)+")";
			//4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
			answer += removeAndReverse(u);
			//4-5. 생성된 문자열을 반환합니다.
			return answer;
		}
    }

	//u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
	private String removeAndReverse(String u) {
		String tmp = u.substring(1,u.length()-1);
		String ret = "";
		for(int i = 0; i < tmp.length(); i++) {
			if(tmp.charAt(i) == ')') {
				ret += "(";
			} else {
				ret += ")";
			}
		}
		return ret;
	}

	private boolean isRightBracket(String u) {
		int cnt = 0;
		for(int i = 0; i < u.length(); i++) {
			if(u.charAt(i) == '(') {
				cnt++;
			} else {
				cnt--;
			}
			if(cnt < 0) {
				return false;
			}
		}
		return cnt == 0;
	}

	private int getCount(char ch, String tmpU) {
		int cnt = 0;
		for(int i = 0; i < tmpU.length(); i++) {
			if(tmpU.charAt(i) == ch) {
				cnt++;
			}
		}
		return cnt;
	}
}
