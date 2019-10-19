package acmicpc._3954;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//TODO
//fail
public class Main {
	private static int m,c,i;
	private static int[] mem;
	private static char[] code;
	private static String input;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int tc = Integer.parseInt(br.readLine());
			for(int t = 0; t < tc; t++) {
				int[] info = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				m = info[0];
				c = info[1];
				i = info[2];
				mem = new int[m];
				code = br.readLine().toCharArray();
				input = br.readLine();
				System.out.println(solve());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static class LoopPoint {
		int start,end;
		LoopPoint(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	private static String solve() {
		Stack<LoopPoint> tmpStack = new Stack<>();
		LoopPoint[] loopArr = new LoopPoint[c];
		for(int i = 0; i < c; i++) {
			if(code[i] == '[') {
				//System.out.println(i+">>"+code[i]);
				LoopPoint ptr = new LoopPoint(i,-1);
				tmpStack.push(ptr);
			} else if(code[i] == ']') {
				LoopPoint ptr = tmpStack.pop();
				ptr.end = i;
				loopArr[ptr.start] = ptr;
				loopArr[ptr.end] = ptr;
			}
		}
		
		int cnt = 0;		
		int memptr = 0;
		int codeptr = 0;
		int inputptr = 0;
		int maxptr = 0;
		while(true) {
			char op = code[codeptr];
			boolean isLoop = false;
			switch(op) {
				//-	포인터가 가리키는 숫자를 1 감소시킨다. (modulo 28)
				case '-':
					mem[memptr] = (mem[memptr]-1+256)%256; 
					break;
				//+	포인터가 가리키는 숫자를 1 증가시킨다. (module 28)
				case '+':
					mem[memptr] = (mem[memptr]+1+256)%256; 
					break;
				//<	포인터를 왼쪽으로 움직인다.(1 감소)
				case '<':
					memptr = (memptr+m-1)%m;
					break;
				//>	포인터를 오른쪽으로 움직인다.(1 증가)
				case '>':
					memptr = (memptr+m+1)%m;
					break;
				//.	포인터가 가리키는 숫자를 출력한다.
				case '.':
					break;
				//,	문자 하나를 읽고 포인터가 가리키는 곳에 저장한다. 입력의 마지막(EOF)인 경우에는 255를 저장한다.
				case ',':
					mem[memptr] = inputptr >= i?255:input.charAt(inputptr);
					inputptr = inputptr >= i ? inputptr:inputptr+1;
					break;
				//[	만약 포인터가 가리키는 숫자가 0과 같다면, [와 짝을 이루는 ]로 점프한다.
				case '[':
					if(mem[memptr] == 0) {
						codeptr = loopArr[codeptr].end;
					}
					break;
				//]	만약 포인터가 가리키는 숫자가 0이 아니라면, ]와 짝을 이루는 [로 점프한다.
				case ']':
					if(mem[memptr] != 0) {
						codeptr = loopArr[codeptr].start;
					}
					break;
			}
			codeptr++;
			if(codeptr > maxptr) {
				maxptr = codeptr;
			}
			if(codeptr >= c) {
				return "Terminates";
			}
			if(cnt > 50000000) {
				return "Loops "+loopArr[maxptr].start+" "+loopArr[maxptr].end;
			}
			cnt++;
		}
	}
}
