#### 문제를 읽고 이해하기
https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

input</br>
matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
matrix = [[-5]], k = 1


output</br>
13
-5


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
주어진 배열에서 k번쨰 배열 찾음

#### 계획 세우기<br>
1. 배열의 수를 자리수로 재배열하여 해당 자릿수의 갯수로 판별함
2. 이분검색을 통해 k번째에 도달하도록 구현함

#### 계획 검증하기

 
