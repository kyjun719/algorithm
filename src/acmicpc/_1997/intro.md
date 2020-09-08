#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/1997

input</br>
0 0 0

10 10 10
5
.XXXXXXXX.
XXXXXXXXXX
XXXXXXXXXX
XXXXXXXXXX
X........X
5
.XXXXXXXX.
XXXXXXXXXX
XXXXXXXXXX
XXXXXXXXXX
X........X
5
.XXXXXXXX.
XXXXXXXXXX
XXXXXXXXXX
XXXXXXXXXX
X........X
5
.XXXXXXXX.
XXXXXXXXXX
XXXXXXXXXX
XXXXXXXXXX
X........X
5
.XXXXXXXX.
XXXXXXXXXX
XXXXXXXXXX
XXXXXXXXXX
X........X
5
.XXXXXXXX.
XXXXXXXXXX
XXXXXXXXXX
XXXXXXXXXX
X........X
5
.XXXXXXXX.
XXXXXXXXXX
XXXXXXXXXX
XXXXXXXXXX
X........X
5
.XXXXXXXX.
XXXXXXXXXX
XXXXXXXXXX
XXXXXXXXXX
X........X
5
.XXXXXXXX.
XXXXXXXXXX
XXXXXXXXXX
XXXXXXXXXX
X........X
5
.XXXXXXXX.
XXXXXXXXXX
XXXXXXXXXX
XXXXXXXXXX
X........X


output</br>
{빈칸}

9 9 9 9 9

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
순서대로 블록을 쌓을때 쌓은 블록의 높이

#### 계획 세우기<br>
각 블록의 매 줄마다 겹침여부를 확인하여 겹치는 줄수를 제외한 쌓이는 높이 계산
넘치는경우 다른 박스에 넣으므로 현재블록 부터 새로운 박스에 시작함

#### 계획 검증하기
http://icpc-ecna.ysu.edu/PastResults/2006/problems.html
E번문제