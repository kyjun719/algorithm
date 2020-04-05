#### 문제를 읽고 이해하기
https://algospot.com/judge/problem/read/FANMEETING

input</br>
4
FFFMMM
MMMFFF
FFFFF
FFFFFFFFFF
FFFFM
FFFFFMMMMF
MFMFMFFFMMMFMF
MMFFFFFMFFFMFFFFFFMFFFMFFFFMFMMFFFFFFF


output</br>
1
6
2
2
 
#### 재정의와 추상화<br>
-- 문제를 읽고 본인이 이해하기 쉬운 방식으로 해석<br>
멤버와 팬이 모두 남남으로 만나지 않는 경우의 수

#### 계획 세우기<br>
최대 2\*10^5, 멤버는 팬수 이하, 팬이 2만, 1\*10^5\*1\*10^5=10^10번으로 1억초과, 타임아웃 가능<br>
카바추라 알고리즘으로 멤버와 팬의 성별을 숫자로 바꿔 곱으로 계산, 멤버수~팬수까지의 인덱스에서 곱이 0이면 경우의수++<br>
<code>
a*b = (a0+a1)*(b0+b1) = a0*b0 + a1*b0+a0*b1 + a1*b1<br>
    = z0+z1+z2
z0 = a0*b0<br>
z2 = a1*b1<br>
z1 = (a0+a1)*(b0+b1)-z0-z2<br>
</code>


#### 계획 검증하기
문제에는 50자리수 이하의 수는 일반곱셈이 더 빠른것으로 되어있음<br>
멤버와 팬은 역순으로 만나므로 팬은 역순으로 저장<br>