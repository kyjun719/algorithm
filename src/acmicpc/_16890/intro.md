#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/16800

input</br>
ioi
imo

koooosaga
cubelover

cubehater
cubelover

startlink
startlink

output</br>
ioi

avaugrkoo

avbucreoe

atitkslrn

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
한쪽은 앞에서부터, 다른쪽은 뒤에서부터 가진 알파벳을 넣을 때 나오는 문자열

#### 계획 세우기<br>
입력받은 문자열의 문자를 사전순으로 정렬
k차례
- k최소값이 c 최대값보다 작은경우 : 작은값 앞으로
- 나머지 : 큰값 뒤로
c차례
- c최대값이 k 최소값보다 작거나 같은경우: 작은값 뒤로
- 나머지 : 큰값 앞으로

#### 계획 검증하기
0)
정렬 후 순서대로 삽입, K의 알파벳이 C의 알파벳보다 큰 경우 결과값은 최소값을 만족하지 않음

ex)
azzz
aaaa
-> az, aa 중 선택
-> a를 맨앞에 놓은 경우
a???
a??a
a?za
aaza

-> z를 맨뒤에 놓은 경우
???z
??az
?aaz
aaaz
-> z를 맨뒤에 놓은경우가 더 작음

azzz
aaaz
->az, az중 선택
-> z를 먼저쓰는경우 c에서 z를 맨앞으로 놓을것이기 때문에 무조건 커짐, a를 먼저 맨앞에 넣음
a???
-> z를 앞에 하는경우
az??
az?z
azaz
-> a를 뒤에 하는경우
a??a
a?za
azza
=> a를 뒤에하는경우가 더 큼
