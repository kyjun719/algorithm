import sys
sys.setrecursionlimit(100000)

def dfsAll(arr, visited, m, n):
    answer = 0
    for y in range(n):
        for x in range(m):
            if arr[y][x]==1 and visited[y][x]==-1:
                answer += 1
                dfs(arr, visited, y, x, m, n)
    
    return answer


def dfs(arr, visited, y, x, m, n):
    visited[y][x] = 1
    ptrArr = [[-1,0],[1,0],[0,-1],[0,1]]
    for ptr in ptrArr:
        [dy,dx] = [ptr[0]+y, ptr[1]+x]
        if not inBound(dy, dx, m, n):
            continue

        if arr[dy][dx]==1 and visited[dy][dx]==-1:
            dfs(arr, visited, dy, dx, m, n)


def inBound(dy, dx, m, n):
    return dx<m and dx>=0 and dy<n and dy>=0


T = int(sys.stdin.readline().rstrip())
for tc in range(T):
    m,n,k = map(int, sys.stdin.readline().rstrip().split())
    arr = [[0 for _ in range(m)] for _ in range(n)]
    visited = [[-1 for _ in range(m)] for _ in range(n)]

    for _ in range(k):
        x,y = map(int, sys.stdin.readline().rstrip().split())
        arr[y][x] = 1
    
    print(dfsAll(arr, visited, m, n))