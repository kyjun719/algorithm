#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/JUMPGAME

input</br>
2
7
2 5 1 6 1 4 1
6 1 1 2 2 9 3
7 2 3 2 1 3 1
1 1 3 1 7 1 2
4 1 2 3 4 1 2
3 3 1 2 3 4 1
1 5 2 9 4 7 0
7
2 5 1 6 1 4 1
6 1 1 2 2 9 3
7 2 3 2 1 3 1
1 1 3 1 7 1 2
4 1 2 3 4 1 3
3 3 1 2 3 4 1
1 5 2 9 4 7 0


output</br>
YES
NO
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
왼쪽위에서 오른쪽아래로 갈수 있는지 확인

#### 계획 세우기<br>
해당칸의 숫자만큼 이동, 다음칸으로 이동한적이 없는경우 이동, 이동 못하는경우 종료

#### 계획 검증하기
오른쪽 또는 아래로만 이동함