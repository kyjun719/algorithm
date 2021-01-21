#### 문제를 읽고 이해하기
https://leetcode.com/problems/count-primes/

input</br>
10
0
1


output</br>
4
0
0


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
n보다 작은 소수의 갯수 판별

#### 계획 세우기<br>
sqrt(n)까지 순회하면서 해당수를 방문한적이 없으면 해당수의 배수들을 방문함

#### 계획 검증하기 
