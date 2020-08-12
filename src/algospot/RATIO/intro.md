#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/RATIO

input</br>
5
10 8
100 80
47 47
99000 0
1000000000 470000000


output</br>
1
6
-1
1000
19230770
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
승률 1%를 올리기 위한 최소 연속승수

#### 계획 세우기<br>
현재 승률이 100%일 경우 -1<br>
p+1=(m+c)/(n+c)*100<br>
(p+1)(n+c)=100(m+c)<br>
(100-p-1)c=n(p+1)-100*m<br>
c=(n(p+1)-100*m)/(100-p-1)<br> 

#### 계획 검증하기
