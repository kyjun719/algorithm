#### 문제를 읽고 이해하기
https://leetcode.com/problems/group-anagrams/

input</br>
strs = ["eat","tea","tan","ate","nat","bat"]


output</br>
[["bat"],["nat","tan"],["ate","eat","tea"]]


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
같은 알파벳을 사용하는 문자열끼리 묶은 배열 반환

#### 계획 세우기<br>
각 문자열을 정렬하여 해시값 또는 문자열을 키로 사용하여 맵에 넣음<br>

#### 계획 검증하기
