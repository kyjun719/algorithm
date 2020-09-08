import sys
import time
sys.setrecursionlimit(100000)
input = sys.stdin

# n = int(input.readline().rstrip())

# n*n에 n개 = 한줄에 하나씩

def solve1():
    col,pline,mline=[False]*15,[False]*30,[False]*30

    def solve(r):
        if r==n:
            return 1

        cnt=0
        for c in range(n):
            if not col[c] and not pline[r+c] and not mline[r-c+14]:
                col[c]=pline[r+c]=mline[r-c+14]=True
                cnt+=solve(r+1)
                col[c]=pline[r+c]=mline[r-c+14]=False
        return cnt

    return solve(0)

def solve2():
    # 각 행의 퀸의 열값
    qinfo = [0]*(n+1)

    def canGo(r):
        for i in range(1,r):
            if qinfo[i]==qinfo[r]:
                return False
            if abs(qinfo[i]-qinfo[r])==abs(i-r):
                return False
        return True

    def solve(r):
        if r==n+1:
            return 1

        cnt=0
        for c in range(1,n+1):
            qinfo[r]=c
            if canGo(r)==True:
                cnt+=solve(r+1)
            qinfo[r]=0
        return cnt

    return solve(1)

def solve3():
    # n*n에 n개 = 한줄에 하나씩
    board = [[0]*n for _ in range(n)]

    def canGo(r,c):
        for i in range(r+1):
            if board[r-i][c]==1:
                return False
        
        for i in range(min(r+1,c+1)):
            if board[r-i][c-i]==1:
                return False
        
        for i in range(min(r+1,n+c)):
            if board[r-i][c+i]==1:
                return False
        
        return True

    def solve(r):
        if r==n:
            return 1
        cnt=0
        for c in range(n):
            if canGo(r,c)==True:
                board[r][c]=1
                cnt+=solve(r+1)
                board[r][c]=0

        return cnt

    return solve(0)

for i in range(1,14):
    n=i
    now=time.time()
    print(solve1(),time.time()-now)
    now=time.time()
    print(solve2(),time.time()-now)
    now=time.time()
    print(solve3(),time.time()-now)
