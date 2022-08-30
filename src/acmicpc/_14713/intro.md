#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/14713

input</br>
3
i want to see you
next week
good luck
i want next good luck week to see you

2
i found
an interesting cave
i found an cave interesting

2
please
be careful
pen pineapple apple pen

output</br>
Possible

Impossible

Impossible



#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
입력 순서대로만 단어를 사용할 수 있을때 출력가능여부 

#### 계획 세우기<br>
사용할 수 있는 문자열을 key, 배열의 인덱스와 배열내의 문자열의 인덱스를 value로 저장한 맵을 가지고  
해당 단어를 찾으면 지운다음 다음단어를 저장하고, 단어를 못찾거나 사용할 단어가 남으면 실패 처리

#### 계획 검증하기

