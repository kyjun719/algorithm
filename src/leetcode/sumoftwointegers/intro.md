#### 문제를 읽고 이해하기
https://leetcode.com/problems/sum-of-two-integers/

input</br>
a = 1, b = 2
a = -2, b = 3


output</br>
3
1


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
+,-없이 덧셈,뺄셈구현

#### 계획 세우기<br>
xor을 통해 두수의 합을 구하고, and를 통해 올림수가 있는지 확인한 후 시프트 연산으로 한자리 이동함
올림수가 0이면 종료

#### 계획 검증하기
https://kplog.tistory.com/m/274
https://smlee729.wordpress.com/2018/03/14/algorithm-%EB%AC%B8%EC%A0%9C-%EB%8D%A7%EC%85%88%ED%95%98%EC%A7%80-%EC%95%8A%EA%B3%A0-%EB%8D%A7%EC%85%88%ED%95%98%EA%B8%B0/
