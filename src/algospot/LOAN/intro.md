#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/LOAN

input</br>
4
20000000 12 6.8
35000000 120 1.1
40000000 36 0.5
100 120 0.1


output</br>
1728691.4686372071
308135.8967737053
1119696.7387703573
0.8375416659
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
한달마다 상환할 금액

#### 계획 세우기<br>
0개월 : n, 1개월 : np-c, 2개월 : (np-c)*p-c = np^2-c(p+1), 3개월 : np^3-c(p^2+p+1)<br>
...<br>
m개월 : np^m-c(p^m+p^(m-1)...+p+1)<br>
c=np^m/(p^m+p^(m-1)...+p+1)<br>

#### 계획 검증하기