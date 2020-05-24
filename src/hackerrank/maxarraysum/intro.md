#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/max-array-sum/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming

input</br>
5
3 7 4 6 5

5
2 1 5 8 4

5
3 5 -7 8 10


output</br>
13

11

15

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
인접하지않은 원소합의 최대값  

#### 계획 세우기<br>
첫번째 합의 최대값 = arr[0]<br>
두번째 합의 최대값 = max(arr[0], arr[1])<br>
i번째 합의 최대값 = max(arr[i], sum[i-1], sum[i-2]+arr[i])<br>
arr[i] : 현재 원소<br>
sum[i-1] : 현재원소를 선택하지 않을때 최대값<br>
sum[i-2]+arr[i] : 현재원소를 선택할때 이전값과 합<br>

#### 계획 검증하기
