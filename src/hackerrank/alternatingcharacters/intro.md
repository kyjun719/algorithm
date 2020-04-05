#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/alternating-characters/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=strings

input</br>
5
AAAA
BBBBB
ABABABAB
BABABA
AAABBB

output</br>
3
4
0
0
4
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
동일한 글자가 나열되지 않도록 할 때 지워야 되는 글자 수

#### 계획 세우기<br>
문자열 순회하면서 기준 글자와 같은 글자면 카운트 증가, 다르면 기준글자를 변경하여 카운트 반환

#### 계획 검증하기
