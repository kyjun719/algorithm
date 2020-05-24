#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/making-candies/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search&h_r=next-challenge&h_v=zen

input</br>
3 1 2 12

1 1 6 45

5184889632 5184889632 20 10000


output</br>
3

16

1

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
매번 m*w개의 사탕을 만들때 n개를 가지는 최대 횟수

#### 계획 세우기<br>
현재m,w로 모았을때 걸리는 날짜와 계속해서 늘렸을 때 걸리는 날짜를 따로 계산함<br>
모았을때 걸리는 날짜가 더 작은경우 모았을때 걸리는 날짜 반환<br>
m,w를 구매하지 못하는경우 해당날짜만큼 넘어감, 이떄 사탕을 다 모을수 있는 경우 종료<br>
m과 w는 차이가 나지않게 증가해야함(예 : 1,1이고 2를 증가가능한 경우 2,2 > 1,3), m과 w를 구매한 후 다음 사탕갯수 계산, 날짜 증가<br>

#### 계획 검증하기
사탕을 계산할 때 범위가 넘어갈수 있으므로 BigInteger로 계산
