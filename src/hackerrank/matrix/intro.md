#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/matrix/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs

input</br>
5 3
2 1 8
1 0 5
2 4 5
1 3 4
2
4
0


output</br>
10

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
기계끼리 연결되지 않도록 간선을 끊을때 최소값 

#### 계획 세우기<br>
0번정점부터 시작해서 깊이우선 탐색, 해당 정점에서 끊어야할 간선의 최소값 반환<br>
해당 정점이 기계인 경우 해당 정점에서 연결된 모든 간선값을 답에 더함, 이전 정점과의 간선값 반환<br>
해당 정점이 기계가 아닌경우 해당 정점과 연결된 간선중 최대값을 제외하고 답에 더함, 이전 정점과의 간선값과 나머지 간선값중 최소값 반환<br>

#### 계획 검증하기
