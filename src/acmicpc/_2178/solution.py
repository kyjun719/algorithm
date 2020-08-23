import sys
from collections import deque

sys.setrecursionlimit(100000)

def isInBound(y,x,n,m):
    return y >= 0 and y < n and x >= 0 and x < m

def solve(board, n, m):
    visited = [[False for _ in range(m)] for _ in range(n)]
    answer = [[987654321 for _ in range(m)] for _ in range(n)]
    q = deque()
    q.append((0,0))
    answer[0][0] = 1
    visited[0][0] = True

    while q:
        y,x = q.popleft()
        next = answer[y][x]+1
        if isInBound(y+1,x,n,m):
            if board[y+1][x] == 1 and not visited[y+1][x] and answer[y+1][x]>next:
                answer[y+1][x] = next
                q.append((y+1,x))
    
        if isInBound(y,x+1,n,m):
            if board[y][x+1] == 1 and not visited[y][x+1] and answer[y][x+1]>next:
                answer[y][x+1] = next
                q.append((y,x+1))

        if isInBound(y-1,x,n,m):
            if board[y-1][x] == 1 and not visited[y-1][x] and answer[y-1][x]>next:
                answer[y-1][x] = next
                q.append((y-1,x))

        if isInBound(y,x-1,n,m):
            if board[y][x-1] == 1 and not visited[y][x-1] and answer[y][x-1]>next:
                answer[y][x-1] = next
                q.append((y,x-1))

    return answer

n, m = map(int, sys.stdin.readline().rstrip().split())
board = [list() for _ in range(n)]
for i in range(n):
    board[i] = list(map(int, sys.stdin.readline().rstrip()))

answer = solve(board, n, m)
print(answer[n-1][m-1])
