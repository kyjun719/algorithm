#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/largest-rectangle/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues

input</br>
5
1 2 3 4 5

5
11 11 10 10 10

output</br>
9

50
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
내부 직사각형의 최대값

#### 계획 세우기<br>
현재높이가 이전의 높이보다 높은경우 스택에 인덱스 넣고 다음 인덱스 확인, 아닐경우 높이가 낮아질떄 까지 스택에서 하나씩 꺼내면서 넓이 계산<br>
스택이 비어있는경우 스택에서 꺼낸 높이가 가장 낮으므로 너비는 탐색중인 인덱스-1까지<br>
스택에 있는 경우 너비는 현재값~스택의 인덱스값<br>  

#### 계획 검증하기
