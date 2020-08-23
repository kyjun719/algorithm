import sys
from heapq import *

sys.setrecursionlimit(100000)

def solve2(adj, k):
    dist = [987654321 for _ in range(len(adj))]
    dist[k] = 0
    q = [(0, k)]

    while q:
        nowW, now = heappop(q)
        for next,w in adj[now].items():
            if dist[now]+w < dist[next]:
                dist[next] = dist[now]+w
                heappush(q, (dist[next], next))

    return dist

v,e = map(int, sys.stdin.readline().rstrip().split())
k = int(sys.stdin.readline().rstrip())
k -= 1

adj = [dict() for _ in range(v)]
for _ in range(e):
    u,v,w = map(int, sys.stdin.readline().rstrip().split())
    u -= 1
    v -= 1
    if v in adj[u]:
        adj[u][v] = min(adj[u][v], w)
    else:
        adj[u][v] = w

answer = solve2(adj,k)
for i in range(len(answer)):
    print('INF' if answer[i]==987654321 else answer[i])
