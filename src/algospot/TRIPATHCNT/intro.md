#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/TRIPATHCNT

input</br>
2
4
1
1 1 
1 1 1 
1 1 1 1 
4
9
5 7
1 3 2
3 5 5 6


output</br>
8
3


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
합이 최대인 경로의 수 구하기

#### 계획 세우기<br>
바로아래로 가는 합과 오른쪽 아래로 가는 합을 비교하여 같으면 둘다, 아니면 큰쪽으로 진행<br>
합을 구하는 메소드에서는 끝까지 도달한경우 해당위치의 배열값 반환<br>
갯수를 구하는 메소드에서는 끝까지 도달한경우 1 반환<br>

#### 계획 검증하기
최대값 저장하여 시간단축
