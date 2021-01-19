#### 문제를 읽고 이해하기
https://leetcode.com/problems/implement-strstr/

input</br>
haystack = "hello", needle = "ll"
haystack = "aaaaa", needle = "bba"
haystack = "", needle = ""


output</br>
2
-1
0


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
haystack에서 needle이 나타나는 인덱스 반환

#### 계획 세우기<br>
kmp...<br>
needle의 접두사/접미사 일치 길이 배열울 계산한 후 needle을 haystack을 돌면서 일치하지 않는경우 접미사 길이만큼 이동하고 아니면 일치길이를 하나씩 늘려 나가면서 일치확인

#### 계획 검증하기 
