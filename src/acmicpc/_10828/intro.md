#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/10828

input</br>
```
14
push 1
push 2
top
size
empty
pop
pop
pop
size
empty
pop
push 3
empty
top

7
pop
top
push 123
top
pop
top
pop
```


output</br>
```
2
2
0
2
1
-1
0
1
-1
0
3

-1
-1
123
123
-1
-1
```



#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>

1. 코틀린의 덱 구현체인 ArrayDeque로 풀기
2. 직접 만들기


#### 계획 세우기<br>
**1번풀이**

스택이므로 LIFO에 맞게 push와 pop은 addLast, removeLast를 사용함


**2번풀이**

직접 구현은 충분히 큰 배열을 만들고, 맨 뒤 위치만 저장함.<br>
인덱스가 0이면 비어있는것으로 보기위해 한칸일 비워놓음.<br>
한칸을 비워놨으므로 인덱스를 먼저 증가시키고 값을 넣음<br>
꺼낼때는 값을 출력하고 인덱스를 감소시킴<br>


#### 계획 검증하기
