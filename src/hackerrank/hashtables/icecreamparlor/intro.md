#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search

input</br>
2
4
5
1 4 5 3 2
4
4
2 2 4 3


output</br>
1 4
1 2

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
두개의 숫자를 더해 금액이 될때 가장 작은 인덱스값들 출력   

#### 계획 세우기<br>
left를 hashMap으로 설정<br>
left의 (money-cost[i]) == null인경우 idx를 넣음<br>
left의 (cost[i]) != null인경우 답 출력<br> 

#### 계획 검증하기
