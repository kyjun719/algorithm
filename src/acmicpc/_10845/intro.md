#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/10845

input</br>
```
15
push 1
push 2
front
back
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
front
```


output</br>
```
1
2
2
0
1
2
-1
0
1
-1
0
3
```



#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>

1. 코틀린의 덱 구현체인 ArrayDeque로 풀기
2. 직접 만들기


#### 계획 세우기<br>
**1번풀이**

큐이므로 FIFO에 맞도록 push와 pop은 addLast, removeFirst를 사용함

**2번풀이**

직접구현은 충분히 큰 배열을 만들고, 맨앞(head)과 맨뒤 다음인덱스(비어있는 위치, tail)를 저장함<br>
값을 넣을떄는 tail에 값을 넣고 tail을 증가시킴<br>
값을 꺼넣떄는 head를 출력하고 head를 증가시킴<br>
맨 앞의 값은 head, 맨 뒤의 값은 tail -1 위치의 값<br>
head와 tail이 같으면 비어있음

#### 계획 검증하기
