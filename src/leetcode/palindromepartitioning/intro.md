#### 문제를 읽고 이해하기
https://leetcode.com/problems/palindrome-partitioning/

input</br>
s = "aab"
s = "a"


output</br>
[["a","a","b"],["aa","b"]]
[["a"]]


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
입력된 문자열의 팰린드롬의 부문문자열의 합 반환

#### 계획 세우기<br>
모든 부분문자열을 순회하면서 끝에 도달하면 답에 저장함

#### 계획 검증하기
