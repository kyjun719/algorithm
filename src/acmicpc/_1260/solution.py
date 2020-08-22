import sys
sys.setrecursionlimit(100000)

def dfs(arr, visited, pos, n):
    visited[pos] = 1
    answer = [pos]
    for x in range(n):
        if arr[pos][x]==1 and visited[x]==-1:
            answer.extend(dfs(arr,visited,x,n))
    
    return answer

def bfs(arr, visited, pos, n):
    a = [pos]
    answer = []
    while len(a)>0:
        val = a.pop(0)
        if visited[val]==-1:
            visited[val]=1
            answer.append(val)
            for x in range(n):
                if arr[val][x]==1:
                    a.append(x)
    
    return answer

n,m,v = map(int, sys.stdin.readline().rstrip().split())
arr = [[0 for _ in range(n+1)] for _ in range(n+1)]
visited = [-1 for _ in range(n+1)]

for _ in range(m):
    x,y = map(int, sys.stdin.readline().rstrip().split())
    arr[y][x] = 1
    arr[x][y] = 1

print(str(dfs(arr, visited, v, n+1)).replace('[','').replace(']','').replace(',',''))

visited = [-1 for _ in range(n+1)]
print(str(bfs(arr, visited, v, n+1)).replace('[','').replace(']','').replace(',',''))