#### 문제를 읽고 이해하기
https://leetcode.com/problems/maximum-subarray/

input</br>
[-2,1,-3,4,-1,2,1,-5,4]
[-2,-1]
[-2,1]

output</br>
6
-1
1


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
배열의 부분합 중 최대값

#### 계획 세우기<br>
현재값과 지금까지의 합과 현재값을 더한값을 비교하여, 현재값이 더 큰경우 구간 초기화<br>
아닐경우 구간합을 이어나감<br>

#### 계획 검증하기
 