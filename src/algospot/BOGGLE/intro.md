#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/BOGGLE

input</br>
1
URLPM
XPRET
GIAET
XTNZY
XOQRS
6
PRETTY
GIRL
REPEAT
KARA
PANDORA
GIAZAPX


output</br>
PRETTY YES
GIRL YES
REPEAT YES
KARA NO
PANDORA NO
GIAZAPX YES
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
글자판에서 입력으로 주어진 단어를 연결할 수 있는가 

#### 계획 세우기<br>
다음 글자로 넘어갈 수 있는 방향은 9개, 최대 10글자 이므로 9^10 <= 10^10 = 100억<br>
-> 해당 글자를 방문한 순서와 위치 저장,-1로 초기화, 1인경우 단어 연결 성공, 0인경우 단어연결 실패

#### 계획 검증하기
5*5*10 = 250개 int형 배열사용
