import sys
from collections import deque
input=sys.stdin
sys.setrecursionlimit(100000)

r,c=map(int,input.readline().rstrip().split(' '))
board=[[] for _ in range(r)]
sq=deque()
wq=deque()
visited=[[False for _ in range(c)] for _ in range(r)]
for i in range(r):
    board[i]=list(input.readline().rstrip())
    for j in range(c):
        if board[i][j]=='S':
            sq.append((i,j,0))
            board[i][j]='.'
            visited[i][j]=True
        if board[i][j]=='D':
            d=(i,j)
        if board[i][j]=='*':
            wq.append((i,j))

ans=987654321
while sq:
    nsq=deque()
    nwq=deque()

    while sq:
        sy,sx,cnt=sq.pop()
        # print(sy,sx,'>>',d,'(',cnt,')')
        if ans<=cnt:
            continue
        if board[sy][sx]=='*':
            continue
        if sy==d[0] and sx==d[1]:
            ans=min(ans,cnt)
            continue
        if 0<=sy+1<r and not visited[sy+1][sx]:
            if board[sy+1][sx]=='.' or board[sy+1][sx]=='D':
                visited[sy+1][sx]=True
                nsq.append((sy+1,sx,cnt+1))
        if 0<=sy-1<r and not visited[sy-1][sx]:
            if board[sy-1][sx]=='.' or board[sy-1][sx]=='D':
                visited[sy-1][sx]=True
                nsq.append((sy-1,sx,cnt+1))
        if 0<=sx+1<c and not visited[sy][sx+1]:
            if board[sy][sx+1]=='.' or board[sy][sx+1]=='D':
                visited[sy][sx+1]=True
                nsq.append((sy,sx+1,cnt+1))
        if 0<=sx-1<c and not visited[sy][sx-1]:
            if board[sy][sx-1]=='.' or board[sy][sx-1]=='D':
                visited[sy][sx-1]=True
                nsq.append((sy,sx-1,cnt+1))

    while wq:
        wy,wx=wq.pop()
        if 0<=wy+1<r and board[wy+1][wx]=='.':
            board[wy+1][wx]='*'
            nwq.append((wy+1,wx))
        if 0<=wy-1<r and board[wy-1][wx]=='.':
            board[wy-1][wx]='*'
            nwq.append((wy-1,wx))
        if 0<=wx+1<c and board[wy][wx+1]=='.':
            board[wy][wx+1]='*'
            nwq.append((wy,wx+1))
        if 0<=wx-1<c and board[wy][wx-1]=='.':
            board[wy][wx-1]='*'
            nwq.append((wy,wx-1))
    
    wq.extend(nwq)
    
    # for i in range(r):
    #     print(board[i])
    # for i in range(r):
    #     print(visited[i])
    
    sq.extend(nsq)

print('KAKTUS' if ans==987654321 else ans)
