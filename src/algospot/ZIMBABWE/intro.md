#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/ZIMBABWE

input</br>
4
321 3
123 3
422 2
12738173912 7 


output</br>
5
0
2
11033 
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
오른후의 계란가격을 자릿수를 바꿔서 m으로 나눠떨어지는 갯수를 1,000,000,007로 나눈 수

#### 계획 세우기<br>
이전보다 가격이 올랐으므로 이전 가격은 무조건 현재 가격보다 낮음<br>
m의 최대값이 20이므로 이전값의 작음여부, 나머지값, 해당자리 위치의 값 사용여부를 저장함<br>
입력된 숫자의 각 자리를 순회하면서 다음과 같은 경우를 제외하고 다음 자리 순회<br>
- 입력된 자리의 수를 사용한 경우
- 입력된 자리의 수가 검색할 수보다 큰경우
- 입력된 자리의 수가 이전자리의 수와 동일하고 이전자리수를 사용한 경우
사용한 자리수, 나머지, 입력한 수와 대소여부를 계산하여 다음자리 검색<br> 

#### 계획 검증하기
