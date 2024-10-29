#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/1406

input</br>
```
abcd
3
P x
L
P y

abc
9
L
L
L
L
L
P x
L
B
P y

dmih
11
B
B
P x
L
B
B
B
P y
D
D
P z
```


output</br>
```
abcdyx

yxabc

yxz
```



#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>

1. 리스트로 풀기 -> 시간초과
2. LinkedList를 사용하여 구현


#### 계획 세우기<br>
공백문자 포함 전체 문자열의 최대갯수인 600_001개로 잡고, 맨 마지막에 공백문자로 초기화 시키고 커서 이동

삭제는 커서의 왼왼쪽 문자와 커서를 연결시킴
```
cursor.prev = cursor.prev.prev
cursor.prev.prev.next = cursor
```


추가는 커서의 왼쪽 문자와 새로운 문자를 연결시키고, 새로운 문자와 커서를 연결시킴
```
addIdx.prev = cursor.prev
addIdx.next = cursor

cursor.prev.next = addIdx
cursor.prev = addIdx
```



#### 계획 검증하기
입력은 최대  100_000, 명령어는 최대 500_000으로 최대 문자열 길이는 600_000, 공백문자 포함 600_001

삭제시 왼왼쪽, 추가시 왼쪽이 빈 문자열일수 있으므로 체크해야함