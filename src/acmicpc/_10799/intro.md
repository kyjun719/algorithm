#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/10799

input</br>
```
()(((()())(())()))(())

(((()(()()))(())()))(()())
```


output</br>
```
17

24
```



#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
인접한 괄호쌍을 만났을때 열린 괄호의 갯수


#### 계획 세우기<br>
여는 괄호를 만났을떄 바로 다음이 닫는 괄호이면 스택의 길이만큼 더함

아니면 스택에 넣고 닫는 괄호를 만나면 하나씩빼고 결과에 1을 더함


#### 계획 검증하기
