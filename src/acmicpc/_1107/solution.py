import sys
from collections import deque

sys.setrecursionlimit(100000)
input=sys.stdin

n=int(input.readline().rstrip())
m=int(input.readline().rstrip())

blocked=input.readline().rstrip().replace(' ','')
ans=abs(n-100)

def getlen(num):
    if num==0:
        return 1 if blocked.find('0')==-1 else 0
    
    len=0
    while num>0:
        if blocked.find(str(num%10))!=-1:
            return 0
        num //= 10
        len+=1
    return len

for i in range(1000000):
    num=i
    l=getlen(i)
    if l>0:
        spress=abs(num-n)
        if ans>spress+l:
            ans=spress+l

print(ans)