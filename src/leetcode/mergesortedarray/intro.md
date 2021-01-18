#### 문제를 읽고 이해하기
https://leetcode.com/problems/merge-sorted-array/

input</br>
nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
nums1 = [1], m = 1, nums2 = [], n = 0


output</br>
[1,2,2,3,5,6]
[1]


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
두개의 정렬된 배열을 합쳐서 정렬된 배열 반환

#### 계획 세우기<br>
1. nums1에 nums2를 복사하여 정렬함
2. 이미 정렬된 배열이므로, nums1과 nums2의 크기를 비교하여 큰 순서대로 넣음

#### 계획 검증하기
