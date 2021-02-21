#### 문제를 읽고 이해하기
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

input</br>
preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
preorder = [-1], inorder = [-1]


output</br>
[3,9,20,null,null,15,7]
[-1]


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
전위순회, 중위순회 결과값이 있을 때 트리구조 생성

#### 계획 세우기<br>
전위순회값의 맨 처음값이 루트값<br>
중위순회에서 루트값 기준 왼쪽은 왼쪽노드들, 오른쪽은 오른쪽노드들의 값들임.<br>
중위순회에서 왼쪽노드 길이를 구하여 전위순회에서 사용하고, 나머지는 오른쪽 노드값임

#### 계획 검증하기
 
