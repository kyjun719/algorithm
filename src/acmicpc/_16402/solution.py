import sys
sys.setrecursionlimit(100000)

n,m = map(int, sys.stdin.readline().rstrip().split(" "))
namelist = []
for i in range(n):
    namelist.append(sys.stdin.readline().rstrip())

parent = []
for i in range(n):
    parent.append(i)

def getparent(i):
    if parent[i] == i:
        return i
    return getparent(parent[i])

for i in range(m):
    info = sys.stdin.readline().rstrip().split(",")
    a = namelist.index(info[0])
    b = namelist.index(info[1])
    pa = getparent(a)
    pb = getparent(b)
    # print(a,pa,'>>',b,pb)
    if info[2]=='1':
        if pa == pb:
            parent[pb]=a
            parent[a]=a
        else:
            parent[pb]=pa
    else:
        if pa==pb:
            parent[pb]=b
            parent[b]=b
        else:
            parent[pa]=pb
    # print(parent)
# print(parent)
nonlist = []
for i in range(n):
    if parent[i] == i:
        nonlist.append(namelist[i])

nonlist.sort()
print(len(nonlist))
for tmp in nonlist:
    print(tmp)