package hackerrank.ds2darray;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
    	//모래시계의 인덱스 변화값
    	int[][] idxArr = {{0,0},
    			{-1,-1},{-1,0},{-1,1},{1,-1},{1,0},{1,1}};
    	
    	int ret = 0;
    	for(int y = 0; y < 6; y++) {
    		for(int x = 0; x < 6; x++) {
    			//음수가 나올수 있으므로 최대값을 적당히 큰 음수값으로 설정
    			int tmp = -987654321;
    			for(int[] idx : idxArr) {
    				int nextY = y+idx[0];
    				int nextX = x+idx[1];
    				//인덱스가 넘어가는 경우 -음수 반환
    				if(nextY < 0 || nextY > 5 || nextX < 0 || nextX > 5) {
    					tmp = -987654321;
    					break;
    				}
    				tmp += arr[nextY][nextX];
    			}
    			ret = Math.max(ret, tmp);
    		}
    	}
    	return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

