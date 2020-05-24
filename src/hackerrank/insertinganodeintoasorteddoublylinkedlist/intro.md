#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/insert-a-node-into-a-sorted-doubly-linked-list/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=linked-lists

input</br>
1
4
1
3
4
10
5


output</br>
1 3 4 5 10

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
연결리스트에서 입력값을 정렬된 위치에 넣었을 때 값들 출력

#### 계획 세우기<br>
해당위치가 나올떄까지 하나씩 넘어가고 해당위치에 연결리스트 객체 하나를 생성하여 연결함<br>
현재 노드값이 삽입값보다 큰경우 해당 노드앞에 삽입, 현재 노드의 다음이 null인 경우 현재노드 뒤에 삽입<br>
반환시 맨앞으로 이동하여 노드 반환

#### 계획 검증하기
