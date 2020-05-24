#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/castle-on-the-grid/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues

input</br>
3
.X.
.X.
...
0 0 0 2

output</br>
3
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
시작점에서부터 끝점까지 가는데 걸리는 최소 이동횟수

#### 계획 세우기<br>
이동이 가능한 경우 동서남북으로 한칸씩 이동하면서 걸리는 최소횟수 계산<br>
뱡향이 바뀌지 않으면 한번에 이동 가능하므로 한쪽 방향으로 이동하지 못할때 까지 한칸씩 이동함<br> 

#### 계획 검증하기
