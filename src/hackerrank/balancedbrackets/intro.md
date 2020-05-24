#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/balanced-brackets/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues

input</br>
3
{[()]}
{[(])}
{{[[(())]]}}


output</br>
YES
NO
YES

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
괄호의 대칭 여부 반환

#### 계획 세우기<br>
여는 괄호일 경우 스택에 저장, 닫는 괄호일 경우 스택의 맨 위의 괄호가 동일한 여는 괄호인지 확인후 아닐경우 종료

#### 계획 검증하기
