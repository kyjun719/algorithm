#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/triple-sum/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen

input</br>
3 2 3
1 3 5
2 3
1 2 3

3 3 3
1 4 5
2 3 3
1 2 3

4 3 4
1 3 5 7
5 7 9
7 9 11 13


output</br>
8

5

12

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
두번째 배열의 원소보다 작은 a,c의 배열의 원소 집합의 갯수

#### 계획 세우기<br>
모든 배열 정렬후 b의 배열값을 기준으로 a,c의 갯수 계산, 곱한후 b의 다음 배열값으로 계산<br>
같은숫자는 하나로 치므로 각 배열에 중복되는 원소가 없도록 해야함<br>

#### 계획 검증하기
