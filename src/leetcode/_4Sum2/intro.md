#### 문제를 읽고 이해하기
https://leetcode.com/problems/4sum-ii/

input</br>
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]


output</br>
2


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
숫자들의 합이 0인 조합수 계산

#### 계획 세우기<br>
c,d의 합을 map의 키로 저장하고, 합의 갯수를 값으로 저장함. a,b를 더한 값의 음수가 키로 있는경우 해당수를 답에 더함

#### 계획 검증하기

 
