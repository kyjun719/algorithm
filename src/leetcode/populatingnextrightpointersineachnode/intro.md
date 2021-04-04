#### 문제를 읽고 이해하기
https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

input</br>
root = [1,2,3,4,5,6,7]


output</br>
[1,#,2,3,#,4,5,6,7,#]


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
같은 깊이의 노드를 이어갈때 값

#### 계획 세우기<br>
동일한 깊이의 노드들을 왼쪽부터 탐색하여 next값 지정함

#### 계획 검증하기
