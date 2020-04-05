#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/repeated-string/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen

input</br>
aba
10
a
1000000000000

output</br>
7
1000000000000
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
문자열을 n번 반복할 때 나오는 a의 갯수

#### 계획 세우기<br>
s에 a가 없는경우 종료, (s중 a갯수)*(n/s길이)+(n%s길이)까지의 a갯수 반환, 

#### 계획 검증하기
