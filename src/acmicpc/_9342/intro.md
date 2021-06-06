#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/9342

input</br>
15
AFC
AAFC
AAAFFCC
AAFCC
BAFC
QWEDFGHJMNB
DFAFCB
ABCDEFC
DADC
SDFGHJKLQWERTYU
AAAAAAAAAAAAABBBBBBBBBBBBBBCCCCCCCCCCCCCCCCCCDDDDDDDDDDDEEEEEEEEEEEEEEEFFFFFFFFC
AAAFFFFFBBBBCCCAAAFFFF
ABCDEFAAAFFFCCCABCDEF
AFCP
AAFFCPP

output</br>
Infected!
Infected!
Infected!
Infected!
Infected!
Good
Good
Good
Good
Good
Good
Good
Good
Good
Good

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
문자열은 {A, B, C, D, E, F} 중 0개 또는 1개로 시작해야 한다.
그 다음에는 A가 하나 또는 그 이상 있어야 한다.
그 다음에는 F가 하나 또는 그 이상 있어야 한다.
그 다음에는 C가 하나 또는 그 이상 있어야 한다.
그 다음에는 {A, B, C, D, E, F} 중 0개 또는 1개가 있으며, 더 이상의 문자는 없어야 한다.

#### 계획 세우기<br>
시작문자열과 끝 문자열이 A,B,C,D,E,F중 하나로 시작하는지 확인
A,F,C의 시작위치와 마지막 위치 확인
다음과 같은 경우 Good 출력
- 해당 문자열로 시작하고 A의 첫 인덱스가 0 또는 1이 아닌경우
- 마지막 A 다음에 F가 오지 않는경우
- 마지막 F 다음에 C가 오지 않는경우
- C가 맨 마지막이거나 해당 문자열로 끝나는 경우

#### 계획 검증하기
