#### 문제를 읽고 이해하기
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

input</br>
root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4

output</br>
5


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
p,q의 최소 공통 부모

#### 계획 세우기<br>
1. 루트부터 탐색을 통해 p와 q가 처음 등장하는 위치를 탐색함. 이후 p와 q사이에 가장 낮은 노드 탐색
2. 모든 노드를 탐색하면서, 해당 노드가 p와 q의 공통부모가 되는지 확인함

#### 계획 검증하기
