#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays&h_r=next-challenge&h_v=zen

input</br>
5 4
1 2 3 4 5

output</br>
5 1 2 3 4
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
왼쪽으로 d번을 움직였을때 나타나는 배열

#### 계획 세우기<br>
ret[i] = arr[(i+d)%n]

#### 계획 검증하기
