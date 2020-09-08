import sys
from collections import deque
sys.setrecursionlimit(100000)
input=sys.stdin

n=int(input.readline().rstrip())
board=[list() for _ in range(n)]

for i in range(n):
    board[i]=list(input.readline().rstrip())

visited=[[False for _ in range(n)] for _ in range(n)]
cnt=0

def bfs(y,x):
    color=board[y][x]
    q=deque()
    visited[y][x]=True
    q.append((y,x))
    while q:
        y,x=q.popleft()
        if 0<=y-1<n and board[y-1][x]==color and not visited[y-1][x]:
            visited[y-1][x]=True
            q.append((y-1,x))

        if 0<=y+1<n and board[y+1][x]==color and not visited[y+1][x]:
            visited[y+1][x]=True
            q.append((y+1,x))

        if 0<=x-1<n and board[y][x-1]==color and not visited[y][x-1]:
            visited[y][x-1]=True
            q.append((y,x-1))

        if 0<=x+1<n and board[y][x+1]==color and not visited[y][x+1]:
            visited[y][x+1]=True
            q.append((y,x+1))


for y in range(n):
    for x in range(n):
        if not visited[y][x]:
            bfs(y,x)
            cnt+=1

# print(visited)

for i in range(n):
    for j in range(n):
        if board[i][j]=='R':
            board[i][j]='G'

visited=[[False for _ in range(n)] for _ in range(n)]
weekcnt=0
for y in range(n):
    for x in range(n):
        if not visited[y][x]:
            bfs(y,x)
            weekcnt+=1

# print(visited)

print(cnt, weekcnt)