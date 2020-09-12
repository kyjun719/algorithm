package acmicpc._19591;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
	private static int INF = 987654321;
	public static void main(String[] args) throws Exception {
//		System.out.println(calc(3,2,'/')+","+calc(-3,2,'/')+","+calc(3,-2,'/')+","+calc(-3,-2,'/'));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		Deque<Long> numDeque = new ArrayDeque<>();
		Deque<Character> opDeque = new ArrayDeque<>();
		boolean isSig=false;
		int idx=0;
		if(s.charAt(0)=='-') {
			idx++;
			isSig=true;
		}
		String tmp="";
		for(; idx<s.length(); idx++) {
			if(s.charAt(idx)=='+' || s.charAt(idx)=='-' || s.charAt(idx)=='*' || s.charAt(idx)=='/') {
				numDeque.add(Long.parseLong(tmp));
				tmp="";
				opDeque.add(s.charAt(idx));
			} else {
				tmp+=s.charAt(idx);
			}
		}
		
		if(!tmp.isEmpty()) {
			numDeque.add(Long.parseLong(tmp));
		}
		if(isSig) {
			long a = numDeque.pollFirst();
			numDeque.addFirst(-a);
		}
		
//		System.out.println(numDeque);
//		System.out.println(opDeque);
//		System.out.println("===================");
		
		while(true) {
			if(opDeque.size()==0) {
				break;
			}
			if(opDeque.size()==1) {
				long a=numDeque.pollFirst();
				long b=numDeque.pollLast();
				numDeque.addFirst(calc(a,b,opDeque.pollFirst()));
				break;
			}
			
			int left=getOrder(opDeque.peekFirst());
			int right=getOrder(opDeque.peekLast());
			
			if(left==right) {
				long la=numDeque.pollFirst();
				long lb=numDeque.peekFirst();
				long rb=numDeque.pollLast();
				long ra=numDeque.peekLast();
				long l = calc(la, lb, opDeque.peekFirst());
				long r = calc(ra, rb, opDeque.peekLast());
				if(l > r) {
					numDeque.pollFirst();
					numDeque.addFirst(l);
					opDeque.pollFirst();
					numDeque.addLast(rb);
				} else {
					numDeque.pollLast();
					numDeque.addLast(r);
					opDeque.pollLast();
					numDeque.addFirst(la);
				}
			} else if(left>right) {
				long a=numDeque.pollFirst();
				long b=numDeque.pollFirst();
				numDeque.addFirst(calc(a,b,opDeque.pollFirst()));
			} else {
				long b=numDeque.pollLast();
				long a=numDeque.pollLast();
				numDeque.addLast(calc(a,b,opDeque.pollLast()));
			}
		
//			System.out.println(numDeque);
//			System.out.println(opDeque);
//			System.out.println("===================");
		}
//		System.out.println(numDeque);
//		System.out.println(opDeque);
		System.out.println(numDeque.poll());
	}
	
	private static int getOrder(char s) {
		if(s=='+' || s=='-') {
			return 1;
		}
		return 2;
	}
	
	private static long calc(long a, long b, char op) {
		switch(op) {
			case '+':
				return a+b;
			case '-':
				return a-b;
			case '*':
				return a*b;
		}

		return a/b;
	}
}
