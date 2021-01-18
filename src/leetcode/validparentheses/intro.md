#### 문제를 읽고 이해하기
https://leetcode.com/problems/valid-parentheses/

input</br>
s = "()"
s = "()[]{}"
s = "(]"
s = "([)]"
s = "{[]}"


output</br>
true
true
false
false
true


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
괄호가 제대로 닫히고 열렸는지 확인

#### 계획 세우기<br>
열기 괄호일경우 스택에 넣고, 닫기 괄호일경우 스택의 마지막 괄호와 비교하여 쌍을 이루지 않는경우 false반환<br>
문자열의 모든 문자를 순회한 후 스택이 비어있는지 확인<br>

#### 계획 검증하기
