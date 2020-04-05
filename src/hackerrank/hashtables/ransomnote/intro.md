#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/ctci-ransom-note/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps&h_r=next-challenge&h_v=zen

input</br>
6 4
give me one grand today night
give one grand today

6 5
two times three is not four
two times two is four

7 4
ive got a lovely bunch of coconuts
ive got some coconuts


output</br>
Yes

No

No

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
첫번째 입력으로 주어진 단어로 두번째 문장 완성여부, 대소문자 구분, 같은 단어는 다른단어가 있어야함 

#### 계획 세우기<br>
키가 문자열, 값이 정수인 해시맵을 만들어서 첫번째 입력 단어 저장<br>
두번째 입력에서 출력할 단어가 없거나 남은갯수가 0인경우 실패 반환, 다 출력 가능한 경우 성공 반환<br>

#### 계획 검증하기
