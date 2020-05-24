#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/min-max-riddle/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues&h_r=next-challenge&h_v=zen

input</br>
4
2 6 1 12

7
1 2 3 5 1 13 3


output</br>
12 2 1 1

13 3 2 1 1 1 1

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
1~n까지 묶음중 최소값들의 최대값

#### 계획 세우기<br>
이전인덱스의 숫자가 현재 인덱스의 숫자보다 큰 경우 스택에 넣고 다음 인덱스 확인, 아닐경우 작아질때 까지 꺼내서 해당 숫자가 가질수 있는 최대 묶음 크기를 계산함<br>
스택이 비어있는경우 현재 인덱스 까지, 아닌경우 스택의 인덱스~현재인덱스-1이 최대 묶음크기임, 각 수의 최대 묶음 크기를 계산함<br>
배열의 처음부터 각 수의 최대 묶음 크기만큼 순회하면서 결과값과 비교하여 최대값을 넣음<br>

#### 계획 검증하기
