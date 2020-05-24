#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/poisonous-plants/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues&h_r=next-challenge&h_v=zen

input</br>
7
4 3 7 5 6 4 2


output</br>
3

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
현재 숫자가 왼쪽 숫자보다 클때 사라지는경우, 사라지지 않을때 까지 반복 횟수

#### 계획 세우기<br>
현재 숫자가 이전숫자보다 작은경우 스택에 넣음, 아닐경우 새로운 스택을 만들어 숫자를 넣음<br>
첫번째 스택을 제외한 나머지 스택을 돌면서 맨 앞 숫자를 지우고, 이전 스택의 맨 뒤 숫자보다 작은경우 합침<br>
스택의 갯수가 하나가 될때 까지 반복<br>

#### 계획 검증하기
