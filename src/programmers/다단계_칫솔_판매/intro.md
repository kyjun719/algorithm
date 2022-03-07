#### 문제를 읽고 이해하기
https://programmers.co.kr/learn/courses/30/lessons/77486

input</br>
["john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"]	["-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"]	["young", "john", "tod", "emily", "mary"]	[12, 4, 2, 5, 10]
["john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"]	["-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"]	["sam", "emily", "jaimie", "edward"]	[2, 3, 5, 4]

output</br>
[360, 958, 108, 0, 450, 18, 180, 1080]
[0, 110, 378, 180, 270, 450, 0, 0]

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
잎에서 발생한 금액의 10%만큼 뿌리로 갈때 총 이익트리

#### 계획 세우기<br>
1. 돈을 번 사람의 수익을 저장함
2. 수익을 10%로 계산함
3. 해당 수익을 현재 사람의 수익에서 빼고, 해당 수익을 부모에 더함
4. 부모가 없는경우 종료하고, 부모가 있는 경우 현재 인덱스를 부모로 바꿈


#### 계획 검증하기
