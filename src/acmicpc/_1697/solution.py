import sys
sys.setrecursionlimit(100000)

def solve(now, end):
    board = [-1 for _ in range(100001)]
    q = list()
    q.append(now)
    board[now] = 0
    answer = 987654321
    while len(q) > 0:
        now = q.pop(0)
        if now == end:
            answer = min(answer, board[now])
            continue

        next = board[now]+1
        if answer <= next:
            continue
        
        if now+1<=100000 and board[now+1]==-1:
            board[now+1] = next
            q.append(now+1)
        
        if now-1>=0 and board[now-1]==-1:
            board[now-1] = next
            q.append(now-1)

        if now*2<=100000 and board[now*2]==-1:
            board[now*2] = next
            q.append(now*2)
        
    return answer

n,k = map(int, sys.stdin.readline().rstrip().split())
print(solve(n,k))
