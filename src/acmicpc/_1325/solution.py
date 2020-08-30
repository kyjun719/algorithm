import sys
from collections import deque
from heapq import *

sys.setrecursionlimit(100000)

def bfs(arr, adj, start):
    visited = [False]*n
    """
    q = deque()
    q.append(start)
    visited[start]=True
    while q:
        now = q.popleft()
        for next in adj[now]:
            if not visited[next]:
                q.append(next)
                visited[next]=True
                arr[next] += 1
    """
    q = [start]
    visited[start]=True
    while q:
        now = heappop(q)
        for next in adj[now]:
            if not visited[next]:
                heappush(q,next)
                visited[next]=True
                arr[next] += 1

n,m = map(int, sys.stdin.readline().rstrip().split(" "))
adj = [list() for _ in range(n)]
for _ in range(m):
    a,b = map(int, sys.stdin.readline().rstrip().split(" "))
    adj[a-1].append(b-1)

arr = [0] * n
for i in range(n):
    bfs(arr,adj,i)

maxVal=max(arr)
"""
for val in arr:
    maxVal = max(maxVal,val)
"""

ret = ""
for i in range(n):
    if arr[i]==maxVal:
        ret +=str(i+1)+" "

print(ret)