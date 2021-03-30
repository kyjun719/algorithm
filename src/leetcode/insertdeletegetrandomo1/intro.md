#### 문제를 읽고 이해하기
https://leetcode.com/problems/insert-delete-getrandom-o1/

input</br>
["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
[[], [1], [2], [2], [], [1], [2], []]


output</br>
[null, true, false, true, 2, true, false, 2]


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
삽입, 삭제, 랜덤의 기능에 따라 출력하기

#### 계획 세우기<br>
삽입 : map에 해당값이 없는 경우 list에 넣고, map에는 값과 인덱스값을 넣음
삭제 : map에 해당값이 있는 경우 map에서 인덱스를 가져오고, 리스트의 맨 마지막과 바꾼 다음 맨 마지막 항목 삭제
랜덤 : 리스트의 랜덤 인덱스의 값 반환

#### 계획 검증하기
