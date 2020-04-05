package algospot.FENCE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
	private static int[] fence;
	private static int n;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		int tc = Integer.parseInt(str);
		for(int i = 0; i < tc; i++) {
			n = Integer.parseInt(br.readLine());
			fence = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			
			//System.out.println(solve(0, n - 1));
			System.out.println(solve2());
		}
	}
	
	public static int solve(int left, int right) {
		if (left == right) {
			return fence[left];
		}
		
		int mid = (left + right) / 2;
		//중간값을 기준으로 왼쪽과 오른쪽중 최대값 계산
		int ret = Math.max(solve(left, mid), solve(mid + 1, right));
		
		int start = mid;
		int end = mid + 1;
		int height = Math.min(fence[start], fence[end]);
		//중간값과 바로 옆값으로 최대값 비교
		ret = Math.max(ret, height * 2);
		
		//비교 범위의 끝까지
		while((start > left) || (end < right)) {
			//왼쪽이 끝에 도달했거나 왼쪽값이 오른쪽값 보다 작은경우
			if((end < right) && ((start == left) || (fence[start - 1] < fence[end + 1]))) {
				end++;
				height = Math.min(height, fence[end]);
			} else {
				start--;
				height = Math.min(height, fence[start]);
			}
			ret = Math.max(ret, height * (end - start + 1));
		}
		
		return ret;
	}
	
	//h[i] < h[i+1] -> 스택에 저장 후 다음 높이와 비교
	//h[i] >= h[i+1] -> 넓이 계산 후 최대값 비교, 가장 작은 높이로 넓이 구함
	//오른쪽 위치는 i-1, 왼쪽 위치는 가장 작은 높이의 위치
	public static int solve2() {
		Stack<Integer> heightStack = new Stack<>();
		int ret = 0;
		int[] newFence = Arrays.copyOf(fence, n+1);
		//System.out.println(Arrays.toString(newFence));
		//순서대로 순회
		for(int i = 0; i < newFence.length; i++) {
			//저장된 위치값이 없거나 이전값의 높이가 더 낮을때까지
			while(!heightStack.isEmpty() && 
					newFence[heightStack.lastElement()] >= newFence[i]) {
				int j = heightStack.lastElement();
				heightStack.pop();
				int width = -1;
				if(heightStack.isEmpty()) {
					width = i;
				} else {
					width = i-heightStack.lastElement()-1;
				}
				ret = Math.max(ret, newFence[j]*width);
			}
			heightStack.push(i);
		}
		
		return ret;
	}
}