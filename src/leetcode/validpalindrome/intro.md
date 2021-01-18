#### 문제를 읽고 이해하기
https://leetcode.com/problems/valid-palindrome/

input</br>
"A man, a plan, a canal: Panama"
"race a car"


output</br>
true
false


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
주어진 문자열이 대칭인지 반환

#### 계획 세우기<br>
1. 정규식으로 특수문자, 띄어쓰기를 제외한 후 대칭 확인<br>
2. 특수문자 또는 띄어쓰기일 경우 넘어가고, 아닐경우 문자열을 비교하여 값 반환<br>

#### 계획 검증하기
