#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/special-palindrome-again/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=strings

input</br>
5
asasd

7
abcbaba

4
aaaa


output</br>
7

10

10

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
글자가 연속되거나, 가운데를 제외한 나머지 글자가 일치하는 부분 문자열의 갯수

#### 계획 세우기<br>
글자마다 연속하는 갯수를 세서 배열에 저장함<br>
n개의 연속하는 문자열에서 총 나올수 있는 부분문자열의 갯수 = 1개로 구성된 부분문자열+2개로 구성된 부분문자열+...+n개로 구성된 부분 문자열<br>
                                      = n + n-1 + n-2 + ... + 1<br>
                                      = n(n+1)/2<br>
연속하는 문자열 사이에 한글자만 존재하는 경우 두 길이중 짧은 길이만큼 대칭 문자열이 나타남<br>

#### 계획 검증하기
