#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/1053

input</br>
babacvabba


output</br>
2


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
문자열의 어떤 위치에 어떤 문자를 삽입 (시작과 끝도 가능), 어떤 위치에 있는 문자를 삭제, 어떤 위치에 있는 문자를 교환, 서로 다른 문자를 교환할 수 있을때 팰린드롬을 만드는 최소 연산수

#### 계획 세우기<br>
연산이 끝난 부분은 팰린드롬이라고 생각했을 때, 연산을 한 후에 다른문자를 교환하는건 이득이 되지 않음.
모든 문자열에 대해 연산을 한 후 계산한 최소값과, 안한 문자열의 최소값을 비교해야함.
연산은 다음과 같이 진행되며, 앞에서부터 센 인덱스를 i, 뒤에서부터 센 인덱스를 j로 함.
1. 문자열 삽입 : j번째에 i와 같은 문자열이 삽입되므로, 0-i,j+1~끝자리 까지 일치하므로, 다음탐색은 i+1부터j까지임
2. 문자열 삭제 : j번째 문자열을 삭제하므로 다음 탐색은 i부터 j-1까지임
3. 문자열 교환 : i번째와 j번째가 다른경우 하나의 문자열은 교환해야 하므로 교환이 일어나면 +1, 일어나지 않으면 0을 더함. 다음탐색은 i+1~j-1

#### 계획 검증하기
https://bongssi.tistory.com/entry/%ED%8C%B0%EB%A6%B0%EB%93%9C%EB%A1%AC-%EA%B3%B5%EC%9E%A5