#### 문제를 읽고 이해하기
https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=strings

input</br>
aabbcd

aabbccddeefghi

abcdefghhgfedecba


output</br>
NO

NO

YES

 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
한글자만 지울수 있을 때 전체 글자의 갯수가 같아지는지 여부

#### 계획 세우기<br>
글자수 카운트, 다음경우에 실패
- 글자수가 다른것이 3개 이상
- 글자수가 2이상 차이나는 경우
- 글자수로 분류하였을 때 두 그룹이 모두 2이상인 경우

#### 계획 검증하기
