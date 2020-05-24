#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/minimum-time-required/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search&h_r=next-challenge&h_v=zen

input</br>
2 5
2 3

3 10
1 3 4

3 12
4 5 6


output</br>
6

7

20

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
n일마다 장난감을 생산할때 목표갯수까지 만드는데 걸리는 일수

#### 계획 세우기<br>
장난감 하나를 만드는데 최단시간의 기계로 구성되었을때 걸리는 일자를 최소값, 장난감 하나를 만드는데 최장시간의 기계로 구성되었을때 걸리는 일자를 최대값으로 하여 중간값으로 범위를 좁힘<br>

#### 계획 검증하기
