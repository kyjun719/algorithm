#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/EDITORWARS

input</br>
4
4 4
ACK 0 1
ACK 1 2
DIS 1 3
ACK 2 0
100 4
ACK 0 1
ACK 1 2
ACK 2 3
ACK 3 4
3 3
ACK 0 1
ACK 1 2
DIS 2 0
8 6
DIS 0 1
ACK 1 2
ACK 1 4
DIS 4 3
DIS 5 6
ACK 5 7


output</br>
MAX PARTY SIZE IS 3
MAX PARTY SIZE IS 100
CONTRADICTION AT 3
MAX PARTY SIZE IS 5 
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
모순이 없을때 사람의 최대수, 모순이 있을때 발생하는 입력의 순번 구하기

#### 계획 세우기<br>
1) 동지의 적은 나의 적이다 : 나와 상대가 같은 편집기를 쓰면, 상대와 다른 편집기를 쓰는 사람은 나와 다른 편집기를 씀<br>
2) 적의 적은 나의 동지다 : 나와 상대가 다른 편집기를 쓰면, 상대와 다른 편집기를 쓰는 사람은 나와 같은 편집기를 씀<br>
DIS a b =>
- a의 루트, b의 루트 찾음
- a의 루트와 b의 루트가 같으면 모순
- 적의 적은 나의 동지이므로 a의루트와 b의 루트의 적과 합침, b의루트와 a의 루트의 적과 합침
- 합친 루트의 적은 상대방의 합친 루트

ACK a b =>
- a의 루트, b의 루트 찾음
- a의 루트와 b의 루트가 적대관계이면 모순
- 동지의 적은 나의적 이므로, a의 루트와 b의 루트를 합침, a루트의 적과 b루트의 적을 합침
- 합친 루트의 적은 상대방의 합친 루트

#### 계획 검증하기
