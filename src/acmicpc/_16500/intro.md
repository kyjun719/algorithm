#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/16500

input</br>
softwarecontest
2
software
contest

aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
8
aa
aaaa
aaaaaa
aaaaaaaaaa
aaaaaaaaaaaa
aaaaaaaaaaaaaaaaaaaa
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

softwarecontestsoft
3
soft
software
contest

output</br>
1

0

1

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
단어들을 사용해서 입력된 문장을 만들수 있는지 여부

#### 계획 세우기<br>
모든 단어를 순회하면서 현재위치와 일치하는경우 채우고 다음으로 넘어감.
사용한 단어수와 현재 문자열의 위치값으로 캐시에 저장. 

#### 계획 검증하기


