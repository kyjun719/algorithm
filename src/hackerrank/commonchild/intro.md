#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/common-child/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=strings

input</br>
HARRY
SALLY

AA
BB

SHINCHAN
NOHARAAA

ABCDEF
FBDAMN

output</br>
2

0

3

2
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
두 문자열이 순서를 지키는 가장 긴 문자열 

#### 계획 세우기<br>
1. s1의 문자들을 순서대로 돌면서 s2에 해당하는 문자들을 선택하여 재귀적으로 갯수 계산, 시간초과<br>
2. https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
LCS(longest common subsequence)는 NP문제지만, 문자열의 길이가 상수이면 N^2으로 해결 가능<br>
LCS는 다음과 같음

```
                0 if i==0 or j == 0
LCS(X_i, Y_i) = LCS(X_i-1, Y_j-1)+x_i if i,j>0 and x_i == y_i
                max(LCS(X_i, Y_j-1), LCS(X_i-1, Y_j)) if i_j>0 and x_i != y_i

```


#### 계획 검증하기
