#### 문제를 읽고 이해하기
https://programmers.co.kr/learn/courses/30/lessons/43164

input</br>
[['ICN', 'JFK'], ['HND', 'IAD'], ['JFK', 'HND']]
[['ICN', 'SFO'], ['ICN', 'ATL'], ['SFO', 'ATL'], ['ATL', 'ICN'], ['ATL','SFO']]

output</br>
[ICN, JFK, HND, IAD]

[ICN, ATL, ICN, SFO, ATL, SFO]


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
ICN에서 시작하여 모든 항공권을 사용하는 경로

#### 계획 세우기<br>
ICN을 시작으로 깊이우선탐색으로 진행. 가능한 모든경로를 탐색해야 하므로 탐색이 끝나면 이전상태로 되돌림<br>
검색한 경로의 갯수와 전체 항공권의 갯수+1이면 올바른 경로를 찾은것 이므로 종료<br>

#### 계획 검증하기
