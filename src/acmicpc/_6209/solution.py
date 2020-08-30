import sys
import copy
sys.setrecursionlimit(100000)

d,n,m = map(int, sys.stdin.readline().rstrip().split(" "))

# 0 2 11 14 17 21 25
arr = [0]
for i in range(n):
    arr.append(int(sys.stdin.readline().rstrip()))
arr.append(d)
arr.sort()

left=0
right=d

for i in range(100):
    mid=(left+right)/2
    cnt=0
    idxDel=0
    for i in range(1,n+2):
        if (arr[i]-arr[i-1-idxDel])<mid:
            cnt+=1
            idxDel+=1
        else:
            idxDel=0
    if cnt>m:
        right=mid
    else:
        left=mid+1
print(int((left+right)/2))