#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/HABIT

input</br>
4
2 
uhmhellouhmmynameislibe
3
banana
1 
thatsagoodquestion 
3
hello


output</br>
3
1
18
0 

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
K번 이상 등장하는 부분 문자열의 최대길이

#### 계획 세우기<br>
문자 X가 K번 이상 등장 = X를 포함하는 접미사의 접두사가 K번 이상 있음<br>
K개의 인접한 접미사의 최대 접두사 길이 계산<br>

#### 계획 검증하기
K개의 인접한 접미사의 최대 접두사 길이 계산은 i번째 인덱스의 접미사와 i+k번쨰의 접미사를 비교함<br>
