#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/JAEHASAFE

input</br>
2
3
abbab
babab
ababb
bbaba
2
RMDCMRCD
MRCDRMDC
DCMRCDRM


output</br>
6
10 

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
i번째 문자열을 쉬프트시켜 i+1번째 문자열로 만듦, 총 n개의 문자열을 맞추기 위해 이동해야 하는 최소 수<br>
홀->짝은 반시계, 짝->홀은 시계<br>

#### 계획 세우기<br>
abcd 반시계-> bcda<br>
	   시계  -> dabc<br>
H*2에서 N을 찾는것이므로 KMP 알고리즘으로 반시계 방향일때 검색임<br>
시계방향의 경우 a를 b로 시계방향으로 돌리는것과 b를 a로 반시계 방향으로 돌리는것과 동일함<br>
따라서 반시계일때 H=i번째, N=i+1번째, 시계일때 H=i+1번째, N=i번째<br>

#### 계획 검증하기
