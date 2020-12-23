#### 문제를 읽고 이해하기
https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

input</br>
[-10,-3,0,5,9]


output</br>
[0,-3,9,-10,null,5]


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
정렬된수가 주어졌을 때 균형잡힌 이진트리 생성

#### 계획 세우기<br>
정렬된 배열의 가운데 값이 현재 노드의 값이고, 0과 중간값-1이 현재 노드의 왼쪽, 중간값+1과 size가 현재노드의 오른쪽으로 들어감<br>
재귀로 풀음

#### 계획 검증하기
