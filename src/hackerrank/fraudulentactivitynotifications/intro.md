#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting

input</br>
9 5
2 3 4 2 3 6 8 4 5

5 4
1 2 3 4 4


output</br>
2

0

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
d개 이전내의 중간값과 현재값으르 비교하여 현재값이 더 큰 횟수

#### 계획 세우기<br>
counting sort(숫자의 총 갯수를 구한 후, 숫자 크기순으로 끝값 배열에 저장. 끝값부터 채워감) 참조<br>
현재 위치 전 d개의 숫자에 대해 등장 횟수를 구한 후, d/2번째 수가 중간값이므로 해당 수 계산, d가 짝수인 경우 해당값 다음에 나타나는 수<br>
현재값이 중간값*2 보다 큰경우 값 더함

#### 계획 검증하기
