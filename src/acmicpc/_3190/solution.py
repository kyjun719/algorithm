import sys
sys.setrecursionlimit(100000)

def isWall(p, n):
    return p[0]<0 or p[0]>=n or p[1]<0 or p[1]>=n

n = int(sys.stdin.readline().rstrip())
k = int(sys.stdin.readline().rstrip())
apple = []
for i in range(k):
    y,x=map(int, sys.stdin.readline().split())
    apple.append([y-1,x-1])
l = int(sys.stdin.readline().rstrip())
dc = {}
for i in range(l):
    t,d = sys.stdin.readline().rstrip().split()
    dc[t]=d
# print(dc)
snake = [(0,0)]
t = 0
d = [[0,1],[1,0],[0,-1],[-1,0]]
nowd = 0
while True:
    t += 1
    bh = snake[-1]
    nh = [bh[0]+d[nowd][0], bh[1]+d[nowd][1]]
    if nh in snake:
        break
    if isWall(nh, n):
        break
    stretched = False
    if nh in apple:
        stretched = True
        apple.remove(nh)
    if not stretched:
        snake.pop(0)
    snake.append(nh)
    if str(t) in dc:
        nowd = (nowd+1)%4 if dc[str(t)]=='D' else (nowd-1)%4
    # print(t,snake)
print(t)
