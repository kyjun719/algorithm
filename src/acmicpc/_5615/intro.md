#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/5615

input</br>
10
4
7
9
10
12
13
16
17
19
20

output</br>
2

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
2xy+x+y를 만족하지 않는 수의 갯수

#### 계획 세우기<br>
2xy+x+y=(2x+1)(y+1/2)-1/2=n, (2x+1)(2y+1)=2n+1, 따라서 2n+1이 소수일 때 x,y는 존재하지 않음
밀러-라빈 소수 판별법을 사용하여 판별함
1) n-1=2^s*d로 놓을때 s와 d값 검색
2) s=0인경우 a^d=1이므로 판정이 불가능함
3) a^d%n = 1인경우 소수일 확률이 높음
4) r=0,1,2,⋯,s−1중 적어도 하나에 대해 a^(d*2^r)%n=n−1이면 소수


#### 계획 검증하기
https://jeonggyun.tistory.com/226
https://ko.wikipedia.org/wiki/%EB%B0%80%EB%9F%AC-%EB%9D%BC%EB%B9%88_%EC%86%8C%EC%88%98%ED%8C%90%EB%B3%84%EB%B2%95
https://casterian.net/archives/396