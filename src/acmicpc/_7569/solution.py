import sys
from collections import deque
sys.setrecursionlimit(100000)
input = sys.stdin

m,n,h = map(int, input.readline().rstrip().split(" "))
arr = [[list(map(int, input.readline().rstrip().split(" "))) for _ in range(n)] for _ in range(h)]
q = deque()
for z in range(h):
    for y in range(n):
        for x in range(m):
            if arr[z][y][x]==1:
                q.append((z,y,x,0))
# print('arr',arr)
# print('cnt',cnt)


dirArr = [[0,0,1],[0,0,-1],[0,1,0],[0,-1,0],[1,0,0],[-1,0,0]]
def canGo(dz,dy,dx):
    return dz>=0 and dz<h and dy>=0 and dy<n and dx>=0 and dx<m

while q:
    z,y,x,count = q.popleft()
    if 0<=z+1<h and arr[z+1][y][x]==0:
        q.append((z+1,y,x,count+1))
        arr[z+1][y][x]=1
    if 0<=z-1<h and arr[z-1][y][x]==0:
        q.append((z-1,y,x,count+1))
        arr[z-1][y][x]=1

    if 0<=y+1<n and arr[z][y+1][x]==0:
        q.append((z,y+1,x,count+1))
        arr[z][y+1][x]=1
    if 0<=y-1<n and arr[z][y-1][x]==0:
        q.append((z,y-1,x,count+1))
        arr[z][y-1][x]=1
    
    if 0<=x+1<m and arr[z][y][x+1]==0:
        q.append((z,y,x+1,count+1))
        arr[z][y][x+1]=1
    if 0<=x-1<m and arr[z][y][x-1]==0:
        q.append((z,y,x-1,count+1))
        arr[z][y][x-1]=1

for z in arr:
    for y in z:
        for x in y:
            if x==0:
                count=-1
                break

print(count)