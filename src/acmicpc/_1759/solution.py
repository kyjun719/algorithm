import sys
sys.setrecursionlimit(100000)
input=sys.stdin

L,C=map(int,input.readline().rstrip().split(' '))
chArr = input.readline().rstrip().split(' ')
chArr.sort()
answer=[]

# 자음에서 시작하는 트라이를 만드는게 나을수도...

def solve(selected,cnt,idx):    
    if cnt==L:
        tmp=''
        cnt=0
        for i in selected:
            if chArr[i]=='a' or chArr[i]=='e' or chArr[i]=='i' or chArr[i]=='o' or chArr[i]=='u':
                cnt +=1
            tmp+=chArr[i]
        if cnt>=1 and L-cnt>=2:
            answer.append(tmp)
        return
    
    if idx >= C:
        return
    
    for i in range(idx+1,C):
        selected.append(i)
        # print('selected::',selected)
        solve(selected,cnt+1,i)
        selected.pop()
    
    solve(selected,cnt,idx+1)

solve([],0,-1)
answer=list(set(answer))
answer.sort()
for tmp in answer:
    print(tmp)