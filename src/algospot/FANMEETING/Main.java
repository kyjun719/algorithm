package algospot.FANMEETING;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @see https://algospot.com/judge/problem/read/FANMEETING
 * @author jun
 * input
4
FFFMMM
MMMFFF
FFFFF
FFFFFFFFFF
FFFFM
FFFFFMMMMF
MFMFMFFFMMMFMF
MMFFFFFMFFFMFFFFFFMFFFMFFFFMFMMFFFFFFF

 * output
1
6
2
2
 */
public class Main {
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			int tc = Integer.parseInt(reader.readLine());
			for(int t = 0;  t < tc; t++) {
				char[] member = reader.readLine().toCharArray();
				char[] fan = reader.readLine().toCharArray();
				int[] int_members = new int[member.length];
				int[] int_fans = new int[fan.length];
				
				for(int i = 0; i < fan.length; i++) {
					if(i < member.length) {
						int_members[i] = member[i] == 'F' ? 0 : 1;
					}
					int_fans[fan.length-i-1] = fan[i] == 'F' ? 0 : 1;
				}
				
				int[] ret = karatsuba(int_members, int_fans);
				int cnt = 0;
				for(int j = int_members.length - 1; j < int_fans.length; j++) {
					if(ret[j] == 0) {
						cnt++;
					}
				}
				System.out.println(cnt);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int[] karatsuba(int[] a, int[] b) {
		if(a.length < b.length) {
			return karatsuba(b, a);
		}

		if(a.length == 0 || b.length == 0) {
			return new int[0];
		}
		
		if(a.length == 1 && b.length == 1) {
			int[] tmp = {a[0] * b[0]};
			return tmp;
		}
		
		if(a.length <= 50) {
			return multiply(a,b);
		}
		
		int half = a.length/2;
		int[] a0 = Arrays.copyOfRange(a, 0, half);
		int[] a1 = Arrays.copyOfRange(a, half, a.length);
		
		int[] b0, b1;
		if(half > b.length) {
			b0 = b;
			b1 = new int[0];
		} else {
			b0 = Arrays.copyOfRange(b, 0, half);
			b1 = Arrays.copyOfRange(b, half, b.length);
		}
		
		int[] z0 = karatsuba(a0, b0);
		int[] z2 = karatsuba(a1, b1);
		a0 = add_to(a0, a1, 0);
		b0 = add_to(b0, b1, 0);
		int[] z1 = karatsuba(a0, b0);
		z1 = sub_from(z1, z0);
		z1 = sub_from(z1, z2);

		int[] ret = z0;
		ret = add_to(ret, z1, half);
		ret = add_to(ret, z2, half * 2);
		return ret;
	}
	
	static int[] multiply(int[] a, int[] b) {
		int[] c = new int[a.length+b.length+1];
		
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < b.length; j++) {
				c[i+j] += a[i]*b[j];
			}
		}
		
		return c;
	}
	
	static int[] add_to(int[] a, int[] b, int ptr) {
		if(a.length < (b.length + ptr)) {
			a = Arrays.copyOf(a, b.length + ptr);
		}
		
		for(int i = 0; i < b.length; i++) {
			a[i + ptr] += b[i];
		}
		
		return a;
	}
	
	static String arrayToString(int[] arr) {
		String ret = "[";
		for(int i = 0; i < arr.length; i++) {
			ret += String.valueOf(arr[i]);
			ret += ", ";
		}
		if(ret.length() > 2) {
			ret = ret.substring(0, ret.length() - 2);
		}
		ret += "]";
		
		return ret;
	}
	
	static int[] sub_from(int[] a, int[] b) {
		for(int i = 0; i < b.length; i++) {
			a[i] -= b[i];
		}
		
		return a;
	}
}
