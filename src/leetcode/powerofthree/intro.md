#### 문제를 읽고 이해하기
https://leetcode.com/problems/power-of-three/

input</br>
27
0
9
45


output</br>
true
false
true
false


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
숫자가 3의 거듭제곱수인지 확인

#### 계획 세우기<br>
1. 재귀로 풀음. 1인경우 true, 0또는 3으로 나눴을때 나머지가 있으면 false 반환
2. 2^31-1까지가 범위이므로, 3^19승이 최대임. 따라서 3^n은 3^19에 나눠져야함

#### 계획 검증하기
