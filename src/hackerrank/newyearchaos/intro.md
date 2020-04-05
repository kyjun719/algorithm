#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/new-year-chaos/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen

input</br>
2
5
2 1 5 3 4
5
2 5 1 3 4

2
8
5 1 2 3 7 8 6 4
8
1 2 5 3 7 8 6 4

1
8
1 2 5 3 4 7 8 6


output</br>
3
Too chaotic

Too chaotic
7

4

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
정렬된 배열에서 입력된 배열로 바뀔때 이동해야 하는 최소 횟수 

#### 계획 세우기<br>
1~n까지 숫자가 나타나므로 해당 인덱스의 값이 배열의 순번이 되어야함<br>
- 배열값-(인덱스+1)이 2보다 크면 배열값의 원래 위치보다 두개 초과 나온것이므로 chaotic 반환
- 앞의 두 배열을 본인과 비교하여 큰값이 있으면 "뇌물"을 준것이므로 더함

#### 계획 검증하기
