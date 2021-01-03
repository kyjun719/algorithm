#### 문제를 읽고 이해하기
https://leetcode.com/problems/intersection-of-two-arrays-ii/

input</br>
nums1 = [1,2,2,1], nums2 = [2,2]
nums1 = [4,9,5], nums2 = [9,4,9,8,4]


output</br>
[2,2]
[4,9]


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
2개의 배열중 겹치는 수 반환

#### 계획 세우기<br>
nums1의 숫자를 map에 넣고, nums2를 순회하면서 맵에 값이 있으면 답에 넣음

#### 계획 검증하기 
