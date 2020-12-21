#### 문제를 읽고 이해하기
https://leetcode.com/problems/maximum-depth-of-binary-tree/

input</br>
root = [3,9,20,null,null,15,7]
root = [1,null,2]
root = []

output</br>
3
2
0

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
이진트리의 루트가 주어졌을 때 해당 트리의 최대 깊이

#### 계획 세우기<br>
재귀로 오른쪽과 왼쪽 중 가장 큰값+1을 반환함<br>
null일경우 0반환

#### 계획 검증하기
