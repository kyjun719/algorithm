#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/candies/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming&h_r=next-challenge&h_v=zen

input</br>
3
1
2
2

10
2
4
2
6
1
7
8
9
2
1

8
2
4
3
5
2
6
4
5

output</br>
4

19

12
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
이웃한 배열값이 큰경우 무조건 사탕을 더 받아야 할때 최소 전체 사탕의 갯수

#### 계획 세우기<br>
왼쪽에서 오른쪽으로 순회하면서 오른쪽이 큰경우 +1, 아닐경우 1로 설정<br>
오른쪽에서 왼쪽으로 순회하면서 왼쪽이 크고 사탕의 갯수가 작은경우 오른쪽의 +1, 왼쪽이 작지만 배열값이 같거나 이미 작은경우를 제외하고 1로 설정<br>

#### 계획 검증하기
