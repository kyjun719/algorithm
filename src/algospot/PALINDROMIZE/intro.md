#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/PALINDROMIZE

input</br>
3
there
amanaplanacanal
xyz


output</br>
7
21
5


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
해당단어에 글자를 추가하여 시작과 끝이 동일한 단어의 길이 출력

#### 계획 세우기<br>
원래 문자열과 뒤집은 문자열의 최대 중복길이를 계산해야함<br>
해당 문자열의 처음부터 검색하면서 시작위치+일치하는길이=문자열이 되면 해당 일치하는 길이가 최대 길이임<br>
팰린드롬 길이 = 문자열*2-최대 일치길이

#### 계획 검증하기
