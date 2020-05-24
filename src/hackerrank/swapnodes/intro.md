#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/reverse-shuffle-merge/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms

input</br>
3
2 3
-1 -1
-1 -1
2
1
1


5
2 3
-1 4
-1 5
-1 -1
-1 -1
1
2

11
2 3
4 -1
5 -1
6 -1
7 8
-1 9
-1 -1
10 11
-1 -1
-1 -1
-1 -1
2
2
4

output</br>
3 1 2
2 1 3

4 2 1 5 3

2 9 6 4 1 3 7 5 11 8 10
2 6 9 4 1 3 7 5 10 8 11

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
해당 깊이의 노드의 왼쪽과 오른쪽을 바꿨을 때 중위순회값 

#### 계획 세우기<br>
입력값에 따른 노드 왼쪽과 오른쪽 서브트리 설정후 해당깊이의 노드의 왼쪽과 오른쪽을 바꾼 후 중위순회값 출력

#### 계획 검증하기
