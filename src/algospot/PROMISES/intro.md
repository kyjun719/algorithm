#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/PROMISES

input</br>
2<br>
4 2 2<br>
0 1 4<br>
0 3 1<br>
0 2 2<br>
1 2 6<br>
4 2 2<br>
0 1 4<br>
0 3 1<br>
1 2 6<br>
0 2 2<br>


output</br>
1<br>
0<br>
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
m개의 간선이 있는 상태에서 n개의 간선이 추가 될 때 필요없는 간선의 수

#### 계획 세우기<br>
m개의 간선을 가지고 모든 정점의 최단경로를 계산하고, n개의 간선 중 시작과 끝값이 현재보다 같거나 큰 경우 필요없는 간선으로 판단함.

#### 계획 검증하기
