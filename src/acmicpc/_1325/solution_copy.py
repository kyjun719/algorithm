import sys
input=sys.stdin.readline
sys.setrecursionlimit(10**9)
n,m=map(int,input().split())
adj=[[] for _ in range(n)]
inv=[[] for _ in range(n)]
for _ in range(m):
  a,b=map(int,input().split())
  adj[b-1].append(a-1)
  inv[a-1].append(b-1)
# 방문한 순서
stack=[]
v=[0]*n
def dfs(i):
  v[i]=1
  for j in adj[i]:
    if v[j]==0:
      dfs(j)
  stack.append(i)
for i in range(n):
  if v[i]==0:
    dfs(i)
print(stack)
v=[0]*n
scc=[0]*n
scc_num=0
scc_size=[]
def dfs_inv(i):
  global size
  v[i]=1
  scc[i]=scc_num
  size+=1
  for j in inv[i]:
    if v[j]==0:
      dfs_inv(j)
while stack:
  i=stack.pop()
  if v[i]==0:
    size=0
    dfs_inv(i)
    scc_size.append(size)
    scc_num+=1
print('scc::'+str(scc))
print(scc_size)
scc_adj=[set() for _ in range(scc_num)]
scc_inv=[set() for _ in range(scc_num)]
for i in range(n):
  for j in adj[i]:
    if scc[i]!=scc[j]:
      scc_adj[scc[i]].add(scc[j])
      scc_inv[scc[j]].add(scc[i])
scc_outd=[len(scc_adj[i]) for i in range(scc_num)]
d=[set() for _ in range(scc_num)]
Q=[]
for i in range(scc_num):
  if scc_outd[i]==0:
    Q.append(i)
while Q:
  q=[]
  for i in Q:
    d[i].add(i)
    for j in scc_inv[i]:
      d[j]=d[j].union(d[i])
      scc_outd[j]-=1
      if scc_outd[j]==0:
        q.append(j)
    print(d)
  Q=q
ds=[sum(scc_size[j] for j in d[i]) for i in range(scc_num)]
M=max(ds)
for i in range(n):
  if ds[scc[i]]==M:
    print(i+1,end=' ')