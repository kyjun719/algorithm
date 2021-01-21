#### 문제를 읽고 이해하기
https://leetcode.com/problems/sqrtx/

input</br>
4
8


output</br>
2
2


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
n의 제곱근값, 버림을 취함

#### 계획 세우기<br>
1. x의 sqrt값을 int로 변환하여 반환
2. 이분법을 사용하여 나눈값이 중간값보다 크면 값을 올리고, 아니면 낮춰서 mid*mid=n이 되도록함

#### 계획 검증하기 
