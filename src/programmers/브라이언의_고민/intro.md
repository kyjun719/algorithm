#### 문제를 읽고 이해하기
https://programmers.co.kr/learn/courses/30/lessons/1830

input</br>
HaEaLaLaObWORLDb  
SpIpGpOpNpGJqOqA  
AxAxAxAoBoBoB  
HELLOWORLD  
HaEaLaLaObWORLDb  
aHbEbLbLbOacWdOdRdLdDc  
aHELLOa bWORLDb  
HaEaLaLObWORLDb  
aHELLOWORLDa  
HaEaLaLaOWaOaRaLaD  
abHELLObaWORLD  

output</br>
HELLO WORLD  
SIGONG JOA  
invalid  
HELLO WORLD  
HELLO WORLD  
HELLO WORLD  
invalid  
HELL O WORLD  
HELLOWORLD  
invalid  
invalid  
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>


#### 계획 세우기<br>
규칙1 또는 규칙2인지 확인하기 위해서 소문자의 갯수와 시작, 끝위치를 저장함.

소문자가 2개인경우 규칙2이고, 나머지는 규칙1이 맞는지 확인함.
- 모든 규칙의 시작점은 이전 파싱완료 위치보다 커야하고, 시작점과 끝점은 대문자로 되어야함
- 시작위치와 이전파싱종료 위치사이에 문자열이 있는 경우 정답에 추가하고 띄어쓰기를 더함
  - 해당 문자열에 소문자가 있는 경우 다음 파싱때 실패가 나므로 전체적으로 실패로 반환됨
- 규칙2 안에 규칙1이 될 수 있으므로 내부 문자열의 끝과 시작이 소문자인 경우 규칙1로 파싱함
- 구간내 문자열이 규칙1 또는 규칙2에 맞으면 원문으로 변환을 하고, 아니면 종료함  

남은 글자의 경우 공백을 앞에 두고 모든 문자열을 추가함

#### 계획 검증하기
