#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/11577

input</br>
1 1
5 5
7 3

1 1
3 3
5 5

1 1
7 3
5 5

output</br>
-1

0

1

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
첫번째 점과 두번째점을 이은 선분이 있을 때 마지막점이 해당선분의 왼쪽일경우 반시계, 오른쪽일 경우 시계 

#### 계획 세우기<br>
첫번째점과 두번째점을 이은 벡터와 두번째점과 세번째점을 이은 벡터를 외적하였을 떄 반시계면 양수, 시계면 음수, 평행이면 0

#### 계획 검증하기
