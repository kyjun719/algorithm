#### 문제를 읽고 이해하기
https://www.acmicpc.net/problem/4811

input</br>
6
1
4
2
3
30
0

output</br>
132
1
14
2
5
3814986502092304


#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
약이 한조각인경우 W, 반조각인경우 H를 적을때 서로다른 문자열의 갯수

#### 계획 세우기<br>
W를 뽑은경우 H가 추가되는것 이므로, W와 H를 각각 n개씩 가진 문자열의 갯수를 찾기로 변환할 수 있음  
arr[i][j] = W가 i개, H가 j개 가진 문자열의 조합수 일때  
W와 H가 1개씩 있는경우 : W+H = arr[1][0] = arr[1][1]  
W와 H가 2개씩 있는경우 : WHH+W, WWH+H = arr[1][2] + arr[2][1] = arr[2][2]  
W와 H가 3개씩 있는경우 : W가 2개, H가 3개조합 + W가 3개, H가 2개조합 = arr[2][3] + arr[3][2] = arr[3][3]
...  
W와 H가 n개씩 있는경우 : arr[n][n-1] + arr[n-1][n]


#### 계획 검증하기
https://ko.wikipedia.org/wiki/%EC%B9%B4%ED%83%88%EB%9E%91_%EC%88%98#%EC%A0%90%ED%99%94%EC%8B%9D  
https://kimtaehyun98.tistory.com/67
