#### 문제를 읽고 이해하기
https://leetcode.com/problems/course-schedule/

input</br>
numCourses = 2, prerequisites = [[1,0]]
numCourses = 2, prerequisites = [[1,0],[0,1]]


output</br>
true
false


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
왼쪽이전에 오른쪽 수업을 들어야 할 때 모든 강의를 들을수 있는지 여부

#### 계획 세우기<br>
각 강의들을 듣기위한 강의들, 해당 강의를 들어야 들을수 있는 강의를 저장함<br>
필요 강의가 없는것부터 시작하여 강의를 들었음을 체크함<br>
듣지않은 강의가 있으면 실패

#### 계획 검증하기 
