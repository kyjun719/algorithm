#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/crush/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays

input</br>
5 3
1 2 100
2 5 100
3 4 100

output</br>
200
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
a~b까지 k를 더할때 배열의 최대값

#### 계획 세우기<br>
a~b까지 k값이 들어가는것이므로, 시작값에 +k, 끝값+1에 -k를 배열에 더함. 더한 배열을 순회하면서 최대 누적합 계산<br>

#### 계획 검증하기
