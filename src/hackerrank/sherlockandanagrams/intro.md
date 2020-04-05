#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps&h_r=next-challenge&h_v=zen

input</br>
2
abba
abcd

2
ifailuhkqq
kkkk

1
cdcd


output</br>
4
0

3
10

5

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
단어내 글자 조합이 일치하는 갯수

#### 계획 세우기<br>
단어를 전체 순환하면서 현재인덱스부터 문자열 끝까지 가면서 정렬한 문자열을 키값으로 값 증가<br>
해당 문자열의 값이 2 이상인 경우 2개를 뽑는 조합값 계산, n!/(2!*(n-2)!) = n*(n-1)/2 

#### 계획 검증하기
