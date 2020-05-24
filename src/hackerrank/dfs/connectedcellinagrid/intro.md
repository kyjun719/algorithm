#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen

input</br>
4
4
1 1 0 0
0 1 1 0
0 0 1 0
1 0 0 0


5
4
0 0 1 1
0 0 1 0
0 1 1 0
0 1 0 0
1 1 0 0


output</br>
5

8
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
8방향을 기준으로 연결될 떄 1로 연결된 가장 넓은 넓이

#### 계획 세우기<br>
각 점을 돌면서 1이고 순회하지 않음 점을 시작으로 깊이우선탐색 시작, 현재 노드에서 연결된 갯수 반환

#### 계획 검증하기

