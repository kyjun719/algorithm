#### 문제를 읽고 이해하기
https://leetcode.com/problems/kth-smallest-element-in-a-bst/

input</br>
root = [3,1,4,null,2], k = 1
root = [5,3,6,2,4,null,null,1], k = 3


output</br>
1
3

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
이진트리에서 k번째로 작은 원소 찾기

#### 계획 세우기<br>
왼쪽 노드를 루트로 하는 트리의 숫자 갯수를 확인함<br>
왼쪽노드의 갯수가 k-1이면 현재 노드가 답이고, 해당값보다 작은경우 왼쪽노드 안에 k번째 숫자가 있고, 큰 경우 오른쪽 노드에 있음<br>

#### 계획 검증하기
