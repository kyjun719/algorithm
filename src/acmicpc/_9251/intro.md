#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/9215

input</br>
ACAYKP
CAPCAK

output</br>
4

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
최장 공통 부분 글씨(LCS)

#### 계획 세우기<br>
https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
https://twinw.tistory.com/126
첫번째 행과 열은 0으로 채움
LCS(X_i, Y_i) = 0 if i==0 or j == 0
                LCS(X_i-1, Y_j-1)+1 if i,j>0 and x_i == y_i
                max(LCS(X_i, Y_j-1), LCS(X_i-1, Y_j)) if i_j>0 and x_i != y_i

#### 계획 검증하기
