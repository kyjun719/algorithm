import sys
from collections import deque

sys.setrecursionlimit(100000)

def inBound(y,x,n):
    return y>=0 and y<n and x>=0 and x<n

def solve(board):
    n = len(board)
    visited = [[False for _ in range(n)] for _ in range(n)]
    cntArr = []
    delArr = [[-1,0],[1,0],[0,-1],[0,1]]

    for y in range(n):
        for x in range(n):
            if board[y][x] == 1 and not visited[y][x]:
                num = 1
                visited[y][x] = True
                q = deque()
                q.append((y,x))
                while q:
                    ny,nx = q.popleft()
                    for d in delArr:
                        dy = ny+d[0]
                        dx = nx+d[1]
                        if inBound(dy,dx,n):
                            if board[dy][dx]==1 and not visited[dy][dx]:
                                num += 1
                                visited[dy][dx] = True
                                q.append((dy,dx))
                
                cntArr.append(num)
    
    return cntArr

n = int(sys.stdin.readline().rstrip())
board = [list() for _ in range(n)]

for i in range(n):
    board[i] = list(map(int, sys.stdin.readline().rstrip()))

cntArr = solve(board)
print(len(cntArr))
cntArr = sorted(cntArr)
for i in cntArr:
    print(i)
