#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/1515

input</br>
1234

234092

999909

82340329923

32098221

1111111

00000000000000000000000000000000000000000000000000000000000000000000000

345029834023049820394802334909240982039842039483294792934790209

12345678910

12345678910111213141516171819202122232425262728293031323334353637383940414243444546474849505152535455565758596061626364656667686970717273747576777879808182838485868788899091929394959697989910011

0


output</br>
4

20

49

43

61

14

400

279

10

101

10


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
문자열의 끝에 도달했을때 맞는 수

#### 계획 세우기<br>
문자열을 순회하면서 숫자와 일치하는 부분이 있는지 확인. 있으면 문자열의 다음 문자를 비교   
숫자를 다돌면 1을 더하여 문자열의 이전 순회자리부터 다시 시작

#### 계획 검증하기