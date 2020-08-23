import sys
# import queue
from heapq import *

sys.setrecursionlimit(100000)

"""
def solve(adj, k):
    dist = [987654321 for _ in range(len(adj))]
    dist[k] = 0
    q = queue.PriorityQueue()
    q.put((0, k))

    while not q.empty():
        nowW, now = q.get(q)
        for next,w in adj[now].items():
            if dist[now]+w < dist[next]:
                dist[next] = dist[now]+w
                q.put((dist[next], next))

    return dist
"""
def solve(adj, k):
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

n = int(sys.stdin.readline().rstrip())
m = int(sys.stdin.readline().rstrip())
adj = [dict() for _ in range(n)]
for _ in range(m):
    u,v,w = map(int, sys.stdin.readline().rstrip().split())
    u -= 1
    v -= 1
    if v in adj[u]:
        adj[u][v] = min(adj[u][v], w)
    else:
        adj[u][v] = w

s,e = map(int, sys.stdin.readline().rstrip().split())
s -= 1
e -= 1
answer = solve(adj,s)
print(answer[e])
