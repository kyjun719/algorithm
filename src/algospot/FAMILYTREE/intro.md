#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/FAMILYTREE

input</br>
1
13 5
0 1 1 3 3 0 6 0 8 9 9 8
2 7
10 11
4 11
7 7
10 0


output</br>
4
2
6
0
3
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
각 노드간 거쳐야되는 노드의 수

#### 계획 세우기<br>
각 노드간의 부모와 자식관계를 저장한 후 루트로부터 전체 순회를 함<br>
노드 순번과 방문하는 순서가 틀리므로 해당 수 들이 연결될수 있도록 배열에 따로 저장함<br>
a노드가 순회에서 시작하는값~b노드가 순회에서 시작하는값의 구간이 a노드에서 b노드간에 순회 과정임<br>
시리얼값은 방문 순서대로 작으므로, 해당 구간에서 가장 작은값에 매칭되는 노드값이 두 노드간의 가장 높이 있는 부모<br>
두 노드간 거리 = a노드의 깊이+b노드의 깊이-2*(a,b노드간 가장 높이있는 공통 부모)<br>

#### 계획 검증하기