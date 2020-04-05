#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/QUADTREE

input</br>
4
w
xbwwb
xbwxwbbwb
xxwwwbxwxwbbbwwxxxwwbbbwwwwbb


output</br>
w
xwbbw
xxbwwbbbw
xxwbxwwxbbwwbwbxwbwwxwwwxbbwb
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
그림을 4분할 하였을 때 1234 -> 3412로 변경된 그림, x가 있는경우 흑백 복합 그림

#### 계획 세우기<br>
x가 포함된 경우 -> 4개 부분이 복합<br>
x가 포함되지 않은 경우 -> 단일<br>
solve(start, string) -> start부터 end까지 쿼드트리 압축
cnt = start<br>
rt = solve(cnt,string); cnt+=rt.length()<br>
lt = solve(cnt, string); cnt+=lt.length()<br>
rb = solve(cnt, string); cnt+=rb.length()<br>
lb = solve(cnt, string);<br>
return rb+lb+rt+lt;

#### 계획 검증하기
input의 idx번째 글자가 x인경우 -> 복합그림 <br>
input의 idx번째 글자가 x가 아닌경우 -> 단일그림<br>