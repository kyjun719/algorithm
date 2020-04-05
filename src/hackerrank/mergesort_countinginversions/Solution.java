package hackerrank.mergesort_countinginversions;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the countInversions function below.
    static long countInversions(int[] arr) {
    	cnt = solve(arr, 0, arr.length-1);
    	return cnt;
    }
    
    static long cnt;
    
    static long solve(int[] arr, int left, int right) {
		if(left == right) {
			//구간이 1이므로 반전이 일어나지 않음
			return 0;
		}
		/* 1. 분할 */
		int mid = (left+right)/2;
		//왼쪽과 오른쪽을 나누어서 정렬, 이때 일어난 반전되는 수의 합 계산
		long ret = solve(arr,left,mid) + solve(arr,mid+1,right);
		
		/* 2. 정복 */
		int[] tmp = new int[right-left+1];
		//tmpIdx : 병합한 배열의 인덱스
		//leftIdx : 기존 배열의 왼쪽 구간 인덱스(left~mid)
		//rightIdx : 기존 배열의 오른쪽 구간 인덱스(mid+1~right)
		int tmpIdx = 0, leftIdx=left, rightIdx=mid+1;
		//왼쪽 또는 오른쪽을 다 탐색할때 까지, 더 큰수가 왼쪽에, 더 작은수가 오른쪽에 포함되어 있는 경우
		while(leftIdx <= mid || rightIdx <= right) {
			//왼쪽구간 인덱스가 중간값 보다 작고 오른쪽을 다 탐색했거나 왼쪽값이 오른쪽값보다 작은 경우
			if(leftIdx <= mid && (rightIdx > right || arr[leftIdx] <= arr[rightIdx])) {
				//왼쪽의 수가 오른쪽의 수 보다 작은경우
				//병합한 배열에 왼쪽수 추가
				tmp[tmpIdx++] = arr[leftIdx++];
			} else {
				//오른쪽의 수가 왼쪽의 수 보다 작으므로 왼쪽에 남은 수 만큼 반전이 일어남
				ret += mid-leftIdx+1;
				//병합한 배열에 오른쪽수 추가
				tmp[tmpIdx++] = arr[rightIdx++];
			}
		}
		/* 3. 결합 */
		//tmp에 합친 결과를 arr로 복사함
		for(int i = 0; i < tmp.length; i++) {
			arr[left+i] = tmp[i];
		}
		return ret;
	}

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            long result = countInversions(arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

