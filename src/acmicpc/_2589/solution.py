import sys
from collections import deque
input = sys.stdin
sys.setrecursionlimit(100000)

r,c=map(int,input.readline().rstrip().split(' '))

board=[list() for _ in range(r)]

for y in range(r):
    board[y]=list(input.readline().rstrip())

def bfs(y,x):
    visited=[[False for _ in range(c)] for _ in range(r)]
    q=deque()
    q.append((y,x,0))
    visited[y][x]=True
    maxCnt=0
    while q:
        ny,nx,cnt=q.popleft()
        maxCnt=max(cnt,maxCnt)

        if 0<=ny+1<r and not visited[ny+1][nx] and board[ny+1][nx]=='L':
            visited[ny+1][nx]=True
            q.append((ny+1,nx,cnt+1))

        if 0<=ny-1<r and not visited[ny-1][nx] and board[ny-1][nx]=='L':
            visited[ny-1][nx]=True
            q.append((ny-1,nx,cnt+1))

        if 0<=nx+1<c and not visited[ny][nx+1] and board[ny][nx+1]=='L':
            visited[ny][nx+1]=True
            q.append((ny,nx+1,cnt+1))

        if 0<=nx-1<c and not visited[ny][nx-1] and board[ny][nx-1]=='L':
            visited[ny][nx-1]=True
            q.append((ny,nx-1,cnt+1))

    return maxCnt

ans=0
for y in range(r):
    for x in range(c):
        if board[y][x]=='L':
            ans=max(ans,bfs(y,x))

print(ans)
