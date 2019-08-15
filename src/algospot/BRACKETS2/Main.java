package algospot.BRACKETS2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @see https://algospot.com/judge/problem/read/BRACKETS2
 * @author jun
 * input
3
()()
({[}])
({}[(){}])

 * output
YES
NO
YES
 */
public class Main {
	private static List<Character> opening = Arrays.asList('(','{','[');
	private static List<Character> closing = Arrays.asList(')','}',']');
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int tc = Integer.parseInt(br.readLine());
			
			for(int t = 0; t < tc; t++) {
				String txt = br.readLine();
				System.out.println(solve(txt)?"YES":"NO");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean solve(String txt) {
		Stack<Character> openingStack = new Stack<>();
		for(int i = 0; i < txt.length(); i++) {
			if(opening.contains(txt.charAt(i))) {
				openingStack.push(txt.charAt(i));
			} else {
				if(openingStack.isEmpty()) {
					return false;
				}
				if(opening.indexOf(openingStack.pop()) != closing.indexOf(txt.charAt(i))) {
					return false;
				}
			}
		}
		
		return openingStack.isEmpty();
	}
}
