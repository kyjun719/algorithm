#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/minimum-absolute-difference-in-an-array/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms

input</br>
3
3 -7 0

10
-59 -36 -13 1 -53 -92 -2 -96 -54 75

5
1 -3 71 68 17


output</br>
3

1

3

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
가장 작은 값의 차의 절대값

#### 계획 세우기<br>
정렬후 바로 인접한것과 계산하여 최소값 계산<br>

a ... i ... j, a<i<j <br>
Math.abs(a-j) > Math.abs(a-i)라고 가정하면 j가 i보다 a에 가까워야하므로 전제 실패<br>

#### 계획 검증하기
