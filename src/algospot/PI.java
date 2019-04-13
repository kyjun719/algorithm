package algospot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PI {
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			int c = Integer.parseInt(reader.readLine().replace(" ", ""));
			for(int tc = 0; tc < c; tc++) {
				String input = reader.readLine().replace(" ", "");
				int[] arr = new int[input.length() + 1];
				int[] val = new int[input.length() + 1];
				Arrays.fill(val, Integer.MAX_VALUE);
				arr[0] = 0;
				val[0] = 0;
				for(int i = 0; i < input.length(); i++) {
					arr[i + 1] = input.charAt(i) - 48;
				}
				List<Integer> tmp_list = new ArrayList<>();
				tmp_list.add(0);
				while(!tmp_list.isEmpty()) {
					List<Integer> next_tmp_list = new ArrayList<>();
					for(int tmp : tmp_list) {
						for(int j = 3; j < 6; j++) {
							if(tmp + j >= arr.length) {
								continue;
							}
							val[tmp + j] = Math.min(classify(Arrays.copyOfRange(arr, tmp+1, tmp + j+1))
									+ val[tmp], val[tmp + j]);
							next_tmp_list.add(tmp + j);
						}
					}
					tmp_list.clear();
					tmp_list.addAll(new HashSet<Integer>(next_tmp_list));
				}
				
				System.out.println(val[val.length - 1]);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static int classify(int[] tmp_arr) {
		boolean is_same = true;
		for(int tmp : tmp_arr) {
			 if(tmp != tmp_arr[0]) {
				 is_same = false;
			 }
		}
		
		if(is_same) {
			return 1;
		}

		boolean is_positive = true;
		boolean is_temp = true;
		int pos_delta = tmp_arr[1] - tmp_arr[0];
		int temp_delta = Math.abs(tmp_arr[1] - tmp_arr[0]);
		for(int idx = 1; idx < tmp_arr.length; idx++) {
			if(pos_delta != (tmp_arr[idx] - tmp_arr[idx - 1])) {
				is_positive = false;
			}
			
			if(temp_delta != Math.abs(tmp_arr[idx] - tmp_arr[idx - 1])) {
		    	is_temp = false;
		    }
		}
		    
		
		if(is_positive) {
			if(pos_delta == 1) {
				return 2;
			} else {
				return 5;
			}
		}
		    
		
		if(is_temp) {
			return 4;
		}
		
		return 10;
	}
/*
 * """
https://algospot.com/judge/problem/read/PI
5
12341234
11111222
12122222
22222222
12673939
"""

import sys


def main():
    

def classify(tmp_arr):
    is_same = True
    for tmp in tmp_arr:
        if tmp != tmp_arr[0]:
            is_same = False
    if is_same:
        return 1

    is_positive = True
    is_temp = True
    pos_delta = tmp_arr[1] - tmp_arr[0]
    temp_delta = abs(tmp_arr[1] - tmp_arr[0])
    for idx in range(1, len(tmp_arr)):
        if pos_delta != (tmp_arr[idx] - tmp_arr[idx - 1]):
            is_positive = False
        if temp_delta != abs(tmp_arr[idx] - tmp_arr[idx - 1]):
            is_temp = False

    if is_positive:
        if pos_delta == 1:
            return 2
        else:
            return 5

    if is_temp:
        return 4

    return 10


def pi(pos):
    if pos == len(val):
        return 0

    if pos > len(val):
        return sys.maxsize

    if val[pos] != -1:
        return val[pos]

    ret = sys.maxsize

    for i in range(3, 6):
        ret = min(ret, pi(pos + i) + classify(arr[pos:pos+i]))

    val[pos] = ret
    return ret


main()

 */
}
