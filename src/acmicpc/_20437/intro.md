#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/20437

input</br>
2
superaquatornado
2
abcdefghijklmnopqrstuvwxyz
5

1
abaaaba
3

output</br>
4 8
-1

3 4

#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
시작과 끝글자 포함 K개를 가지는 가장 짧은 부분문자열의 시작과 끝 인덱스

#### 계획 세우기<br>
각 글자들의 인덱스를 저장하고 다음 두 값을 계산함.
- 리스트의 k-1 다음번째 아이템간의 차이가 짧은것(부분 문자열의 길이가 짧은것)
- 리스트의 k-1 다음번째 아이템간의 차이가 긴것(부분 문자열의 길이가 긴것)

#### 계획 검증하기
