#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/maximum-subarray-sum/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search&h_r=next-challenge&h_v=zen

input</br>
1
5 7
3 3 9 9 5


output</br>
6

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
부분합의 나머지로 나눈값의 최대값

#### 계획 세우기<br>
누적합의 나머지를 구한 후 정렬. i~j까지의 부분합의 나머지 = (j까지의 부분합의 나머지-i까지의 부분합의 나머지)의 나머지<br>
따라서 i<j이고 i까지의 누적합>j까지의 누적합일떄 m-두 누적합간의 차의 최대값을 구함

#### 계획 검증하기
