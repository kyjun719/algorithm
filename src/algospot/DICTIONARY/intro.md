#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/DICTIONARY

input</br>
3
3
ba
aa
ab
5
gg
kia
lotte
lg
hanhwa
6
dictionary
english
is
ordered
ordinary
this


output</br>
INVALID HYPOTHESIS
ogklhabcdefijmnpqrstuvwxyz
abcdefghijklmnopqrstuvwxyz
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
입력한 단어들이 사전순으로 정렬되어 있을 때 알파벳 순서, 아닌경우 틀림 출력

#### 계획 세우기<br>
각 단어들을 비교하여 글자가 다른 부분에 대해 간선 저장함<br>
인접한 단어만 비교하는 이유<br>
- A,B,C를 검사할 때 A와 C를 비교하여 n번째 글자에서 차이가 생긴경우, B도 n-1번째 글자까지는 동일함<br>
-- B[n] == A[n] : A[n]이 C[n]보다 먼저옴<br>
-- B[n] == C[n] : A[n]이 B[n]보다 먼저 오므로 A[n]이 C[n]보다 먼저옴<br>
-- B[n]이 다른값인 경우 : A[n]과 C[n] 사이에 B[n]이 있으므로 A[n]이 C[n]보다 먼저옴<br>
따라서 인접한 두 단어씩 비교해도 위상정렬에 필요한 모든 정보를 얻을 수 있음<br>
DAG의 순서를 구하기 위해 dfs가 끝나는 순서를 기록한 뒤, 뒤집은 다음 중복검사 후 반환<br>
글자가 중복되면 사이클이 있는것이므로 이론은 틀림<br>

#### 계획 검증하기
