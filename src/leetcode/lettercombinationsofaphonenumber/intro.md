#### 문제를 읽고 이해하기
https://leetcode.com/problems/letter-combinations-of-a-phone-number/

input</br>
digits = "23"

output</br>
["ad","ae","af","bd","be","bf","cd","ce","cf"]


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
해당 숫자를 눌러 만들 수 있는 글자 조합 구하기

#### 계획 세우기<br>
자릿수 마다 누를 수 있는 글자들을 저장하여 입력숫자 길이만큼 반복하면서 조합들을 구함

#### 계획 검증하기
