#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/WILDCARD

input</br>
2
he?p
3
help
heap
helpp
*p*
3
help
papa
hello


output</br>
heap
help
help
papa
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
와일드카드와 일치하는 단어를 아스키코드 순서대로 출력

#### 계획 세우기<br>
와일드카드와 단어 비교<br>
1. 둘다 같은 글자인경우 -> 다음글자 검색<br>
2. ?인 경우 -> 다음글자 검색<br>
3. *인 경우 -><br>
와일드 카드의 마지막인경우 : 참으로 종료<br>
*다음 글자와 일치하는 글자가 없는경우 : 거짓으로 종료<br>
*다음 글자를 찾은 경우 : 다음글자 검색<br>

#### 계획 검증하기
*은 0~n개까지의 글자가 매칭되므로 비교할 글자의 현재위치부터 끝까지 매칭되는지 검색해야함
