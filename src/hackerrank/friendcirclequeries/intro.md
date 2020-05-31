#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/friend-circle-queries/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=miscellaneous&h_r=next-challenge&h_v=zen


input</br>
2
1 2
1 3

4
1000000000 23
11 3778
7 47
11 1000000000

6
1 2
3 4
1 3
5 7
5 6
7 4


output</br>
2
3

2
2
2
4

2
2
4
4
4
7

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
입력 노드끼리 연결될때 그룹내 노드의 최대 갯수

#### 계획 세우기<br>
입력노드의 부모와, 해당 노드의 연결돈 노드값 저장, 입력노드의 최대 부모까지 올라간 후, 작은수가 부모가 됨, 두 수가 같은경우 넘어감<br>

#### 계획 검증하기