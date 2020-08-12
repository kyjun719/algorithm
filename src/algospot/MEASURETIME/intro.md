#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/MEASURETIME

input</br>
2
5
5 1 4 3 2
10
7 8 6 6 1 9 4 4 3 10


output</br>
7
25
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
정렬과정에서 숫자를 총 몇번이나 옮기는지 여부

#### 계획 세우기<br>
1) 팬윅트리<br>
각 배열의 수보다 큰 수의 갯수를 구하면서 해당 수를 포함하는 트리 업데이트<br>

2) 구간트리<br>
팬윅트리가 하는 일은 구간트리에서도 똑같이 할 수 있으므로 동일하게 수행<br>

3) 이진검색 트리사용<br>
팬윅트리로 하는것과 동일, 해당 수보다 큰 노드의 갯수를 찾고, 해당 수를 가진 노드를 추가함<br>

4) 병합정렬<br>
0. 병합정렬의 순서 : 분할 -> 정복 -> 결합
1. 배열의 길이가 1인경우 이미 정렬된 것으로 봄
2. 왼쪽과 오른쪽을 비슷한 길이로 나눔
3. 나눈다음 왼쪽과 오른쪽을 비교하여 작은 수대로 새로운 배열에 저장


#### 계획 검증하기