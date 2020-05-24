#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/find-the-nearest-clone/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs

input</br>
4 3
1 2
1 3
4 2
1 2 1 1 
1

4 3
1 2
1 3
4 2
1 2 3 4
2

5 4
1 2
1 3
2 4
3 5
1 2 3 3 2
2


output</br>
1

-1

3
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
해당하는 색깔의 노드간 최단 연결거리

#### 계획 세우기<br>
해당값에 해당하는 노드부터 너비우선탐색 시작, 다음 노드로 건너갈 때 지나온 간선 제거하면서 진행함, 다음 노드를 간적이 있는 경우 다음노드의 값+현재노드의 값+1 반환<br>

#### 계획 검증하기

