import sys
sys.setrecursionlimit(100000)
input=sys.stdin

a=list(input.readline().rstrip())
b=list(input.readline().rstrip())

"""
LCS(X_i, Y_i) = 0 if i==0 or j == 0
                LCS(X_i-1, Y_j-1)+1 if i,j>0 and x_i == y_i
                max(LCS(X_i, Y_j-1), LCS(X_i-1, Y_j)) if i_j>0 and x_i != y_i
"""

lcs=[[0]*(len(b)+1) for _ in range(len(a)+1)]
for aptr in range(1,len(a)+1):
    for bptr in range(1,len(b)+1):
        if a[aptr-1]==b[bptr-1]:
            lcs[aptr][bptr]=lcs[aptr-1][bptr-1]+1
        else:
            lcs[aptr][bptr]=max(lcs[aptr][bptr-1],lcs[aptr-1][bptr])

print(lcs[len(a)][len(b)])
