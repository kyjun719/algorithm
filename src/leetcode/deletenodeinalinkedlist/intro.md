#### 문제를 읽고 이해하기
https://leetcode.com/problems/delete-node-in-a-linked-list/

input</br>
head = [4,5,1,9], node = 5
head = [4,5,1,9], node = 1
head = [0,1], node = 0

output</br>
[4,1,9]
[4,5,9]
[1]

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
링크드리스트에서 특정 노드를 뺀 리스트

#### 계획 세우기<br>
현재 노드를 지워야 하므로 현재 노드의 값을 다음 노드의 값으로 바꾸고, 현재 노드의 다음노드를 다음노드의 다음노드로 바꿈 

#### 계획 검증하기
