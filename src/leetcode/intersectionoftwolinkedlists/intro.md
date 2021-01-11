#### 문제를 읽고 이해하기
https://leetcode.com/problems/intersection-of-two-linked-lists/

input</br>
listA = [4,1,8,4,5], listB = [5,6,1,8,4,5]
listA = [2,6,4], listB = [1,5]


output</br>
Reference of the node with value = 8
null


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
두개의 배열중 교차점 노드 반환

#### 계획 세우기<br>
1. 두 리스트의 모든노드를 돌면서 노드가 일치하는지 확인
2. a또는 b를 순회하면서 set에 저장하고, 한 리스트를 순회하면서 set에 들어가있는지 확인
3. a와 b의 처음부터 시작해서, 끝나면 다른 리스트의 맨처음부터 순회함. 이 때 두개의 노드가 일치하면, 해당 노드부터 교차점의 시작임

#### 계획 검증하기
```
A: a1 -> a2 -> a3 -> c1 -> c2
B: b1 -> b2 -> c1 -> c2


while 문으로 비교
a1 -> a2 -> a3 -> c1 -> c2 -> b1 -> b2 -> c1 -> c2 -> null
b1 -> b2 -> c1 -> c2 -> a1 -> a2 -> a3 -> c1 -> c2 -> null
```