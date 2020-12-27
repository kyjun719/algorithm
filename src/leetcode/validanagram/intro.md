#### 문제를 읽고 이해하기
https://leetcode.com/problems/valid-anagram/

input</br>
s = "anagram", t = "nagaram"
s = "rat", t = "car"

output</br>
true
false


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
s와 t가 같은 문자들을 사용하였는지 확인

#### 계획 세우기<br>
하나의 맵에 저장해서 s의 문자열은 +1, t의 문자열은 -1을 하여 최종적으로 맵의 모든 값이 0이면 true, 아니면 false

#### 계획 검증하기
