#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/1759

input</br>
4 6
a t c i s w


output</br>
acis
acit
aciw
acst
acsw
actw
aist
aisw
aitw
astw
cist
cisw
citw
istw

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
알파벳 순서대로 L개를 선택할 때 한개이상의 모음(a,i,e,o,u)와 두개이상의 자음을 가지는 단어

#### 계획 세우기<br>
글자를 하나씩 선택하면서 L개를 선택하였을 때 모음과 자음조건을 만족하는지 확인후 정답배열에 저장

#### 계획 검증하기
