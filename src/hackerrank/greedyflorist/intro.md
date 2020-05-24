#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/greedy-florist/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms&h_r=next-challenge&h_v=zen

input</br>
3 3
2 5 6

5 3
1 3 5 7 9


output</br>
13

29
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
i번째에 k개씩 살수 있을때 i*n개중 k개 합의 최소 

#### 계획 세우기<br>
큰 순서대로 정렬, i는 1부터 시작, i%k == 0이면 배수 증가, 배수*수의 누적합

#### 계획 검증하기
