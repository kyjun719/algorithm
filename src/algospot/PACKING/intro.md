#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/PACKING

input</br>
2
6 10
laptop 4 7
camera 2 10
xbox 6 6
grinder 4 7
dumbell 2 5
encyclopedia 10 4
6 17
laptop 4 7
camera 2 10
xbox 6 6
grinder 4 7
dumbell 2 5
encyclopedia 10 4


output</br>
24 3
laptop
camera
grinder
30 4
laptop
camera
xbox
grinder


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
절박도를 최대화 하는 물건들의 조합

#### 계획 세우기<br>
해당 물건을 넣었을 때와 안넣었을 때 최대 절박도가 같으면 넘어감<br>
아닐 경우 해당 물건을 포함해야함, 해당물건의 무게만큼 빼고 다음 물건 진행<br>

#### 계획 검증하기
