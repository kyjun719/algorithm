#### 문제를 읽고 이해하기
https://leetcode.com/problems/contains-duplicate/

input</br>
[1,2,3,1]
[1,2,3,4]


output</br>
true
false


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
배열내 원소들이 중복되어있는지 확인후 중복된것이 있으면 true, 아니면 false반환

#### 계획 세우기<br>
1. 배열내 모든 원소들의 갯수를 세서 1이 아닌 값이 있는경우 false 반환
2. 배열을 set으로 변경해 길이 비교하여 다르면 true 반환 

#### 계획 검증하기
