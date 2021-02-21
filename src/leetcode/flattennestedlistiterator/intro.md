#### 문제를 읽고 이해하기
https://leetcode.com/problems/flatten-nested-list-iterator/

input</br>
[[1,1],2,[1,1]]
[1,[4,[6]]]


output</br>
Output: [1,1,2,1,1]
Output: [1,4,6]


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
입력받은 배열을 풀은 객체 생성 및 아이템 반환

#### 계획 세우기<br>
생성자에서 입력받은 리스트를 1차원 리스트로 변환한 후 next, hasNext를 맞게 구현함

#### 계획 검증하기
 
