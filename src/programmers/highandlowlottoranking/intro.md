#### 문제를 읽고 이해하기
https://programmers.co.kr/learn/courses/30/lessons/77484

input</br>
[44, 1, 0, 0, 31, 25]	[31, 10, 45, 1, 6, 19]
[0, 0, 0, 0, 0, 0]	[38, 19, 20, 40, 15, 25]
[45, 4, 35, 20, 3, 9]	[20, 9, 3, 45, 4, 35]

output</br>
[3, 5]
[1, 6]
[1, 1]

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
최대로 일치하는 숫자의 갯수와 최소로 일치하는 숫자의 갯수

#### 계획 세우기<br>
입력된 수 중 0을 제외하고 일치하는 숫자의 갯수 계산

0의갯수+일치하는 수가 최대로 일치하는 갯수이고, 계산한 일치하는수가 최소로 일치하는 갯수임

#### 계획 검증하기