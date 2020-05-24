#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/decibinary-numbers/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen

input</br>
10
19
25
6
8
20
10
27
24
30
11


output</br>
102
103
11
12
110
100
8
31
32
5
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
n번째 순서에 해당하는 decibinary 숫자 출력

#### 계획 세우기<br>
https://math.stackexchange.com/questions/3540243/whats-the-number-of-decibinary-numbers-that-evaluate-to-given-decimal-number/3545775#3545775
해당 페이지의 공식으로 모든 수의 자릿수에 대한 갯수를 미리 계산하고, 숫자 갯수의 누적합을 구함<br>
입력의 번째수에 해당하는 숫자 검색후, 해당 숫자내에서 몇번째 숫자인지 계산<br>
각 자리의 숫자를 계산하기 위해서는 해당 숫자를 decibinary형식의 m자리에 몇까지 쓸수 있는지를 계산해야함<br>
pmax=min(9, d/2^m)이므로 m번째 자리의 숫자는 p값중 가장 큰값으로 되고, 나머지 자리는 0~pmax-1로 구성됨<br>
따라서 각 자리마다 들어가는 숫자를 구하고, 해당 수만큼 빼면서 모든 자리를 순회함<br>  

#### 계획 검증하기
