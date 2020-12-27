#### 문제를 읽고 이해하기
https://leetcode.com/problems/excel-sheet-column-number/

input</br>
A
AB


output</br>
1
28


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
A-Z가 26, AA가 27일때 입력문자열에 대한 숫자

#### 계획 세우기<br>
26진수와 비슷하게 생각하고, 각 자리수를 넘어갈 때 마다 이전수에26을 곱해주면됨

#### 계획 검증하기
