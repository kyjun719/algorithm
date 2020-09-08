import sys
from collections import deque
input = sys.stdin
sys.setrecursionlimit(100000)

r,c=map(int, input.readline().rstrip().split())
board=[['']*c]*r
# print(board)
for i in range(r):
    board[i]=list(input.readline().rstrip())

def printboard():
    # print('========================')
    for row in board:
        print(''.join(row))
    # print('========================')

def movestone(row,col):
    nextr=row+1
    nextc=0
    # 돌의 아랫칸이 벽으로 막혀있거나 가장 아랫줄이라면, 돌은 그 자리에 그대로 멈춰 있는다.
    if nextr>=r:
        return
    if board[nextr][col]=='X':
        return

    # 돌의 아랫칸이 비어있다면, 돌은 아랫칸으로 이동한다.
    if board[nextr][col]=='.':
        nextc=col
        board[nextr][nextc]='O'
        board[row][col]='.'
        movestone(nextr,nextc)
        return
    # 돌의 아랫칸에 돌이 있다면, 돌은 다음과 같이 미끄러진다.
    # 만약 돌의 왼쪽 칸과 왼쪽-아래 칸이 비어있다면, 돌은 왼쪽으로 미끄러진다.
    if (0<=col-1 and board[row][col-1]=='.') and (board[nextr][col-1]=='.'):
        nextc=col-1
        board[nextr][nextc]='O'
        board[row][col]='.'
        movestone(nextr,nextc)
        return
    # 만약 돌이 왼쪽으로 미끄러지지 않았고, 오른쪽 칸과 오른쪽-아래 칸이 비어있다면, 돌은 오른쪽으로 미끄러진다.
    if (c>col+1 and board[row][col+1]=='.') and (board[nextr][col+1]=='.'):
        nextc=col+1
        board[nextr][nextc]='O'
        board[row][col]='.'
        movestone(nextr,nextc)
        return
    # 위의 두 경우가 아니라면 돌은 그 자리에 멈추고, 다시는 움직이지 않는다.

# printboard()

"""
n = int(input.readline().rstrip())
for _ in range(n):
    scol = int(input.readline().rstrip())
    scol-=1
    board[0][scol]='O'
    movestone(0,scol)
    # printboard()
"""

"""
# n,c컬럼에서 떨어트리면 다음 도착하는 위치값
colmovearr = [[tuple() for _ in range(c)] for _ in range(r)]

for col in range(c):
    startrow=0
    for row in range(r):
        if(board[row][col]=='X'):
            for i in range(startrow,row):
                colmovearr[i][col]=(row-1,col)
            startrow=row+1
    for i in range(startrow,r):
        colmovearr[i][col]=(r-1,col)

def printcolmove():
    print('========================')
    for col in colmovearr:
        print(col)
    print('========================')

def updatecolmove(movedhistory,stopr,stopc):
    # print('update list::',movedhistory)
    while movedhistory:
        row,col = movedhistory.pop()
        for i in range(r):
            if row-i<0 or board[row-i][col]=='X':
                break
            colmovearr[row-i][col]=(stopr,stopc)

# printcolmove()

n = int(input.readline().rstrip())
for _ in range(n):
    col = int(input.readline().rstrip())    
    col-=1

    movedhistory=deque()
    row=0
    while True:
        nextr,col=colmovearr[row][col]
        movedhistory.append((row,col))
        # print(row,col,'>>',nextr,col)
        # 벽 또는 바닥이면 업데이트 후 종료
        if nextr==r-1 or (nextr+1<r-1 and board[nextr+1][col]=='X'):
            board[nextr][col]='O'
            # colmovearr update
            colmovearr[nextr][col]=(-1,-1)
            movedhistory.append((nextr-1,col))

            updatecolmove(movedhistory,nextr-1,col)
            break
        if board[nextr+1][col]=='O':
            # 만약 돌의 왼쪽 칸과 왼쪽-아래 칸이 비어있다면, 돌은 왼쪽으로 미끄러진다.
            if (0<=col-1 and board[nextr][col-1]=='.') and (board[nextr+1][col-1]=='.'):
                movedhistory.append((nextr,col))
                col-=1
                row=nextr
                # print('moveto::',row,col)
                continue
            # 만약 돌이 왼쪽으로 미끄러지지 않았고, 오른쪽 칸과 오른쪽-아래 칸이 비어있다면, 돌은 오른쪽으로 미끄러진다.
            if (c>col+1 and board[nextr][col+1]=='.') and (board[nextr+1][col+1]=='.'):
                movedhistory.append((nextr,col))
                col+=1
                row=nextr
                # print('moveto::',row,col)
                continue
            # 위의 두 경우가 아니라면 돌은 그 자리에 멈추고, 다시는 움직이지 않는다.
            board[nextr][col]='O'
             # colmovearr update
            colmovearr[nextr][col]=(-1,-1)

            movedhistory.append((nextr-1,col))
            updatecolmove(movedhistory,nextr-1,col)
    # printboard()
    # printcolmove()
"""

# val=0~(r-1)*(c-1)
destarr = [[tuple()*c] for _ in range(r)]
for col in range(c):
    startrow=0
    for row in range(r):
        if(board[row][col]=='X'):
            destarr[row][col]=(-1,-1)
            for i in range(startrow,row):
                destarr[i][col]=(row-1,col)
            startrow=row+1
    for i in range(startrow,r):
        destarr[i][col]=(r-1,col)

n = int(input.readline().rstrip())
for _ in range(n):
    col = int(input.readline().rstrip())    
    col-=1
    row=0
    movedhistory=deque()

    while True:
        nr,nc=destarr[row][col]


printboard()