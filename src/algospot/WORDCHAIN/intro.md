#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/WORDCHAIN

input</br>
3
4
dog
god
dragon
need
3
aa
ab
bb
2
ab
cd


output</br>
need dog god dragon
aa ab bb
IMPOSSIBLE
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
단어가 연결되는지 반환, 연결되는 경우 단어 순서, 아닌경우 불가 반환

#### 계획 세우기<br>
단어의 첫글자와 마지막 글자를 정점으로 생각하여 그래프 생성<br>
첫글자와 마지막 글자가 정해져 있으므로 방향그래프로 봐야함<br>
정점중 홀수점이 있는경우 불가 반환, 아닌경우 오일러서킷 또는 트레일을 찾아 반환<br>  

#### 계획 검증하기
