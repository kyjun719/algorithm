import sys
input=sys.stdin
sys.setrecursionlimit(100000)    

INF=987654321

def psum(pos):
    global ptree
    pos+=1
    s=0
    while pos>0:
        s+=ptree[pos]
        pos &= (pos-1)
    return s

def pins(pos,val):
    global ptree
    pos+=1
    while pos<len(ptree):
        ptree[pos]+=val
        pos+=(pos & -pos)

def solve(s,e):
    global cache, psum
    if s==e:
        return 0

    if cache[s][e]!=INF:
        return cache[s][e]
    
    val=INF
    for i in range(s,e):
        val=min(val,solve(s,i)+solve(i+1,e)+psum(e)-psum(s-1))
    cache[s][e]=val
    return val

tc=int(input.readline().rstrip())
for _ in range(tc):
    k=int(input.readline().rstrip())
    arr=list(map(int,input.readline().rstrip().split(' ')))
    arr.append(0)
    ptree=[0 for _ in range(k+1)]
    for i in range(k):
        pins(i,arr[i])
    
    cache=[[INF for _ in range(k)] for _ in range(k)]

    print(solve(0,k-1))
