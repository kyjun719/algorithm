#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/maximum-xor/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=miscellaneous

input</br>
3
0 1 2
3
3
7
2


5
5 1 7 4 3
2
2
0


4
1 3 5 7
2
17
6

output</br>
3 
7 
3 


7
7


22
7

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
배열의 숫자들과 입력으로 주어진 숫자를 xor할떄 가장 큰수들 

#### 계획 세우기<br>
각 배열들의 숫자를 이진수의 자리별로 저장함, 정수범위내 이므로 숫자의 모든 비트를 순회하여 트라이에 저장함<br>
검색할 숫자도 동일하게 31비트를 순회하면서, 숫자를 반전시켜 해당 숫자에 해당하는 노드가 있는경우 해당자리를 포함하는 숫자가 있는것 이므로 해당 자리의 숫자를 더하고 해당 노드로 진행함<br>
아닐경우 다른숫자의 검색을 위해 반대편 노드로 진행함<br> 

#### 계획 검증하기
