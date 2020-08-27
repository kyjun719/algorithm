import sys
import math
import random
sys.setrecursionlimit(100000)

def miller_rabin(n,a):
    r=0
    d=n-1
    while d%2==0:
        r += 1
        d = d>>1
    if r==0:
        return False
    x = pow(a,d,n)
    if x==1:
        return True
    for i in range(r):
        if x==n-1:
            return True
        if i==r-1:
            return False
        x = pow(x,2,n)
    return True

def isPrime(n):
    if n <= 1:
        return False
    if n%2 == 0:
        return False
    
    for a in [2,3,5,7,11]:
        if a>=n:
            break
        if not miller_rabin(n,a):
            return False
    return True

k = int(sys.stdin.readline().rstrip())
cnt = 0
for i in range(k):
    n = int(sys.stdin.readline().rstrip())
    n = 2*n+1
    if isPrime(n):
        cnt += 1

print(cnt)