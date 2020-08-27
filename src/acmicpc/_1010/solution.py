import sys

sys.setrecursionlimit(100000)

# calc mCn
pre = [[0 for _ in range(31)] for _ in range(31)]
pre[0][0]=1
pre[1][0]=1
pre[1][1]=1
for m in range(2,31):
    for n in range(m+1):
        if n==0:
            pre[m][n] = 1
        else:
            pre[m][n]=pre[m-1][n]+pre[m-1][n-1]

t = int(sys.stdin.readline().rstrip())

for _ in range(t):
    n,m = map(int, sys.stdin.readline().rstrip().split())
    print(pre[m][n])
