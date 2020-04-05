#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/count-triplets-1/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps

input</br>
4 2
1 2 2 4

6 3
1 3 9 9 27 81

5 5
1 5 5 25 125

4 2
1 2 4 2


output</br>
2

6

4

1

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
3개가 연속하는 등비수열의 갯수

#### 계획 세우기<br>
- 현재값이 a*r*r이 되는 경우 : 답에 더함
- 현재값이 a*r이 되는 경우 : a*r*r의 갯수를 저장해는 맵에 더함
- 현재값의 a*r을 계산하여 저장 

#### 계획 검증하기
