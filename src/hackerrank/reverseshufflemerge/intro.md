#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/reverse-shuffle-merge/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms

input</br>
eggegg

abcdefgabcdefg

aaabeeba


output</br>
egg

agfedcb

abea
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
k개의 숫자를 선택해서 최대값과 최소값의 차이가 가장 작은수

#### 계획 세우기<br>
지나오면서 가장 작은 문자, 쓸수 있는 문자중 가장 작은 문자<br>
현재 문자가 쓸수 있는 문자보다 큰경우 -> 해당문자를 못지나 가는경우 -> 이전에 본 문자중 가장 작은것 보다 큰경우 이전에 본 문자로 돌아감<br>
											-> 이전에 본 문자중 가장 작은것 보다 작은경우 해당문자 사용<br>
						-> 해당 문자를 지나갈수 있는경우 지나감, 이전에 본 문자보다 작은경우 해당문자를 이전에 본 문자중 작은 문자로 설정<br>
현재 문자가 쓸수 있는 문자보다 작은경우 -> 해당문자 사용, 쓸수있는 문자중 작은문자 계산, 이전에 본 문자 최대값으로 초기화<br>


#### 계획 검증하기
