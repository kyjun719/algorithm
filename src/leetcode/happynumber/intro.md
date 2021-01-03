#### 문제를 읽고 이해하기
https://leetcode.com/problems/happy-number/

input</br>
n = 19
n = 2

output</br>
true
false


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
각 자리의 수를 제곱해서 더할때 1이 되는지 여부

#### 계획 세우기<br>
1이 나올떄까지 반복하면서 해당 수가 이전에 나온적이 있는경우 false 반환

#### 계획 검증하기
Cycle 을 이룰 경우 slow 와 fast 의 변수가 언젠가 같아지는 순간이 옴
 
