#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/POLY

input</br>
3
2
4
92


output</br>
2
19
4841817


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
n개의 블록으로 만들수 있는 블록 수

#### 계획 세우기<br>
n=4일때<br>
1111, 112*2, 121*4, 13*3으로 10가지<br>
211*2,22*3으로 5가지<br>
...<br>
남은게 n개, 해당 층에서 선택한 블록수가 m개이면<br>
k=1~(n-m) sum((m+k-1)*(n-m,k))

#### 계획 검증하기
1~n까지 순회, 중간값 저장