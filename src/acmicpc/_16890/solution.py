import sys
from collections import deque
sys.setrecursionlimit(1000000)

a = list(sys.stdin.readline().rstrip())
b = list(sys.stdin.readline().rstrip())

a.sort()
b.sort(reverse=True)
n = (len(a)+len(b))//2

out = [' ']*n
outh, outt = 0,n-1
K = deque(a[:(n+1)//2])
C = deque(b[:(n)//2])

for i in range(n):
    kmax=0 if len(K)==0 else ord(K[-1])
    cmax=0 if len(C)==0 else ord(C[0])
    kmin=100 if len(K)==0 else ord(K[0])
    cmin=100 if len(C)==0 else ord(C[-1])

    if i%2==0:
        # k turn
        if kmin < cmax:
            out[outh] = K.popleft()
            outh += 1
        else:
            out[outt] = K.pop()
            outt -= 1
    else:
        # c turn
        if cmax <= kmin:
            out[outt] = C.pop()
            outt -= 1
        else:
            out[outh] = C.popleft()
            outh += 1
            
    print(K,C)
    print(out)

print(''.join(out))