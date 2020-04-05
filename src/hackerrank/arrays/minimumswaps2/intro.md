#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/minimum-swaps-2/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays

input</br>
4
4 3 1 2

5
2 3 4 1 5

7
1 3 5 2 4 6 7

output</br>
3

3

3
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
최소한의 움직임으로 정렬할 때 이동 횟수

#### 계획 세우기<br>
arr[i] != i+1인 경우 최소 하나 이상의 배열이 순번이 맞지 않는것이므로, arr[i] != i-1인 경우가 하나인 케이스는 전제에 성립하지않음 
숫자의 범위가 1~n까지이고, 배열은 n개임. 따라서 arr[i]는 arr[i]-1번째에 있어야함. 모든배열을 순회하면서 처음부터 맞춰감<br>

#### 계획 검증하기
