#### 문제를 읽고 이해하기
https://leetcode.com/problems/find-the-duplicate-number/

input</br>
nums = [1,3,4,2,2]
nums = [3,1,3,4,2]


output</br>
2
3


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
n+1개 배열에서 1~n까지의 숫자가 주어질때 중복되는 수

#### 계획 세우기<br>
1. 리스트에 넣어 해당수가 있는지 확인하고, 없으면 리스트에 넣고 있으면 해당수를 반환함.
2. 한 수는 해당배열의 값으로 이동하고, 다른수는 두번 이동함.<br>
이 때 한수는 무조건 중복되므로, 언젠간 두개의 위치가 같아짐. 이때 해당 수는 사이클안에 있다고 판단할 수 있음.<br>
처음부터 시작했을 때 해당 수와 만나는 점이 사이클의 입구임

#### 계획 검증하기
https://en.wikipedia.org/wiki/The_Tortoise_and_the_Hare
https://leetcode.com/problems/find-the-duplicate-number/solution/

