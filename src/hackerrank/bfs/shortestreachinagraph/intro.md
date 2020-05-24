#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs&h_r=next-challenge&h_v=zen

input</br>
2
4 2
1 2
1 3
1
3 1
2 3
2


output</br>
6 6 -1
-1 6

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
간선 하나당 6의 가중치를 가질때 시작노드에서 탐색한 거리

#### 계획 세우기<br>
시작노드에서 거리를 6씩 더하여 너비우선 탐색, 시작점은 출력하지 않음

#### 계획 검증하기

