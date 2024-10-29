#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/10866

input</br>
```
15
push_back 1
push_front 2
front
back
size
empty
pop_front
pop_back
pop_front
size
empty
pop_back
push_front 3
empty
front

22
front
back
pop_front
pop_back
push_front 1
front
pop_back
push_back 2
back
pop_front
push_front 10
push_front 333
front
back
pop_back
pop_back
push_back 20
push_back 1234
front
back
pop_back
pop_back
```


output</br>
```
2
1
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
-1
-1
1
1
2
2
333
10
10
333
20
1234
1234
20
```



#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>

1. 코틀린의 덱 구현체인 ArrayDeque로 풀기
2. 직접 만들기


#### 계획 세우기<br>
**1번풀이**

덱이므로 양방향으로 넣을 수 있도록 함


**2번풀이**

직접구현은 충분히 큰 배열을 만들고, 맨앞(head)와 맨뒤 다음 인덱스(비어있는위치, tail)을 저장함<br>
양쪽으로 움직여야 하므로 head와 tail은 배열의 중간값<br>
앞에 값을 넣을때는 head를 감소시키고 값을 넣음<br>
뒤에 값을 넣을떄는 값을 넣고 tail을 증가시킴<br>
앞에 값을 꺼낼때는 head를 출력하고 head를 증가시킴<br>
뒤에 값을 꺼낼떄는 tail-1을 출력하고 tail을 감소시킴<br>
맨 앞의 값은 head, 맨뒤의 값은 tail-1<br>
head와 tail이 같으면 비어있음


#### 계획 검증하기
