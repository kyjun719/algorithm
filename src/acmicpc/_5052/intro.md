#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/5052

input</br>
2
3
911
97625999
91125426
5
113
12340
123440
12345
98346

output</br>
NO
YES

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
주어진 문자열 중 접두사에 해당하는 문자열이 있는지 여부

#### 계획 세우기<br>
입력받은 문자열을 길이순으로 정렬한 후 트라이에 넣음. 진행중 끝인 단어를 만난경우 일관성이 없음으로 반환

#### 계획 검증하기