package kakao.test2019;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @see https://programmers.co.kr/learn/courses/30/lessons/42891
 * @author jun

3, 1, 2
5

1

1,2,3
5

3

3,1,2
7

-1

3,1,1,1,2,4,3
12

6

 */
public class FoodLive {
	///*
	public static void main(String[] args) {
		try {
			/*
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] food_times = Arrays.stream(br.readLine().replace(" ", "").split(","))
					.mapToInt(Integer::parseInt)
					.toArray();
			int k = Integer.parseInt(br.readLine());
			*/
			while(true) {
				Random rand = new Random(); 
				int n = rand.nextInt(30);
				int[] food_times = new int[n];
				for(int i = 0; i < n; i++) {
					food_times[i] = rand.nextInt(1000);
				}
				int k = rand.nextInt(n*1000);
				System.out.println(k+","+Arrays.toString(food_times));
				int ret1 = new FoodLive().solution(food_times.clone(), k);
				int ret2 = new FoodLive().test(food_times.clone(), k);
				if(ret1 != ret2) {
					System.out.println(ret1);
					System.out.println(ret2);
					break;
				}
				System.out.println("----------------------------");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int test(int[] food_times, long k) {
		long total_sum = 0;
		for(int i = 0; i < food_times.length; i++) {
			total_sum += food_times[i];
		}
		System.out.println("in test total::"+total_sum);
		if(total_sum < k) {
			return -1;
		}
		int num = 0;
		int n = food_times.length;
		while(true) {
			if(food_times[num] > 0) {
				if(k == 0) {
					break;
				} else {
					food_times[num]--;
					k--;
				}
			}

			num = (num+1)%n;
		}
		System.out.println(Arrays.toString(food_times));
		return num+1;
	}
	//*/
	private class Food {
		int id;
		long remainTime;
		Food(int id, long remainTime) {
			this.id = id;
			this.remainTime = remainTime;
		}
	}
	
	public int solution(int[] food_times, long k) {
		int n = food_times.length;
		List<Food> foodList = new ArrayList<>();
		for(int i = 0; i < n; i++ ) {
			foodList.add(new Food(i+1, food_times[i]));
		}
		
		while(!foodList.isEmpty()) {
			int remainFoodSize = foodList.size();
			int eachEatTime = (int)(k/remainFoodSize);
			k = k % remainFoodSize;
			if(eachEatTime == 0) {
				return foodList.get((int) k).id;
			}
			
			k += eatFood(foodList, eachEatTime);
		}
		
		
		return -1;
    }

	private long eatFood(List<Food> foodList, int eachEatTime) {
		long overflow = 0;
		for(Food food : foodList) {
			food.remainTime -= eachEatTime;
			if(food.remainTime < 0) {
				overflow += -food.remainTime;
			}
		}
		foodList.removeIf(food -> food.remainTime <= 0);
		
		return overflow;
	}
}
