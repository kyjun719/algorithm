#### 문제를 읽고 이해하기
https://leetcode.com/problems/fizz-buzz/

input</br>
n = 15

output</br>
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
15배수는 FizzBuzz, 5배수는 Buzz, 3배수는 Fizz일때 배열

#### 계획 세우기<br>
%연산으로 나머지 0일때 문자열 넣음

#### 계획 검증하기
넣을 문자열 변수를 생성하고, 3의 배수면 Fizz, 5의 배수면 Buzz를 추가함<br>
15의 배수의 경우 FizzBuzz가 완성되고, 해당변수가 비어있는경우 정수값을 배열에 넣으면 됨
 
