import sys
from collections import deque

sys.setrecursionlimit(100000)

def bfs(adj):
    visited = [False for _ in range(len(adj))]
    q = deque()
    q.append(0)
    visited[0] = True
    cnt = 0
    while q:
        now = q.popleft()
        for next in adj[now]:
            if not visited[next]:
                visited[next] = True
                cnt += 1
                q.append(next)
    
    return cnt

n = int(sys.stdin.readline().rstrip())
adj = [list() for _ in range(n)]

v = int(sys.stdin.readline().rstrip())
for _ in range(v):
    u,v = map(int, sys.stdin.readline().rstrip().split())
    adj[u-1].append(v-1)
    adj[v-1].append(u-1)

print(bfs(adj))