import sys
import time
from collections import deque

sys.setrecursionlimit(100000)

# TODO timeout, python으로 해결한 사람이 없음
startTime = time.time()
tc = int(sys.stdin.readline().rstrip())
for t in range(tc):
    m,c,i = map(int, sys.stdin.readline().rstrip().split())
    mem = [0 for _ in range(m)]
    q = deque()
    cmd = sys.stdin.readline().rstrip()
    jumpdict = dict()
    for i in range(c):
        if cmd[i]=='[':
            q.appendleft(i)
        if cmd[i]==']':
            start = q.pop()
            jumpdict[start] = (start,i)
            jumpdict[i] = (start,i)
    # print(jumpdict)
    text = sys.stdin.readline().rstrip()
    # ord({ch})
    isfinish = False
    if len(jumpdict) == 0:
        isfinish = True
    else:
        cmdptr = 0
        memptr = 0
        cnt = 0
        while True:
            if cmd[cmdptr]=='-':
                mem[memptr] = (mem[memptr]-1)%256
            elif cmd[cmdptr]=='+':
                mem[memptr] = (mem[memptr]+1)%256
            elif cmd[cmdptr]=='<':
                memptr = (memptr-1)%m
            elif cmd[cmdptr]=='>':
                memptr = (memptr+1)%m
            elif cmd[cmdptr]=='.':
                pass
            elif cmd[cmdptr]==',':
                if text is None:
                    continue
                elif len(text)>0:
                    mem[memptr]=ord(text[0])
                    text=text[1:]
                elif len(text)==0:
                    mem[memptr]=ord(text[0])
                    text=None
            elif cmd[cmdptr]=='[':
                if mem[memptr]==0:
                    # goto = jumpdict[cmdptr]
                    cmdptr = jumpdict[cmdptr][1]
            elif cmd[cmdptr]==']':
                if mem[memptr]!=0:
                    # goto = jumpdict[cmdptr]
                    cmdptr = jumpdict[cmdptr][0]
                    end=cmdptr
            cmdptr+=1

            if cmdptr>=len(cmd):
                isfinish = True
                break
            if cnt > 50000000:
                break
            cnt += 1
    if isfinish:
        print('Terminates')
    else:
        print('Loops',jumpdict[end][0],jumpdict[end][1])

print(time.time()-startTime)