#### 문제를 읽고 이해하기
https://programmers.co.kr/learn/courses/30/lessons/12900

input</br>
4

output</br>
5
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
2xN을 채울수 있는 가짓수 계산

#### 계획 세우기<br>
2x2 = 2x1을 채우는 가짓수*2 + 2x2를 채우는 가짓수 = 2<br>
2x3 = 2x1을 채우는 가짓수 + 2x1을 채우는 가짓수 = 3<br>
...<br>
2xn = 2x(n-1) + 2x(n-2)<br>

#### 계획 검증하기
