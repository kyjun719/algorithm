#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/16402

input</br>
5 2
Kingdom of A
Kingdom of B
Kingdom of C
Kingdom of D
Kingdom of E
Kingdom of A,Kingdom of B,1
Kingdom of C,Kingdom of D,2

5 5
Kingdom of A
Kingdom of B
Kingdom of C
Kingdom of D
Kingdom of E
Kingdom of A,Kingdom of B,2
Kingdom of C,Kingdom of D,1
Kingdom of E,Kingdom of C,2
Kingdom of C,Kingdom of D,2
Kingdom of D,Kingdom of E,2

5 4
Kingdom of A
Kingdom of B
Kingdom of C
Kingdom of D
Kingdom of E
Kingdom of A,Kingdom of B,2
Kingdom of C,Kingdom of D,1
Kingdom of E,Kingdom of C,2
Kingdom of A,Kingdom of C,1

output</br>
3
Kingdom of A
Kingdom of D
Kingdom of E

2
Kingdom of B
Kingdom of E

1
Kingdom of B

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
각 왕국들의 전쟁이 끝난후 그룹의 최상위에 있는 왕국 출력

#### 계획 세우기<br>
부모를 관리하는 배열 생성, 초기값은 본인
전쟁에서 이긴경우 진 왕국의 부모가됨
이미 동일한 부모값을 가지고 있으면 속국에서 종주국을 공격한것이므로 진쪽과 이긴쪽의 부모값을 이긴쪽의 입력순서값으로 설정

#### 계획 검증하기


