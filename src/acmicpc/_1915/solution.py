import sys
input=sys.stdin
sys.setrecursionlimit(100000)

r,c=map(int,input.readline().rstrip().split(' '))
board=[list() for _ in range(r)]

for i in range(r):
    board[i]=list(map(int,input.readline().rstrip()))

maxCnt=1
for y in range(r):
    for x in range(c):
        if board[y][x]==1:
            length=1
            while True:
                # added:y+len,x~y+len,x+len~y,x+len
                if y+length>=r or x+length>=c:
                    break
                added=0
                for i in range(length+1):
                    if x+i>=c or y+i>=r:
                        continue
                    added+=board[y+length][x+i]
                    added+=board[y+i][x+length]  
                    # print(y+length,x+i,',',y+i,x+length)              
                
                # print(y,x,length,'>>',added)

                if added-1 != (2*length+1):
                    break
                length+=1

            maxCnt=max(maxCnt,length)
print(int(pow(maxCnt,2)))