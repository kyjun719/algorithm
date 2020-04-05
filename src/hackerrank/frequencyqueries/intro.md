#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/frequency-queries/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps&h_r=next-challenge&h_v=zen


input</br>
8
1 5
1 6
3 2
1 10
1 10
1 6
2 5
3 2
6 3
1 3 9 9 27 81

4
3 4
2 1003
1 16
3 1

10
1 3
2 3
3 2
1 4
1 5
1 5
1 4
3 2
2 4
3 2


output</br>
0
1

0
1

0
1
1

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
숫자를 추가하고 삭제하였을 때 해당 갯수의 존재여부 반환

#### 계획 세우기<br>
숫자의 갯수를 저장하는 맵, 갯수의 갯수를 저장하는맵에 자료들 삽입<br>
- 쿼리가 1인 경우 : 숫자의 갯수 증가, 이전 갯수의 갯수 감소, 증가한 갯수의 갯수 증가
- 쿼리가 2인 경우 : 숫자의 갯수 감소, 이전 갯수의 갯수 감소, 감소한 갯수의 갯수 증가
- 쿼리가 3인 경우 : 해당갯수의 수가 0보다 큰지 반환

#### 계획 검증하기