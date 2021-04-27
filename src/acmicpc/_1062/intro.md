#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/1062

input</br>
2 7
antaatica
antabtica

9 8
antabtica
antaxtica
antadtica
antaetica
antaftica
antagtica
antahtica
antajtica
antaktica


output</br>
2

3

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
k개의 글자를 알때 해당단어를 읽을수 있는지 여부

#### 계획 세우기<br>
anta,tica는 필수이므로 5개의 단어는 가진채로 시작함
각 단어의 4~len-4까지 저장, 각 단어는 쉽게 비교하기 위해서 비트마스킹하여 변환
글자를 하나씩 선택하여 맞출 수 있는지 확인

#### 계획 검증하기

