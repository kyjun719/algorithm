#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/ALLERGY

input</br>
2
4 6
cl bom dara minzy
2 dara minzy
2 cl minzy
2 cl dara
1 cl
2 bom dara
2 bom minzy
10 7
a b c d e f g h i j
6 a c d h i j
3 a d i
7 a c f g h i j
3 b d g
5 b c f h i
4 b e g j
5 b c g h i 


output</br>
2
3
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
모두 먹을 수 있는 최소 음식수

#### 계획 세우기<br>
먹을수 있는 음식이 가장 적은 사람부터 검색, 해당 사람이 먹을수 있는 음식을 먹을수 있는 사람을 체크하고 다음 탐색<br>
모두 먹을수 있는 음식이 있는경우 음식수 계산<br>

#### 계획 검증하기
