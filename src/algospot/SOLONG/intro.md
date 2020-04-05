#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/SOLONG

input</br>
2
7 8
ALL 4
AND 3
FISH 8
FOR 6
SO 4
THANKS 9
THE 9
SO LONG AND THANKS FOR ALL THE FISH
7 8
ALL 4
AND 5
FISH 3
FOR 6
SO 8
THANKS 1
THE 2
SO LONG AND THANKS FOR ALL THE FISH


output</br>
28
29


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
모든 단어를 입력하기 위해 필요한 최소 타자수

#### 계획 세우기<br>
트라이를 사용하여 각 단어들을 저장해서 입력해야할 단어가 있는지 검색함<br>
트라이에 입력전에 단어들을 출현빈도의 내림차순으로, 출현빈도가 같을 경우 사전순으로 정렬하여 저장함<br>
이때 단어를 저장하면서 해당 노드의 첫번째로 나올수 있는 단어 인덱스를 저장하여 탭을 눌렀을 때 나오는 단어 계산<br>

#### 계획 검증하기
