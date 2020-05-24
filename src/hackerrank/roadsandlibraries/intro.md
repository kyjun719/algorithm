#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/torque-and-development/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs

input</br>
2
3 3 2 1
1 2
3 1
2 3
6 6 2 5
1 3
3 4
2 4
1 2
2 3
5 6


output</br>
4
12
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
도로와 정점 비용이 주어질때 정점을 기준으로 연결되는 최소 금액

#### 계획 세우기<br>
깊이우선 탐색으로 각 노드에서 연결될수 있는 노드들 검색, 도로비용이 도서관비용보다 비싼경우 각 노드에 도서관 건설<br>
아닐경우 각 묶음의 도로수-1*도로비용+묶음수*정점 비용 반환<br>

#### 계획 검증하기
