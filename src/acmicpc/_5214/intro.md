#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/5214

input</br>
9 3 5
1 2 3
1 4 5
3 6 7
5 6 7
6 8 9

15 8 4
11 12 8 14 13 6 10 7
1 5 8 12 13 6 2 4
10 15 4 5 9 8 14 12
11 12 14 3 5 6 1 13

output</br>
4

3

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
1번에서 N번으로 가는데 방문하는 역의 최소갯수

#### 계획 세우기<br>
배열을 하나두고 1~n까지는 인덱스=역, 값=하이퍼루프(n+1+순번)
n+1~n+1+1000까지는 인덱스=하이퍼루프, 값=역

너비우선 검색을 통해 현재값이 n+1보다 큰경우 하이퍼루프, 작은경우 역으로 간주하여 이동값을 늘림 

#### 계획 검증하기
https://yabmoons.tistory.com/260