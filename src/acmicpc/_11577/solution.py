import sys
input=sys.stdin
sys.setrecursionlimit(100000)

n,k=map(int,input.readline().rstrip().split(' '))
arr=list(map(int,input.readline().rstrip().split()))
ans=987654321
pusharr=[0 for i in range(n-k+1)]

"""
def solve(cnt,arr,pusharr):
    if arr.count(0)==n:
        global ans
        ans=min(ans,cnt)
        return
    for i in range(n-k+1):
        if pusharr[i]<2:
            pusharr[i]+=1
            for j in range(k):
                arr[i+j]^=1
            solve(cnt+1,arr,pusharr)
            pusharr[i]-=1
            for j in range(k):
                arr[i+j]^=1

solve(0,arr,pusharr)
"""

def solve(arr,pusharr):
    idx=0
    cnt=0
    while idx<n:
        # print(arr,pusharr,idx)
        if arr[idx]==0:
            idx+=1
            continue
        if idx>(n-k):
            idx=n-k
        if pusharr[idx]>=2:
            break
        cnt+=1
        pusharr[idx]+=1
        next=idx
        for i in range(k):
            d=k-1-i
            arr[idx+d]^=1
            if arr[idx+d]==1:
                next=idx+d
    if idx==n:
        global ans
        ans=cnt

solve(arr,pusharr)
print("Insomnia" if ans==987654321 else ans)
