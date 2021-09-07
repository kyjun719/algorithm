#### 문제를 읽고 이해하기
https://programmers.co.kr/learn/courses/30/lessons/81305

input</br>
3	[12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1]	[[-1, -1], [-1, -1], [-1, -1], [-1, -1], [8, 5], [2, 10], [3, 0], [6, 1], [11, -1], [7, 4], [-1, -1], [-1, -1]]
1	[6, 9, 7, 5]	[[-1, -1], [-1, -1], [-1, 0], [2, 1]]
2	[6, 9, 7, 5]	[[-1, -1], [-1, -1], [-1, 0], [2, 1]]
4	[6, 9, 7, 5]	[[-1, -1], [-1, -1], [-1, 0], [2, 1]]

output</br>
40
27
14
9
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
이진트리형식의 방 연결구조에서 k개의 그룹으로 나눌때 그룹의 합 최대값의 최소값

#### 계획 세우기<br>
k-1개의 간선을 삭제해야 하므로 
이분탐색을 통해 그룹의 합의 최대값이 기준값을 넘지 않을때 그룹의 갯수가 k개 이하인지 계산
- 왼쪽과 오른쪽을 합쳐도 기준값을 넘지 않는경우
- 왼쪽 또는 오른쪽을 합쳤을 때 기준값을 넘지 않는경우
- 둘다 합치지 못하는 경우
- 그외

#### 계획 검증하기
https://tech.kakao.com/2021/07/08/2021-%EC%B9%B4%EC%B9%B4%EC%98%A4-%EC%9D%B8%ED%84%B4%EC%8B%AD-for-tech-developers-%EC%BD%94%EB%94%A9-%ED%85%8C%EC%8A%A4%ED%8A%B8-%ED%95%B4%EC%84%A4/